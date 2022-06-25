# **Overview**

This project is to automatically import invoices from E-invoicing programm
and send payment orders to online banking.

# How to use
First run sql next run bank system app on port 8080 next 
run invoicing payment system on port 8081

**Functional specification of the project**
**Company**
    tin (as login)
    password
    companyName
    compAccountNumber
    phoneNumber
    role
**Invoice**
    invoiceNumber
    deliveryDate
    suppAccountNumber
    totalPrice
    submissionDate
    paymentStatus
    invoiceDebt
    adjustmentInvoiceNumber
    company
    supplier
**Supplier**
    supplierTin
    supplierName
    suppAccountNumber
    invoices
    debts
**PaymentOrder**
    paymentAmount
    currentPaymentDate
    invoice

![img.png](img.png)