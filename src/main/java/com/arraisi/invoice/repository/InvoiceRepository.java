package com.arraisi.invoice.repository;

import com.arraisi.invoice.entity.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, String> {
}
