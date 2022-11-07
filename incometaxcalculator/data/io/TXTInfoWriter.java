package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class TXTInfoWriter implements FileWriter {

  private TaxpayerManager theManager;

  //////////////////////////////////////////////////
  public int getReceiptId(Receipt receipt) {
    return receipt.getId();
  }

  public String getReceiptIssueDate(Receipt receipt) {
    return receipt.getIssueDate();
  }

  public String getReceiptKind(Receipt receipt) {
    return receipt.getKind();
  }

  public float getReceiptAmount(Receipt receipt) {
    return receipt.getAmount();
  }
  /////////////////////////////////////////////////
  public String getCompanyName(Receipt receipt) {
    return receipt.getCompany().getName();
  }

  public String getCompanyCountry(Receipt receipt) {
    return receipt.getCompany().getCountry();
  }

  public String getCompanyCity(Receipt receipt) {
    return receipt.getCompany().getCity();
  }

  public String getCompanyStreet(Receipt receipt) {
    return receipt.getCompany().getStreet();
  }

  public int getCompanyNumber(Receipt receipt) {
    return receipt.getCompany().getNumber();
  }
  
  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    return theManager.getReceiptHashMap(taxRegistrationNumber);
  }
  
  public void generateFile(int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
    outputStream.println("Name: " + theManager.getTaxpayerName(taxRegistrationNumber));
    outputStream.println("AFM: " + taxRegistrationNumber);
    outputStream.println("Status: " + theManager.getTaxpayerStatus(taxRegistrationNumber));
    outputStream.println("Income: " + theManager.getTaxpayerIncome(taxRegistrationNumber));
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println("Receipts:");
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }

  private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {

    HashMap<Integer, Receipt> receiptsHashMap = getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println("Receipt ID: " + getReceiptId(receipt));
      outputStream.println("Date: " + getReceiptIssueDate(receipt));
      outputStream.println("Kind: " + getReceiptKind(receipt));
      outputStream.println("Amount: " + getReceiptAmount(receipt));
      outputStream.println("Company: " + getCompanyName(receipt));
      outputStream.println("Country: " + getCompanyCountry(receipt));
      outputStream.println("City: " + getCompanyCity(receipt));
      outputStream.println("Street: " + getCompanyStreet(receipt));
      outputStream.println("Number: " + getCompanyNumber(receipt));
      outputStream.println();
    }
  }

}