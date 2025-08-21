package com.demo.bean;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private String id;
    private String code;
    private String name;
    private Double price;
    private Double discount;
    private Double netPrice;
    private Double quantity;
    private Double itemCharge;
    private Double itemAllowance;
    private Double taxableAmount;
    private Double itemTotalTax;
    private Double itemTotalAmount;
    private List<Tax> taxes;

}
