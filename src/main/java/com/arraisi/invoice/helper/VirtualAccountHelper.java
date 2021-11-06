package com.arraisi.invoice.helper;

import com.arraisi.invoice.entity.PaymentProvider;
import com.arraisi.invoice.entity.VirtualAccount;
import com.arraisi.invoice.exception.VirtualAccountAlreadyPaidException;
import com.arraisi.invoice.exception.VirtualAccountNotFoundException;
import com.arraisi.invoice.repository.VirtualAccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class VirtualAccountHelper {
    public static VirtualAccount getExistingVirtualAccounnt(VirtualAccountRepository virtualAccountRepository, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa = virtualAccountRepository.findByPaymentProviderAndCompanyIdAndAccountNumber(provider, companyId, accountNumber);

        if (!optVa.isPresent()) {
            throw new VirtualAccountNotFoundException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] not found");
        }

        VirtualAccount va = optVa.get();
        return va;
    }

    public static void checkVaAlreadyPaid(PaymentProvider provider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] already paid");
        }
    }

    public static void checkPaymentAmount(VirtualAccount va, BigDecimal amount) {

    }
}
