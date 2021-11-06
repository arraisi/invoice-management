package com.arraisi.invoice.repository;

import com.arraisi.invoice.entity.PaymentProvider;
import com.arraisi.invoice.entity.VirtualAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VirtualAccountRepository extends PagingAndSortingRepository<VirtualAccount, String> {
    Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(PaymentProvider provider, String companyId, String accountNumber);
}
