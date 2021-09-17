package com.arraisi.invoice.util;

import com.arraisi.invoice.entity.PaymentProvider;
import com.arraisi.invoice.entity.VirtualAccount;
import com.arraisi.invoice.exception.VirtualAccountNotFoundException;
import com.arraisi.invoice.repository.VirtualAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class VirtualAccountHelper {
    public static VirtualAccount cekVaAda(VirtualAccountRepository virtualAccountRepository, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa = virtualAccountRepository.findByPaymentProviderAndCompanyIdAndAccountNumber(provider, companyId, accountNumber);

        if (!optVa.isPresent()) {
            throw new VirtualAccountNotFoundException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] not found");
        }

        VirtualAccount va = optVa.get();
        return va;
    }
}
