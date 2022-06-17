package com.example.e_invoicingpaymentsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportedXmlDto {
    // General
    private String invoiceNumber;   // /ExportedData/SignedData/Data/SignableData/GeneralInfo/InvoiceNumber/Number
    private String invoiceSeries;   // /ExportedData/SignedData/Data/SignableData/GeneralInfo/InvoiceNumber/Series
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate; // /ExportedData/SignedData/Data/SignableData/GeneralInfo/SupplyDate
    //Supplier Info
    private String supplierTin;       // /ExportedData/SignedData/Data/SignableData/SupplierInfo/Taxpayer/TIN
    private String supplierName;      // /ExportedData/SignedData/Data/SignableData/SupplierInfo/Taxpayer/Name
    private String suppAccountNumber; // /ExportedData/SignedData/Data/SignableData/SupplierInfo/Taxpayer/BankAccount/BankAccountNumber
    //BuyerInfo
    private String buyerTin;          // /ExportedData/SignedData/Data/SignableData/BuyerInfo/Taxpayer/TIN

    //Goods Info
    private Double totalPrice;        // /ExportedData/SignedData/Data/SignableData/GoodsInfo/Total/TotalPrice

    //InvoiceMetadata
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate submissionDate; // /ExportedData/SignedData/InvoiceMetadata/SubmissionDate

    private String parentInvoiceNumber;
}
