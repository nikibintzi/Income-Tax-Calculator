package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class XMLInfoWriter extends InfoWriter {

  public XMLInfoWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
  }

  @Override
  protected void printInFile(PrintWriter outputStream, String[] constantsMatrix, ArrayList<String> informationOnTaxpayer) {
    System.out.println("_______________IN GENERATEFILE : XMLINFOWRITER____________");
    for (int i=0,j=0; i < constantsMatrix.length && j < informationOnTaxpayer.size() ; i=i+2,j++ ) {
      if (i==8) {
        outputStream.println(constantsMatrix[i]);
     }else {
      outputStream.println(constantsMatrix[i] + informationOnTaxpayer.get(j) + constantsMatrix[i+1]);
     }
    }
  }

  protected void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream,String[] infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts ) {
    System.out.println("_______________IN generateTaxpayerReceipts : XMLINFOWRITER____________");
    System.out.println("informationOnAllReceipts: "+informationOnAllReceipts.size());
    for (int j=0; j < informationOnAllReceipts.size(); j++ ) {
      //System.out.println("informationOnAllReceipts.get(j).size(): "+informationOnAllReceipts.get(j).size());
      for (int k=0,l=0; k < infoOnReceiptsMatrix.length; k=k+2,l++ ) {
        //System.out.println(infoOnReceiptsMatrix[k] + informationOnAllReceipts.get(j).get(l) + infoOnReceiptsMatrix[k+1]);
        outputStream.println(infoOnReceiptsMatrix[k] + informationOnAllReceipts.get(j).get(l) + infoOnReceiptsMatrix[k+1]);
      }
      outputStream.println();
    }
  }

}