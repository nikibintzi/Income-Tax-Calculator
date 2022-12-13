package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import incometaxcalculator.data.management.TaxpayerManager;

public class XMLLogWriter extends LogWriter {

  public XMLLogWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
  }

  @Override
  protected void printInFile(PrintWriter outputStream, String[] constantsMatrix, ArrayList<String> taxpayerInformation) {
    for (int i=0,j=0; i < constantsMatrix.length && j < taxpayerInformation.size() ; i=i+2,j++ ){
       outputStream.println(constantsMatrix[i] + taxpayerInformation.get(j)+ constantsMatrix[i+1]);
    }
  }

}
