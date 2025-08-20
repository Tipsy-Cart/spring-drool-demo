package com.demo.controller;

import com.demo.beanOne.Invoice;
import com.demo.beanOne.LineItem;
import com.demo.beanOne.Tax;
import com.demo.beanOne.ValidationResult;
import com.demo.config.KieBaseManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.apache.poi.ss.usermodel.*;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        kieSession.setGlobal("results", results);
        ObjectMapper mapper = new ObjectMapper();
        String invoiceJson = mapper.writeValueAsString(invoice);
        DocumentContext invoiceCtx = JsonPath.using(config).parse(invoiceJson);
        kieSession.insert(invoiceCtx);
        kieSession.fireAllRules();
        kieSession.dispose();
        return results.get();
    }


}
