package com.demo.controller;

import com.demo.beanOne.Invoice;
import com.demo.beanOne.LineItem;
import com.demo.beanOne.Tax;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class TestControllerOne {

    PoijiOptions invoiceOptions = PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetName("Documents")
            .build();

    PoijiOptions itemOptions = PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetName("LineItem")
            .build();

    PoijiOptions taxOptions = PoijiOptions.PoijiOptionsBuilder.settings()
            .sheetName("Tax")
            .build();

    @PostMapping(value = "/upload/one", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
