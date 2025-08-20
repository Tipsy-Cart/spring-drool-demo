package com.demo.beanOne;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tax {

    @ExcelCellName("Invoice Number")
    private String invNum;

    @ExcelCellName("Item Id")
    private String itemId;

    @ExcelCellName("Tax Type")
    private String taxType;

    @ExcelCellName("Tax Rate")
    private String taxRate;

    @ExcelCellName("Tax Amount")
    private String  taxAmount;

}
