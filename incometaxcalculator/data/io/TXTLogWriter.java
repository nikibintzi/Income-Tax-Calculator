package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import incometaxcalculator.data.management.TaxpayerManager;

public class TXTLogWriter extends LogWriter {

  public TXTLogWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
  }

  @Override
  protected void printInFile(PrintWriter outputStream, String[] constantsMatrix, ArrayList<String> taxpayerInformation) {
    System.out.println("_______________IN MAKEFILE : TXTLOGWRITER____________");
    for (int i=0; i < constantsMatrix.length; i++ ) {
       System.out.println(constantsMatrix[i] + taxpayerInformation.get(i));
       outputStream.println(constantsMatrix[i] + taxpayerInformation.get(i));
    }
  }
  
}
