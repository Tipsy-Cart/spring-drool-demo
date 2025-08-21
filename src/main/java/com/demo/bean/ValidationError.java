package com.demo.bean;

import lombok.Data;

@Data
public class ValidationError {

    private String field;
    private String message;
}
