package com.arraisi.invoice.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@SQLDelete(sql = "UPDATE invoice SET status_record = 'INACTIVE' WHERE ID=?")
@Where(clause = "status_record = 'ACTIVE'")
public class Invoice extends BaseEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_invoice_type")
    private InvoiceType invoiceType;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String invoiceNumber;

    @NotNull
    private Boolean paid = false;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    @Min(0)
    private BigDecimal totalPayment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
