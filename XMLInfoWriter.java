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
    
    constantsAboutTaxpayer.add("<Name> ");
    constantsAboutTaxpayer.add(" </Name>");
    constantsAboutTaxpayer.add("<AFM> ");
    constantsAboutTaxpayer.add(" </AFM>");
    constantsAboutTaxpayer.add("<Status> ");
    constantsAboutTaxpayer.add(" </Status>");
    constantsAboutTaxpayer.add("<Income> ");
    constantsAboutTaxpayer.add(" </Income>");
    constantsAboutTaxpayer.add("<Receipts> ");
    
    constantsAboutReceipts.add("<ReceiptID> ");
    constantsAboutReceipts.add(" </ReceiptID>");
    constantsAboutReceipts.add("<Date> ");
    constantsAboutReceipts.add(" </Date>");
    constantsAboutReceipts.add("<Kind> ");
    constantsAboutReceipts.add(" </Kind>");
    constantsAboutReceipts.add("<Amount> ");
    constantsAboutReceipts.add(" </Amount>");
    constantsAboutReceipts.add("<Company> ");
    constantsAboutReceipts.add(" </Company>");
    constantsAboutReceipts.add("<Country> ");
    constantsAboutReceipts.add(" </Country>");
    constantsAboutReceipts.add("<City> ");
    constantsAboutReceipts.add(" </City>");
    constantsAboutReceipts.add("<Street> ");
    constantsAboutReceipts.add(" </Street>");
    constantsAboutReceipts.add("<Number> ");
    constantsAboutReceipts.add(" </Number>");
  }

  @Override
  protected void printInFile(PrintWriter outputStream, ArrayList<String> constantsMatrix, ArrayList<String> informationOnTaxpayer) {
    for (int i=0,j=0; i < constantsMatrix.size() && j < informationOnTaxpayer.size() ; i=i+2,j++ ) {
      if (i==8) {
        outputStream.println(constantsMatrix.get(i));
     }else {
      outputStream.println(constantsMatrix.get(i) + informationOnTaxpayer.get(j) + constantsMatrix.get(i+1));
     }
    }
  }

  protected void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream,ArrayList<String> infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts ) {
    for (int j=0; j < informationOnAllReceipts.size(); j++ ) {
      for (int k=0,l=0; k < infoOnReceiptsMatrix.size(); k=k+2,l++ ) {
        outputStream.println(infoOnReceiptsMatrix.get(k) + informationOnAllReceipts.get(j).get(l) + infoOnReceiptsMatrix.get(k+1));
      }
      outputStream.println();
    }
  }

}