package com.arraisi.invoice.service;

import com.arraisi.invoice.entity.PaymentProvider;
import com.arraisi.invoice.entity.VirtualAccount;
import com.arraisi.invoice.exception.VirtualAccountAlreadyPaidException;
import com.arraisi.invoice.exception.VirtualAccountNotFoundException;
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

    public void pay(PaymentProvider provider,
                    String companyId, String accountNumber,
                    BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        // 1. cek apakah va ada
        VirtualAccount va = VirtualAccountHelper.getExistingVirtualAccounnt(virtualAccountRepository, provider, companyId, accountNumber);
        // 2. cek apakah va sudah lunas?
        VirtualAccountHelper.checkVaAlreadyPaid(provider, companyId, accountNumber, va);

        // 3. cek apakah amount pembayaran > nilai tagihan
        // 4. update status va menjadi lunas
        // 5. update status invoice menjadi lunas
        // 6. insert ke tabel payment
        // 7. notifikasi (next fase)
    }

//    private void checkPayment(VirtualAccount va, BigDecimal amount) {
//        if (va.getInvoice().getAmunt().compareTo(amount)) {
//            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] already paid");
//        }
//    }


}
