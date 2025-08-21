package com.demo.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class JsonHelper {

    public List readList(DocumentContext context, String path){
        List result = context.read(path);
        return result;
    }

    public DocumentContext parse(Object json){
        return JsonPath.parse(json);
    }

    public boolean log(List list){
        System.out.println(list.size());
        return null != list && !list.isEmpty();
    }
}
