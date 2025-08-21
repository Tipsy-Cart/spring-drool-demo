package com.demo.beanOne;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
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

    @ExcelCellName("Invoice Number")
    private String invNum;

    @ExcelCellName("Invoice Issue Date")
    private String invDate;

    @ExcelCellName("Invoice Type Code")
    private String invType;

    @ExcelCellName("Invoice Transaction Type Code")
    private String invTxnType;

    @ExcelCellName("Invoice Currency Code")
    private String invCurrencyCode;

    @ExcelCellName("Currency Exchange Rate")
    private String taxAccountingCurrencyCode;

    @ExcelCellName("Tax Accounting Currency Code")
    private String exchangeRate;

    @ExcelCellName("Frequency of Billing")
    private String billingFrequency;

    @ExcelCellName("Invoicing Period Start Date")
    private String startDate;

    @ExcelCellName("Invoicing Period End Date")
    private String endDate;

    @ExcelCellName("Seller Name")
    private String supName;

    @ExcelCellName("Seller TIN")
    private String supTin;

    @ExcelCellName("Seller Legal Registration Identifier Type")
    private String supRegistrationType;

    @ExcelCellName("Seller Registration Authority Name")
    private String supAuthorityName;

    @ExcelCellName("Seller Passport Issuing Country")
    private String supPassportIssuingCountry;

    @ExcelCellName("Seller Electronic Address")
    private String supElectronicAddress;

    @ExcelCellName("Seller Address Line One")
    private String supAddressLineOne;

    @ExcelCellName("Seller City")
    private String supCity;

    @ExcelCellName("Seller Postal Code")
    private String supPostalCode;

    @ExcelCellName("Seller Country SubDivision")
    private String supCountrySubDivision;

    @ExcelCellName("Seller Country Code")
    private String supCountryCode;

    @ExcelCellName("Buyer Name")
    private String buyName;

    @ExcelCellName("Buyer TIN")
    private String buyTin;

    @ExcelCellName("Buyer Legal Registration Identifier Type")
    private String buyRegistrationType;

    @ExcelCellName("Buyer Registration Authority Name")
    private String buyAuthorityName;

    @ExcelCellName("Buyer Passport Issuing Country")
    private String buyPassportIssuingCountry;

    @ExcelCellName("Buyer Electronic Address")
    private String buyElectronicAddress;

    @ExcelCellName("Buyer Address Line One")
    private String buyAddressLineOne;

    @ExcelCellName("Buyer City")
    private String buyCity;

    @ExcelCellName("Buyer Postal Code")
    private String buyPostalCode;

    @ExcelCellName("Buyer Country SubDivision")
    private String buyCountrySubDivision;

    @ExcelCellName("Buyer Country Code")
    private String buyCountryCode;

    private List<LineItem> lineItems;

}
