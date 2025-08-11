package com.demo.controller;

import com.demo.bean.Country;
import com.demo.bean.ValidationResult;
import com.demo.config.KieBaseManager;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private final KieBaseManager kieBaseManager;

    public TestController(KieBaseManager kieBaseManager) {
        this.kieBaseManager = kieBaseManager;
    }

    @PostMapping("/validate")
    public Map<String,String> validate(@RequestParam String id, @RequestBody Country country) {
        ValidationResult results = new ValidationResult();
        KieBase kieBase = kieBaseManager.get(id);
        KieSession kieSession = kieBase.newKieSession();
        kieSession.setGlobal("results", results);
        kieSession.insert(country);
        kieSession.fireAllRules();
        kieSession.dispose();
        return results.get();
    }

}
