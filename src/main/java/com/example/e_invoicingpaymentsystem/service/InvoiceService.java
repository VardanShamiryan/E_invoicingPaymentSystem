package com.example.e_invoicingpaymentsystem.service;

import com.example.e_invoicingpaymentsystem.dto.CompanyDto;
import com.example.e_invoicingpaymentsystem.dto.ImportedXmlDto;
import com.example.e_invoicingpaymentsystem.fileReader.ImportFromXml;
import com.example.e_invoicingpaymentsystem.mapper.FromImportedXmlDtoToInvoice;
import com.example.e_invoicingpaymentsystem.mapper.FromImportedXmlDtoToSupplier;
import com.example.e_invoicingpaymentsystem.model.Company;
import com.example.e_invoicingpaymentsystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

import static com.example.e_invoicingpaymentsystem.fileReader.ImportFromXml.importFromXml;

@Service
public class InvoiceService {
    ImportFromXml importFromXml;
    FromImportedXmlDtoToSupplier supplierMapper;
    FromImportedXmlDtoToInvoice invoiceMapper;

    @Autowired
    public InvoiceService(ImportFromXml importFromXml,
                          FromImportedXmlDtoToSupplier supplierMapper,
                          FromImportedXmlDtoToInvoice invoiceMapper) {
        this.importFromXml = importFromXml;
        this.supplierMapper = supplierMapper;
        this.invoiceMapper = invoiceMapper;
    }

    public ResponseEntity<?> importInvoice(String path) {
        List<ImportedXmlDto> importedXmlDtoList;
        try {
        importedXmlDtoList = importFromXml(path);
         } catch (Exception e) {
           return new ResponseEntity<>("Path doesn't exist!", HttpStatus.BAD_REQUEST);
        }
        for (ImportedXmlDto importedXmlDto : importedXmlDtoList) {
            supplierMapper.fromImportedXmlDtoToSupplier(importedXmlDto);
            invoiceMapper.fromImportedXmlDtoToInvoice(importedXmlDto);
        }
        return new ResponseEntity<>("Invoices imported.", HttpStatus.OK);
    }

}
