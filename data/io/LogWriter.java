package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;

public abstract class LogWriter implements FileWriter{

  private TaxpayerManager theManager;
  
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  

  protected ArrayList<String> constantsAboutTaxpayer = new ArrayList<String>();
  
  protected abstract void printInFile(PrintWriter outputStream, ArrayList<String> constantsMatrix, ArrayList<String> taxpayerInformation);
   
  public LogWriter(TaxpayerManager newTaxpayerManager) {
      theManager = newTaxpayerManager;
  }
  
  @Override
  public void generateFile(int taxRegistrationNumber, String fileFormat) throws IOException {
    ArrayList<String> taxpayerInformation = new ArrayList<String>();
    taxpayerInformation=extractTaxpayerInfoInList(taxRegistrationNumber, taxpayerInformation);
    generateOutputStream(fileFormat, taxRegistrationNumber,taxpayerInformation, constantsAboutTaxpayer);
  }

  protected void generateOutputStream(String fileFormat, int taxRegistrationNumber,
      ArrayList<String> taxpayerInformation, ArrayList<String> constantsAboutTaxpayer)
      throws IOException {
    PrintWriter outputStream = new PrintWriter(
      new java.io.FileWriter(taxRegistrationNumber + "_LOG."+fileFormat));
    printInFile(outputStream, constantsAboutTaxpayer, taxpayerInformation);
    outputStream.close();
  }
  
  protected ArrayList<String> extractTaxpayerInfoInList(int taxRegistrationNumber,
      ArrayList<String> taxpayerInformation) {
    taxpayerInformation.add(theManager.getTaxpayerName(taxRegistrationNumber));
    taxpayerInformation.add(Integer.toString(taxRegistrationNumber));
    taxpayerInformation.add(theManager.getTaxpayerIncome(taxRegistrationNumber));
    taxpayerInformation.add(Double.toString(theManager.getTaxpayerBasicTax(taxRegistrationNumber)));
    taxpayerInformation.add(Double.toString(theManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber)));
    taxpayerInformation.add(Double.toString(theManager.getTaxpayerTotalTax(taxRegistrationNumber)));
    taxpayerInformation.add(Integer.toString(theManager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber)));
    taxpayerInformation.add(Float.toString(theManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)));
    taxpayerInformation.add(Float.toString(theManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC)));
    taxpayerInformation.add(Float.toString(theManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL)));
    taxpayerInformation.add(Float.toString(theManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH)));
    taxpayerInformation.add(Float.toString(theManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER)));
    return taxpayerInformation;
  }


}
