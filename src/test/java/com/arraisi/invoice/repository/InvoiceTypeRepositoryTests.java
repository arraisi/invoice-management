package com.arraisi.invoice.repository;

import com.arraisi.invoice.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"/sql/delete-invoice-type.sql", "/sql/insert-invoice-type.sql"})
public class InvoiceTypeRepositoryTests {
    @Autowired
    InvoiceTypeRepository invoiceTypeRepository;

    @Test
    public void testInsertInvoiceType() throws Exception {
        InvoiceType invoiceType = new InvoiceType();

        invoiceType.setCode("IT-001");
        invoiceType.setName("Invoice Type Test");

        Assertions.assertNull(invoiceType.getId());

        invoiceTypeRepository.save(invoiceType);

        Assertions.assertNotNull(invoiceType.getId());

        System.out.println("ID: " + invoiceType.getId());
        System.out.println("Create Time: " + invoiceType.getCreated());
        System.out.println("Create By: " + invoiceType.getCreatedBy());
        System.out.println("Update Time: " + invoiceType.getUpdated());
        System.out.println("Update By: " + invoiceType.getUpdatedBy());

        Assertions.assertEquals(invoiceType.getCreated(), invoiceType.getUpdated());

        Thread.sleep(1000);

        invoiceType.setName("Invoice Type Updated");
        invoiceType = invoiceTypeRepository.save(invoiceType);

        System.out.println("Create Time: " + invoiceType.getCreated());
        System.out.println("Update Time: " + invoiceType.getUpdated());
        Assertions.assertNotEquals(invoiceType.getCreated(), invoiceType.getUpdated());

    }

    @Test
    public void testQuerySoftDelete() {
        Long countInvoiceType = invoiceTypeRepository.count();
        System.out.println("Jumlah record: " + countInvoiceType);
        Assertions.assertEquals(1, countInvoiceType);
    }

}
