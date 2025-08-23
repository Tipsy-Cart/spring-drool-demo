package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        String path = "items[%d].id.tax[%d]";
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(2);
        System.out.println(String.format(path,indices.toArray()));
    }
}
