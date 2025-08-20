package com.demo.beanOne;

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
public class LineItem {

    @ExcelCellName("Invoice Number")
    private String invNum;

    @ExcelCellName("Id")
    private String itemId;

    @ExcelCellName("Name")
    private String name;

    @ExcelCellName("Description")
    private String description;

    @ExcelCellName("Price")
    private String price;

    @ExcelCellName("Discount")
    private String discount;

    @ExcelCellName("Net Price")
    private String netPrice;

    @ExcelCellName("Unit of Measurement")
    private String unitOfMeasurement;

    @ExcelCellName("Quantity")
    private String noOfUnits;

    @ExcelCellName("Total Amount")
    private String totalAmount;

    private List<Tax> taxes;

}
