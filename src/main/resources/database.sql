use e_invoicing_payment_system;
--     after creating tables
alter table debt add unique (company_id,supplier_id);

-- then change application properties to update