use e_invoicing_payment_system;

--     after creating tables

alter table debt add unique (company_id,supplier_id);
INSERT INTO role (role_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO role (role_id, role) VALUES (2,'ROLE_ADMIN');

