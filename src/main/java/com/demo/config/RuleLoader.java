package com.demo.config;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleLoader {

    public List<Rule> loadRules(String id){
        Rule invNumIsMandatory = new Rule()
                .setId("Invoice Number is Mandatory")
                .setCondition("$ctx : DocumentContext(); eval( null == $ctx.read(\"$.invNum\"));")
                .setAction("results.put(\"Invoice.InvoiceNumber\", \"Invoice Number is Mandatory\");");
        Rule invalidInvoiceNumber = new Rule()
                .setId("Invoice Number is Invalid")
                .setCondition("$ctx : DocumentContext(); $inv : String() from $ctx.read(\"$.invNum\", String.class); eval($inv.length() != 2);")
                .setAction("results.put(\"Invoice.InvoiceNumber\", \"Invoice Number is Invalid\");");
        return List.of(invNumIsMandatory, invalidInvoiceNumber);
    }
}
