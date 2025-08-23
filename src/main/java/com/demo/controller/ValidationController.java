package com.demo.controller;

import com.demo.bean.Invoice;
import com.demo.beanOne.ValidationResult;
import com.demo.config.KieBaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.kie.api.KieBase;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ValidationController {

    private final KieBaseManager kieBaseManager;

    private final Configuration config = Configuration.builder()
            .options(Option.SUPPRESS_EXCEPTIONS)
            .build();

    public ValidationController(KieBaseManager kieBaseManager) {
        this.kieBaseManager = kieBaseManager;
    }

    @PostMapping("/validate")
    public Map<String, String> validate(@RequestParam String id, @RequestBody Invoice invoice) throws JsonProcessingException {
        ValidationResult results = new ValidationResult();
        KieBase kieBase = kieBaseManager.get(id);
        KieSession kieSession = kieBase.newKieSession();
//        kieSession.addEventListener(new DebugAgendaEventListener());
//        kieSession.addEventListener(new DebugRuleRuntimeEventListener());
        kieSession.setGlobal("results", results);
        kieSession.setGlobal("jsonHelper", new JsonHelper());
        ObjectMapper mapper = new ObjectMapper();
        String invoiceJson = mapper.writeValueAsString(invoice);
        DocumentContext invoiceCtx = JsonPath.using(config).parse(invoiceJson);
        kieSession.insert(invoiceCtx);
        kieSession.getAgenda().getAgendaGroup("Tax").setFocus();
        kieSession.fireAllRules();
        if(!results.get().isEmpty()){
            kieSession.dispose();
            return results.get();
        }
        kieSession.getAgenda().getAgendaGroup("LineItem").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return results.get();
    }


}
