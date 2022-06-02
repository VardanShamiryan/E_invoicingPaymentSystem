use e_invoicing_payment_system;
alter table invoice add  unique (invoice_series,invoice_number);
alter table debt add unique (company_id,supplier_id);
alter table debt add unique (company_id,supplier_id);