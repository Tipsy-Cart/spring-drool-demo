package com.demo.config;

public class Rule {

    private String id;
    private String condition;
    private String action;

    public String getId() {
        return id;
    }

    public Rule setId(String id) {
        this.id = id;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public Rule setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getAction() {
        return action;
    }

    public Rule setAction(String action) {
        this.action = action;
        return this;
    }

}
