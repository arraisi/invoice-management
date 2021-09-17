package com.arraisi.invoice.service;

import com.arraisi.invoice.entity.PaymentProvider;
import com.arraisi.invoice.entity.VirtualAccount;
import com.arraisi.invoice.exception.VirtualAccountAlreadyPaidException;
import com.arraisi.invoice.exception.VirtualAccountNotFoundException;
import com.arraisi.invoice.repository.VirtualAccountRepository;
import com.arraisi.invoice.util.VirtualAccountHelper;
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
        VirtualAccount va = VirtualAccountHelper.cekVaAda(virtualAccountRepository, provider, companyId, accountNumber);
        // 2. cek apakah va sudah lunas?
        cekVaLunas(provider, companyId, accountNumber, va);

        // 3. cek apakah amount pembayaran > nilai tagihan
        // 4. update status va menjadi lunas
        // 5. update status invoice menjadi lunas
        // 6. insert ke tabel payment
        // 7. notifikasi (next fase)
    }

    private void cekVaLunas(PaymentProvider provider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] already paid");
        }
    }


}
