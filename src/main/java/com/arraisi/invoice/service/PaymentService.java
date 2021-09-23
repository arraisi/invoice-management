package com.arraisi.invoice.service;

import com.arraisi.invoice.entity.*;
import com.arraisi.invoice.exception.VirtualAccountAlreadyPaidException;
import com.arraisi.invoice.exception.VirtualAccountNotFoundException;
import com.arraisi.invoice.repository.InvoiceRepository;
import com.arraisi.invoice.repository.VirtualAccountRepository;
import com.arraisi.invoice.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private VirtualAccountRepository virtualAccountRepository;
    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public void pay(PaymentProvider provider,
                    String companyId, String accountNumber,
                    BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {

        //begin tx1
        auditLogService.log("start payment VA " + accountNumber); // suspend tx1 dalam method log

        // 1. cek apakah va ada
        VirtualAccount va = VirtualAccountHelper.getExistingVirtualAccounnt(virtualAccountRepository, provider, companyId, accountNumber);

        // 2. cek apakah va sudah lunas?
        VirtualAccountHelper.checkVaAlreadyPaid(provider, companyId, accountNumber, va);
        VirtualAccountHelper.checkPaymentAmount(va, amount);

        // 3. cek apakah amount pembayaran > nilai tagihan

        // 4. update status va menjadi lunas
        va.setStatusRecord(StatusRecord.INACTIVE);

        // 5. update status invoice menjadi lunas
        Invoice invoice = va.getInvoice();
        invoice.setTotalPayment(invoice.getTotalPayment().add(amount));
        invoice.setPaymentStatus(PaymentStatus.FULL);
        invoiceRepository.save(invoice);

        // 6. insert ke tabel payment
        // 7. notifikasi (next fase)

        // commit tx1
    }

//    private void checkPayment(VirtualAccount va, BigDecimal amount) {
//        if (va.getInvoice().getAmunt().compareTo(amount)) {
//            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] already paid");
//        }
//    }


}
