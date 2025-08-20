package com.demo.controller;


import com.demo.beanOne.ValidationResult;
import com.demo.beanTwo.Invoice;
import com.demo.beanTwo.LineItem;
import com.demo.beanTwo.Tax;
import com.demo.config.KieBaseManager;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class TestControllerTwo {

    PoijiOptions invoiceOptions = PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetIndex(0)
            .skip(2)
            .build();

    PoijiOptions itemOptions = PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetIndex(1)
            .skip(1)
            .build();

    PoijiOptions taxOptions = PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetIndex(2)
            .skip(1)
            .build();

    @PostMapping(value = "/upload/two", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Invoice> transform(@RequestPart("file") MultipartFile file) throws IOException {
            List<Invoice> invoices = Poiji.fromExcel(file.getInputStream(), PoijiExcelType.XLSX, Invoice.class, invoiceOptions);
            List<LineItem> lineItems = Poiji.fromExcel(file.getInputStream(), PoijiExcelType.XLSX, LineItem.class, itemOptions);
            List<Tax> taxes = Poiji.fromExcel(file.getInputStream(), PoijiExcelType.XLSX, Tax.class, taxOptions);
            return invoices.stream().map(invoice -> addLineItems(invoice, lineItems, taxes)).toList();
    }

    private Invoice addLineItems(Invoice invoice, List<LineItem> lineItems, List<Tax> taxes) {
        List<LineItem> invoiceLineItems = lineItems.stream().filter(lineItem -> lineItem.getInvNum().equals(invoice.getInvNum())).map(lineItem -> addTaxes(lineItem, taxes)).toList();
        invoice.setLineItems(invoiceLineItems);
        return invoice;
    }

    private LineItem addTaxes(LineItem lineItem, List<Tax> taxes) {
        List<Tax> itemTaxes = taxes.stream().filter(tax -> tax.getInvNum().equals(lineItem.getInvNum()) && tax.getItemId().equals(lineItem.getItemId())).toList();
        lineItem.setTaxes(itemTaxes);
        return lineItem;
    }


}
