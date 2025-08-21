package com.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private String invNum;
    private String invDate;
    private String invType;
    private String supRegistrationType;
    private String supPassportIssuingCountry;
    private List<Item> items;

}
