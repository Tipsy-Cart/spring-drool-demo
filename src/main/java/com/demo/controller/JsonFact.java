package com.demo.controller;

import com.jayway.jsonpath.DocumentContext;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JsonFact {

    private String type;
    private int index;
    private DocumentContext context;

}
