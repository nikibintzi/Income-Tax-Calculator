package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {

  public TXTInfoWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
  }

  @Override
  protected void printInFile(PrintWriter outputStream, String[] constantsMatrix,
      ArrayList<String> informationOnTaxpayer) {
    for (int i=0; i < constantsMatrix.length; i++ ) {
      if (i==4) {
        outputStream.println(constantsMatrix[i]);
      }else {
        outputStream.println(constantsMatrix[i] + informationOnTaxpayer.get(i));
      }
    }
  }
  
  @Override
  protected void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream,String[] infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts ) {
    for (int j=0; j < informationOnAllReceipts.size(); j++ ) {
      for (int k=0; k < informationOnAllReceipts.get(j).size(); k++ ) {
        outputStream.println(infoOnReceiptsMatrix[k] + informationOnAllReceipts.get(j).get(k));
      }
      outputStream.println();
    }
  }



}