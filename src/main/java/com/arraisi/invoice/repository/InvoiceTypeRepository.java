package com.arraisi.invoice.repository;

import com.arraisi.invoice.entity.InvoiceType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceTypeRepository extends PagingAndSortingRepository<InvoiceType, String> {
}
