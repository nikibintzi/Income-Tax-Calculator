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
    
    constantsAboutTaxpayer.add("Name: ");
    constantsAboutTaxpayer.add("AFM: ");
    constantsAboutTaxpayer.add("Status: ");
    constantsAboutTaxpayer.add("Income: ");
    constantsAboutTaxpayer.add("Receipts: ");
    
    constantsAboutReceipts.add("Receipt ID: ");
    constantsAboutReceipts.add("Date: ");
    constantsAboutReceipts.add("Kind: ");
    constantsAboutReceipts.add("Amount: ");
    constantsAboutReceipts.add("Company: ");
    constantsAboutReceipts.add("Country: ");
    constantsAboutReceipts.add("City: ");
    constantsAboutReceipts.add("Street: ");
    constantsAboutReceipts.add("Number: ");

  }

  @Override
  protected void printInFile(PrintWriter outputStream, ArrayList<String> constantsMatrix,
      ArrayList<String> informationOnTaxpayer) {
    for (int i=0; i < constantsMatrix.size(); i++ ) {
      if (i==4) {
        outputStream.println(constantsMatrix.get(i));
      }else {
        outputStream.println(constantsMatrix.get(i) + informationOnTaxpayer.get(i));
      }
    }
  }
  
  @Override
  protected void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream,ArrayList<String> infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts ) {
    for (int j=0; j < informationOnAllReceipts.size(); j++ ) {
      for (int k=0; k < informationOnAllReceipts.get(j).size(); k++ ) {
        outputStream.println(infoOnReceiptsMatrix.get(k) + informationOnAllReceipts.get(j).get(k));
      }
      outputStream.println();
    }
  }



}