package com.arraisi.invoice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditLogService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String message) {
        // suspend trx yang sedang berjalan (tx1)
        // start tx baru (tx2)
        // yang ada disini akan dijalankan dalam trx baru (tx2)
        // commit/rollback tx2
        // resume tx1
    }
}
