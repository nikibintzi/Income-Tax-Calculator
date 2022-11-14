package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import incometaxcalculator.data.management.TaxpayerManager;

public class TXTLogWriter extends LogWriter {

  /*private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
 */
  /*public void generateFile(int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_LOG.txt"));
    outputStream.println("Name: " + getTaxpayerName(taxRegistrationNumber));
    outputStream.println("AFM: " + taxRegistrationNumber);
    outputStream.println("Income: " + getTaxpayerIncome(taxRegistrationNumber));
    outputStream.println("Basic Tax: " + getTaxpayerBasicTax(taxRegistrationNumber));
    if (getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream
          .println("Tax Increase: " + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
    } else {
      outputStream
          .println("Tax Decrease: " + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber));
    }
    outputStream.println("Total Tax: " + getTaxpayerTotalTax(taxRegistrationNumber));
    outputStream.println(
        "TotalReceiptsGathered: " + getTaxpayerTotalReceiptsGathered(taxRegistrationNumber));
    outputStream.println(
        "Entertainment: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT));
    outputStream.println("Basic: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC));
    outputStream
        .println("Travel: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL));
    outputStream
        .println("Health: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH));
    outputStream.println("Other: " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER));
    outputStream.close();
  }*/

  public TXTLogWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void makeFile(PrintWriter outputStream, String[] constantsMatrix, ArrayList<String> taxpayerInformation) {
    System.out.println("_______________IN MAKEFILE : TXTLOGWRITER____________");
    
    for (int i=0; i < constantsMatrix.length; i++ ) 
    {
      System.out.println("_______________IN  FOR _____________IN MAKEFILE : TXTLOGWRITER____________");
      /*if (i==4 && flagIncrease==0 ) {
        System.out.println(constantsMatrix[i+1] + taxpayerInformation.get(i));
        outputStream.println(constantsMatrix[i+1] + taxpayerInformation.get(i));
        i++;
        System.out.println("FLAG=0, i is:" + i);
      }else if (i==4 && flagIncrease==1) {
        System.out.println(constantsMatrix[i] + taxpayerInformation.get(i));
        outputStream.println(constantsMatrix[i] + taxpayerInformation.get(i));
        i++;
        System.out.println("FLAG=1, i is:" + i);
      }else {
        //System.out.println("_______________IN else in MAKEFILE : TXTLOGWRITER____________");
        */
        //System.out.println("IN ELSE, i is:" + i);
       System.out.println(constantsMatrix[i] + taxpayerInformation.get(i));
       outputStream.println(constantsMatrix[i] + taxpayerInformation.get(i));
      
    }
  }
  
}
