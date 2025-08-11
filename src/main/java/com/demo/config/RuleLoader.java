package com.demo.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleLoader {

    public List<Rule> loadRules(String id){
        Rule invalidCountryCode = new Rule()
                .setId("Invalid Country Code")
                .setCondition("Country(null == code || code.trim().length() != 2)")
                .setAction("results.put(\"Country.code\", \"Invalid Country Code\");");
        return List.of(invalidCountryCode);
    }
}
