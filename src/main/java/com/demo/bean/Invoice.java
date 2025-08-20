package com.demo.bean;

import com.demo.beanTwo.LineItem;
import com.poiji.annotation.ExcelCell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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

}
