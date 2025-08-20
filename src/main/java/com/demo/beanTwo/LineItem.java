package com.demo.beanTwo;

import com.poiji.annotation.ExcelCell;
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

    @ExcelCell(0)
    private String invNum;

    @ExcelCell(1)
    private String itemId;

    @ExcelCell(2)
    private String name;

    @ExcelCell(3)
    private String description;

    @ExcelCell(4)
    private String price;

    @ExcelCell(5)
    private String discount;

    @ExcelCell(6)
    private String netPrice;

    @ExcelCell(7)
    private String unitOfMeasurement;

    @ExcelCell(8)
    private String noOfUnits;

    @ExcelCell(9)
    private String totalAmount;

    private List<Tax> taxes;

}
