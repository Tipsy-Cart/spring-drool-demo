package com.demo.beanTwo;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @ExcelCell(0)
    private String invNum;

    @ExcelCell(1)
    private String invDate;

    @ExcelCell(2)
    private String invType;

    @ExcelCell(3)
    private String invTxnType;

    @ExcelCell(4)
    private String invCurrencyCode;

    @ExcelCell(5)
    private String exchangeRate;

    @ExcelCell(6)
    private String taxAccountingCurrencyCode;

    @ExcelCell(7)
    private String billingFrequency;

    @ExcelCell(8)
    private String startDate;

    @ExcelCell(9)
    private String endDate;

    @ExcelCell(10)
    private String supName;

    @ExcelCell(11)
    private String supTin;

    @ExcelCell(12)
    private String supRegistrationType;

    @ExcelCell(13)
    private String supAuthorityName;

    @ExcelCell(14)
    private String supPassportIssuingCountry;

    @ExcelCell(15)
    private String supElectronicAddress;

    @ExcelCell(16)
    private String supAddressLineOne;

    @ExcelCell(17)
    private String supCity;

    @ExcelCell(18)
    private String supPostalCode;

    @ExcelCell(19)
    private String supCountrySubDivision;

    @ExcelCell(20)
    private String supCountryCode;

    @ExcelCell(21)
    private String buyName;

    @ExcelCell(22)
    private String buyTin;

    @ExcelCell(23)
    private String buyRegistrationType;

    @ExcelCell(24)
    private String buyAuthorityName;

    @ExcelCell(25)
    private String buyPassportIssuingCountry;

    @ExcelCell(26)
    private String buyElectronicAddress;

    @ExcelCell(27)
    private String buyAddressLineOne;

    @ExcelCell(28)
    private String buyCity;

    @ExcelCell(29)
    private String buyPostalCode;

    @ExcelCell(30)
    private String buyCountrySubDivision;

    @ExcelCell(31)
    private String buyCountryCode;

    private List<LineItem> lineItems;

    private Map<String,String> errors;

}
