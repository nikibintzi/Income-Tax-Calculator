package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import incometaxcalculator.data.management.TaxpayerManager;

public class XMLLogWriter extends LogWriter {

  /*
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;

  public void generateFile(int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_LOG.xml"));
    outputStream.println("<Name> " + getTaxpayerName(taxRegistrationNumber) + " </Name>");
    outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
    outputStream.println("<Income> " + getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
    outputStream
        .println("<BasicTax> " + getTaxpayerBasicTax(taxRegistrationNumber) + " </BasicTax>");
    if (getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println("<TaxIncrease> "
          + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxIncrease>");
    } else {
      outputStream.println("<TaxDecrease> "
          + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + " </TaxDecrease>");
    }
    outputStream
        .println("<TotalTax> " + getTaxpayerTotalTax(taxRegistrationNumber) + " </TotalTax>");
    outputStream.println(
        "<Receipts> " + getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) + " </Receipts>");
    outputStream.println(
        "<Entertainment> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)
            + " </Entertainment>");
    outputStream.println(
        "<Basic> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC) + " </Basic>");
    outputStream.println(
        "<Travel> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL) + " </Travel>");
    outputStream.println(
        "<Health> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH) + " </Health>");
    outputStream.println(
        "<Other> " + getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER) + " </Other>");
    outputStream.close();
  }*/

  public XMLLogWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void makeFile(PrintWriter outputStream, String[] constantsMatrix, ArrayList<String> taxpayerInformation) {
    System.out.println("_______________IN MAKEFILE : XMLLOGWRITER____________");
    System.out.println("length of constanstmatrxi: "+constantsMatrix.length);
    for (int i=0,j=0; i < constantsMatrix.length && j < taxpayerInformation.size() ; i=i+2,j++ )
    {
      /*if (i==8 && flagIncrease==0 ) {
        outputStream.println(constantsMatrix[i+2] + taxpayerInformation.get(i) + constantsMatrix[i+3]);
        i=i+4;
        System.out.println(i);
      }else if (i==8 && flagIncrease==1) {
        outputStream.println(constantsMatrix[i] + taxpayerInformation.get(i) + constantsMatrix[i+1]);
        i=i+4;
        System.out.println(i);
      }else {
        outputStream.println(constantsMatrix[i] + taxpayerInformation.get(i) + constantsMatrix[i+1]);
      }*/
       System.out.println(" i is:" + i);
       System.out.println(" j is:" + j);
       System.out.println(constantsMatrix[i] + taxpayerInformation.get(j)+ constantsMatrix[i+1]);
       outputStream.println(constantsMatrix[i] + taxpayerInformation.get(j)+ constantsMatrix[i+1]);
    }
  }

}
