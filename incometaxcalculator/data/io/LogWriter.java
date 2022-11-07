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
  
  private String[] constantsTXT = {"Name: ","AFM: ","Income: ","Basic Tax: ","Tax Increase: ","Tax Decrease: ", "Total Tax: ","TotalReceiptsGathered: ","Entertainment: ", "Basic: ","Travel: ","Health: ","Other: " };
  private String[] constantsXML = {"<Name> "," </Name>","<AFM> "," </AFM>","<Income> "," </Income>","<Basic Tax> "," </Basic Tax>","<Tax Increase> "," </Tax Increase>",
                                    "<Tax Decrease> "," </Tax Decrease>",
                                    "<Total Tax> ", " </Total Tax>",
                                    "<TotalReceiptsGathered> "," </TotalReceiptsGathered>",
                                    "<Entertainment> ", " </Entertainment>",
                                    "<Basic> "," </Basic>",
                                    "<Travel> "," </Travel>",
                                    "<Travel> "," </Travel>",
                                    "<Health> "," </Health>",
                                    "<Other> "," </Other>"};
  private int flagIncrease;
  
  protected abstract void makeFile(PrintWriter outputStream, int flagIncrease, String[] constantsMatrix, ArrayList<String> taxpayerInformation);
   
  @Override
  public void generateFile(int taxRegistrationNumber, String fileFormat) throws IOException, WrongFileFormatException {
    ArrayList<String> taxpayerInformation = new ArrayList<String>();
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
    
    double taxpayerVariationTaxOnReceipts = Double.parseDouble(taxpayerInformation.get(3)); 
    if (taxpayerVariationTaxOnReceipts > 0) {
      flagIncrease=1;
    }else {
      flagIncrease=0;
    }
    if (fileFormat.equals("txt")) {
      PrintWriter outputStream = new PrintWriter(
          new java.io.FileWriter(taxRegistrationNumber + "_LOG.txt"));
      makeFile(outputStream, flagIncrease, constantsTXT, taxpayerInformation);
      outputStream.close();
    } else if (fileFormat.equals("xml")) 
      {
        PrintWriter outputStream = new PrintWriter(
          new java.io.FileWriter(taxRegistrationNumber + "_LOG.xml"));
        makeFile(outputStream, flagIncrease, constantsXML, taxpayerInformation);
        outputStream.close();
    } else {
      throw new WrongFileFormatException();
    } 
  }


}
