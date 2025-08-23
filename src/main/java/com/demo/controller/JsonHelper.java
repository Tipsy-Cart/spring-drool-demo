package com.demo.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class JsonHelper {

    public List readList(DocumentContext context, String path){
        List result = context.read(path);
        return result;
    }

    public Double readDouble(DocumentContext context, String path){
        Double value = context.read(path, Double.class);
        return null != value ? value : 0.0;
    }

    public DocumentContext parse(Object json){
        return JsonPath.parse(json);
    }

    public Double sum(List<Object> list) {
        return list.stream().map(Object::toString).map(Double::valueOf).reduce(Double::sum).orElse(0.0);
    }

    public boolean log(String msg){
        System.out.println(msg);
        return true;
    }
}
