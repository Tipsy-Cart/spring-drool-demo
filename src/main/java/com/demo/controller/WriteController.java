package com.demo.controller;

import com.demo.bean.CellIndexMap;
import com.demo.beanTwo.Invoice;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WriteController {

    @PostMapping("/write")
    public String validate(@RequestBody List<Invoice> invoices) throws IOException {
        List<Invoice> invList = invoices.stream().map(this::addError).toList();
        File template = new File("./tmp/" + "Invoice_Template_One.xlsx");
        File tmpOutput = new File("./tmp/" + "temp.xlsx");
        File finalOutput = new File("./tmp/" + "final.xlsx");
        Context context = new Context();
        context.putVar("invoices", invList);
        FileInputStream in = new FileInputStream(template);
        FileOutputStream out = new FileOutputStream(tmpOutput);
        JxlsHelper.getInstance().processTemplate(in, out, context);
        in.close();
        out.close();
        FileInputStream in1 = new FileInputStream(tmpOutput);
        FileOutputStream out1 = new FileOutputStream(finalOutput);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in1);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,31));
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,9));
        sheet.addMergedRegion(new CellRangeAddress(1,1,10,20));
        sheet.addMergedRegion(new CellRangeAddress(1,1,21,31));
        int headerRow = 3;
        for(int i = 0; i < invList.size(); i++) {
            Invoice invoice = invList.get(i);
            Map<String, String> errors = invoice.getErrors();
            XSSFRow row = sheet.getRow(i + headerRow);
            errors.forEach((key, value) -> {
                CellIndexMap cellIndexMap = CellIndexMap.valueOf(key);
                int cellIndex = cellIndexMap.getCellIndex();
                XSSFCell cell = row.getCell(cellIndex);

                CellStyle style = xssfWorkbook.createCellStyle();
                Font redFont = xssfWorkbook.createFont();
                redFont.setColor(IndexedColors.RED.getIndex());
                style.setFont(redFont);
                cell.setCellStyle(style);

                Drawing<?> drawing = sheet.createDrawingPatriarch();
                CreationHelper factory = xssfWorkbook.getCreationHelper();

                ClientAnchor anchor = factory.createClientAnchor();
                anchor.setCol1(cell.getColumnIndex());
                anchor.setCol2(cell.getColumnIndex() + 2); // width of comment box
                anchor.setRow1(row.getRowNum());
                anchor.setRow2(row.getRowNum() + 3);       // height of comment box
                Comment comment = drawing.createCellComment(anchor);
                RichTextString str = factory.createRichTextString(value);
                comment.setString(str);
                comment.setAuthor("Prashant Bharti");
                cell.setCellComment(comment);
            });
        }
        xssfWorkbook.write(out1);
        in1.close();
        out1.close();
        return "Success";
    }

    private Invoice addError(Invoice invoice) {
        Map<String, String> errors = new HashMap<>();
        errors.put(CellIndexMap.invType.name(), "Invoice Type is Mandatory");
        errors.put(CellIndexMap.supTin.name(), "Invalid Supplier TIN");
        errors.put(CellIndexMap.buyerElectronicAddress.name(), "Max 20 characters allowed");
        invoice.setErrors(errors);
        return invoice;
    }

}
