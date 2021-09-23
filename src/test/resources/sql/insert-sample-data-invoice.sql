

-- payment provider
insert into payment_provider(id, code, name, created, created_by, status_record, updated, updated_by)
values ('bankbca', 'BCA', 'Bank BCA', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User');
insert into payment_provider(id, code, name, created, created_by, status_record, updated, updated_by)
values ('bankbni', 'BNI', 'Bank BNI', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User');
insert into payment_provider(id, code, name, created, created_by, status_record, updated, updated_by)
values ('bankbni', 'BNI', 'Bank BNI', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User');
insert into payment_provider(id, code, name, created, created_by, status_record, updated, updated_by)
values ('bankcimb', 'CIMB', 'Bank CIMB', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User');

-- virtual account configuration
-- insert into virtual_account_configuration(id, code, name, id_payment_provider, id_bank, transaction_fee_flat,
--                                           transaction_fee_percentage, status_record, created, created_by)
-- values ('va-bni', 'VA-BNI', 'Virtual Account BNI', 'bankbni', 'bni001', 2000, 0.0, '0123');

-- invoice type
insert into invoice_type(id, created, created_by, status_record, updated, updated_by, code, name, payment_type)
values ('registrasi', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User', 'REG-001',
        'REG-001', 'Biaya Pendaftaran');
insert into invoice_type(id, created, created_by, status_record, updated, updated_by, code, name, payment_type)
values ('donasi', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User', 'DONASI-001',
        'Biaya Pendaftaran', 'Sumbangan Sukarela');
insert into invoice_type(id, created, created_by, status_record, updated, updated_by, code, name, payment_type)
values ('uang-muka', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User', 'DP-001',
        'Uang Muka', 'INSTALLMENT');

-- invoice type configuration
insert into invoice_type_configuration(id_invoice_type, id_virtual_account_configuration)
values ('registrasi', 'va-bni');

insert into invoice_type_configuration(id_invoice_type, id_virtual_account_configuration)
values ('registrasi', 'va-gopay');

insert into invoice_type_configuration(id_invoice_type, id_virtual_account_configuration)
values ('donasi', 'va-gopay');

insert into invoice_type_configuration(id_invoice_type, id_virtual_account_configuration)
values ('uang-muka', 'va-bni');

--customer
insert into customer(id, created, created_by, status_record, updated, updated_by, code, name, email,
                     mobile_phone)
values ('c001', current_timestamp, 'Test User', 'ACTIVE', current_timestamp, 'Test User', 'CUST-001',
        'Customer 001', 'c001@mail.com');