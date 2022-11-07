package incometaxcalculator.data.io;

import java.io.IOException;
import java.util.HashMap;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;

public interface FileWriter {
 
  //private TaxpayerManager theTaxpayerManager;
  
  //public FileWriter(TaxpayerManager newTaxpayerManager) {
  //  theTaxpayerManager = newTaxpayerManager;
  //}
  
  public void generateFile(int taxRegistrationNumber,String fileFormat) throws IOException, WrongFileFormatException;

  //public TaxpayerManager getTaxpayerManager() {
    //TaxpayerManager manager = new TaxpayerManager();
   // return theTaxpayerManager;
  //}
  
  /*
   * public Taxpayer getTaxpayer(int taxRegistrationNumber) { TaxpayerManager manager = new
   * TaxpayerManager(); return manager.getTaxpayer(taxRegistrationNumber); }
   * 
   * public String getTaxpayerName(int taxRegistrationNumber) { TaxpayerManager manager = new
   * TaxpayerManager(); return manager.getTaxpayerName(taxRegistrationNumber); }
   * 
   * public String getTaxpayerIncome(int taxRegistrationNumber) { TaxpayerManager manager = new
   * TaxpayerManager(); return manager.getTaxpayerIncome(taxRegistrationNumber); }
   * 
   * public String getTaxpayerStatus(int taxRegistrationNumber) { TaxpayerManager manager = new
   * TaxpayerManager(); return manager.getTaxpayerStatus(taxRegistrationNumber); }
   * 
   * public double getTaxpayerVariationTaxOnReceipts(int taxRegistrationNumber) { TaxpayerManager
   * manager = new TaxpayerManager(); return
   * manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber); }
   * 
   * public int getTaxpayerTotalReceiptsGathered(int taxRegistrationNumber) { TaxpayerManager
   * manager = new TaxpayerManager(); return
   * manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber); }
   * 
   * public float getTaxpayerAmountOfReceiptKind(int taxRegistrationNumber, short kind) {
   * TaxpayerManager manager = new TaxpayerManager(); return
   * manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, kind); }
   * 
   * public double getTaxpayerTotalTax(int taxRegistrationNumber) { TaxpayerManager manager = new
   * TaxpayerManager(); return manager.getTaxpayerTotalTax(taxRegistrationNumber); }
   * 
   * public double getTaxpayerBasicTax(int taxRegistrationNumber) { TaxpayerManager manager = new
   * TaxpayerManager(); return manager.getTaxpayerBasicTax(taxRegistrationNumber); }
   * 
   * public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) { TaxpayerManager
   * manager = new TaxpayerManager(); return manager.getReceiptHashMap(taxRegistrationNumber); }
   */
/*
  public Taxpayer getTaxpayer(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayer(taxRegistrationNumber);
  }

  public String getTaxpayerName(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerName(taxRegistrationNumber);
  }

  public String getTaxpayerIncome(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerIncome(taxRegistrationNumber);
  }

  public String getTaxpayerStatus(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerStatus(taxRegistrationNumber);
  }

  public double getTaxpayerVariationTaxOnReceipts(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber);
  }

  public int getTaxpayerTotalReceiptsGathered(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber);
  }

  public float getTaxpayerAmountOfReceiptKind(int taxRegistrationNumber, short kind) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, kind);
  }

  public double getTaxpayerTotalTax(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerTotalTax(taxRegistrationNumber);
  }

  public double getTaxpayerBasicTax(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getTaxpayerBasicTax(taxRegistrationNumber);
  }

  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    //TaxpayerManager manager = new TaxpayerManager();
    return theTaxpayerManager.getReceiptHashMap(taxRegistrationNumber);
  }
  //moved to XML-TXTInfoWriter
  /*public int getReceiptId(Receipt receipt) {
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
  }*/

//moved to XML-TXTInfoWriter
  /*
   * public String getCompanyName(Receipt receipt) { return receipt.getCompany().getName(); }
   * 
   * public String getCompanyCountry(Receipt receipt) { return receipt.getCompany().getCountry(); }
   * 
   * public String getCompanyCity(Receipt receipt) { return receipt.getCompany().getCity(); }
   * 
   * public String getCompanyStreet(Receipt receipt) { return receipt.getCompany().getStreet(); }
   * 
   * public int getCompanyNumber(Receipt receipt) { return receipt.getCompany().getNumber(); }
   */

}