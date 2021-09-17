package com.arraisi.invoice.service;

import com.arraisi.invoice.exception.VirtualAccountAlreadyPaidException;
import com.arraisi.invoice.exception.VirtualAccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTests {
    @Autowired
    private PaymentService paymentService;

    @Test
    public void testPay() throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        paymentService.pay(null, null, null, null, null);
    }
}
