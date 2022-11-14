package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {

  public TXTInfoWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
    // TODO Auto-generated constructor stub
  }

  private TaxpayerManager theManager;

  
/*
  public void generateFile(int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
    outputStream.println("Name: " + theManager.getTaxpayerName(taxRegistrationNumber));
    outputStream.println("AFM: " + taxRegistrationNumber);
    outputStream.println("Status: " + theManager.getTaxpayerStatus(taxRegistrationNumber));
    outputStream.println("Income: " + theManager.getTaxpayerIncome(taxRegistrationNumber));
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println("Receipts:");
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }*/

  @Override
  protected void generateFile(PrintWriter outputStream, String[] constantsMatrix,
      ArrayList<String> informationOnTaxpayer) {
    System.out.println("_______________IN GENERATEFILE : TXTINFOWRITER____________");
    for (int i=0; i < constantsMatrix.length; i++ ) 
    {
      if (i==4) {
        outputStream.println(constantsMatrix[i]);
      }else {
        outputStream.println(constantsMatrix[i] + informationOnTaxpayer.get(i));
      }
    }
  }
  
  @Override
  protected void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream,String[] infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts ) {

    /*
    HashMap<Integer, Receipt> receiptsHashMap = getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      for (int i=0; i <  infoOnReceiptsMatrix.length; i++ ) {
        outputStream.println(infoOnReceiptsMatrix[i]);
        outputStream.println("Receipt ID: " + getReceiptId(receipt));
        outputStream.println("Date: " + getReceiptIssueDate(receipt));
        outputStream.println("Kind: " + getReceiptKind(receipt));
        outputStream.println("Amount: " + getReceiptAmount(receipt));
        outputStream.println("Company: " + getCompanyName(receipt));
        outputStream.println("Country: " + getCompanyCountry(receipt));
        outputStream.println("City: " + getCompanyCity(receipt));
        outputStream.println("Street: " + getCompanyStreet(receipt));
        outputStream.println("Number: " + getCompanyNumber(receipt));
      }
      outputStream.println();
    }
  }*/
    System.out.println("_______________IN generateTaxpayerReceipts : TXTINFOWRITER____________");
    for (int j=0; j < informationOnAllReceipts.size(); j++ ) {
      for (int k=0; k < informationOnAllReceipts.get(j).size(); k++ ) {
        outputStream.println(infoOnReceiptsMatrix[k] + informationOnAllReceipts.get(j).get(k));
      }
      outputStream.println();
    }
  }




  

}