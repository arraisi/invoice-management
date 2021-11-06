package com.arraisi.invoice.service;

import com.arraisi.invoice.entity.Invoice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceServiceTests {
    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void createInvoice() {
        Invoice invoice = invoiceService.createInvoice();
    }
}
