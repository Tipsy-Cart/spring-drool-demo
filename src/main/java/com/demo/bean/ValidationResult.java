package com.demo.bean;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private final Map<String,String> errors = new HashMap<>();

    public void put(String field, String message){
        errors.put(field,message);
    }

    public Map<String, String> get() {
        return errors;
    }

}
