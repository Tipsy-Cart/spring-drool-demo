package com.demo.config;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KieBaseManager {

    private final Map<String, KieBase> kieBaseMap = new ConcurrentHashMap<>();
    private final RuleLoader ruleLoader;
    private final ResourceLoader resourceLoader;

    public KieBaseManager(RuleLoader ruleLoader, ResourceLoader resourceLoader) {
        this.ruleLoader = ruleLoader;
        this.resourceLoader = resourceLoader;
    }

    public KieBase get(String id){
        return this.kieBaseMap.computeIfAbsent(id, this::loadKieBase);
    }

    private KieBase loadKieBase(String id) {
        List<Rule> rules = ruleLoader.loadRules(id);
        ObjectDataCompiler compiler = new ObjectDataCompiler();
        Resource resource = resourceLoader.getResource("classpath:rules/" + id + ".drl");
        InputStream ruleInputStream = null;
        String drl = null;
        try {
            ruleInputStream = resource.getInputStream();
            drl = new String(ruleInputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new KieHelper().addContent(drl, ResourceType.DRL).build();
    }

//    private KieBase loadKieBase(String id) {
//        List<Rule> rules = ruleLoader.loadRules(id);
//        ObjectDataCompiler compiler = new ObjectDataCompiler();
//        Resource resource = resourceLoader.getResource("classpath:rules/template.drt");
//        InputStream templateInputStream = null;
//        try {
//            templateInputStream = resource.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        String drl = compiler.compile(rules, templateInputStream);
//        return new KieHelper().addContent(drl, ResourceType.DRL).build();
//    }

}
