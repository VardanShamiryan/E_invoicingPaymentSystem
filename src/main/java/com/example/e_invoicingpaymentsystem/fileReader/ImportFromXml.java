package com.example.e_invoicingpaymentsystem.fileReader;

import com.example.e_invoicingpaymentsystem.dto.ImportedXmlDto;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImportFromXml {
    public static List<ImportedXmlDto> importFromXml(String path) throws Exception {
        List<ImportedXmlDto> importedXmlDtoList = new ArrayList<>();
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);
            document.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            // each invoice
            String expression = " /ExportedData/SignedData";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    ImportedXmlDto importXmlDto = new ImportedXmlDto();

                    if (eElement.getElementsByTagName("ParentInvoiceNumber").getLength() != 0) {

                        importXmlDto.setParentInvoiceNumber(eElement.getElementsByTagName("ParentInvoiceNumber").item(0).getTextContent());
                        importXmlDto.setInvoiceNumber(eElement.getElementsByTagName("Number").item(0).getTextContent());
                        importXmlDto.setInvoiceSeries(eElement.getElementsByTagName("Series").item(0).getTextContent());
                        importXmlDto.setDeliveryDate(LocalDate.parse(eElement.
                                getElementsByTagName("SupplyDate").item(0).getTextContent().substring(0, 10)));
                        importXmlDto.setSupplierTin(eElement.getElementsByTagName("TIN").item(0).getTextContent());
                        importXmlDto.setSupplierName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                        importXmlDto.setBuyerTin(eElement.getElementsByTagName("TIN").item(1).getTextContent());
                        int length = eElement.getElementsByTagName("TotalPrice").getLength();
                        importXmlDto.setTotalPrice(Double.parseDouble(eElement.getElementsByTagName("TotalPrice").item(length - 1).getTextContent()));
                        importedXmlDtoList.add(importXmlDto);


                    } else {

                        importXmlDto.setInvoiceNumber(eElement.getElementsByTagName("Number").item(0).getTextContent());
                        importXmlDto.setInvoiceSeries(eElement.getElementsByTagName("Series").item(0).getTextContent());
                        importXmlDto.setDeliveryDate(LocalDate.parse(eElement.
                                getElementsByTagName("SupplyDate").item(0).getTextContent().substring(0, 10)));
                        importXmlDto.setSupplierTin(eElement.getElementsByTagName("TIN").item(0).getTextContent());
                        importXmlDto.setSupplierName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                        importXmlDto.setSuppAccountNumber(eElement.getElementsByTagName("BankAccountNumber").item(0).getTextContent());
                        importXmlDto.setBuyerTin(eElement.getElementsByTagName("TIN").item(1).getTextContent());
                        int length = eElement.getElementsByTagName("TotalPrice").getLength();
                        importXmlDto.setTotalPrice(Double.parseDouble(eElement.getElementsByTagName("TotalPrice").item(length - 1).getTextContent()));
                        importXmlDto.setSubmissionDate(LocalDate.parse(eElement.
                                getElementsByTagName("SubmissionDate").item(0).getTextContent().substring(0, 10)));
                        importedXmlDtoList.add(importXmlDto);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException |
                 XPathExpressionException e) {
            throw new Exception();
        }
        return importedXmlDtoList;

    }
}
