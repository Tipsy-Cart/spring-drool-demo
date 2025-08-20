package com.demo.beanTwo;

import com.poiji.annotation.ExcelCell;
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

    @ExcelCell(0)
    private String invNum;

    @ExcelCell(1)
    private String itemId;

    @ExcelCell(2)
    private String taxType;

    @ExcelCell(3)
    private String taxRate;

    @ExcelCell(4)
    private String  taxAmount;

}
