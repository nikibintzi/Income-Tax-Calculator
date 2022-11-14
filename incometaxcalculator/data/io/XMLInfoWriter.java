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
    // TODO Auto-generated constructor stub
  }

  private TaxpayerManager theManager;
  
  /*public void generateFile(int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
    outputStream.println("<Name> " + theManager.getTaxpayerName(taxRegistrationNumber) + " </Name>");
    outputStream.println("<AFM> " + taxRegistrationNumber + " </AFM>");
    outputStream.println("<Status> " + theManager.getTaxpayerStatus(taxRegistrationNumber) + " </Status>");
    outputStream.println("<Income> " + theManager.getTaxpayerIncome(taxRegistrationNumber) + " </Income>");
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println("<Receipts>");
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }*/
  @Override
  protected void generateFile(PrintWriter outputStream, String[] constantsMatrix, ArrayList<String> informationOnTaxpayer) {
    System.out.println("_______________IN GENERATEFILE : XMLINFOWRITER____________");
    for (int i=0,j=0; i < constantsMatrix.length && j < informationOnTaxpayer.size() ; i=i+2,j++ ) 
    {
      if (i==8) {
        outputStream.println(constantsMatrix[i]);
     }else {
      outputStream.println(constantsMatrix[i] + informationOnTaxpayer.get(j) + constantsMatrix[i+1]);
     }
    }
  }

  protected void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream,String[] infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts ) {
    /*
    HashMap<Integer, Receipt> receiptsHashMap = getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println("<ReceiptID> " + getReceiptId(receipt) + " </ReceiptID>");
      outputStream.println("<Date> " + getReceiptIssueDate(receipt) + " </Date>");
      outputStream.println("<Kind> " + getReceiptKind(receipt) + " </Kind>");
      outputStream.println("<Amount> " + getReceiptAmount(receipt) + " </Amount>");
      outputStream.println("<Company> " + getCompanyName(receipt) + " </Company>");
      outputStream.println("<Country> " + getCompanyCountry(receipt) + " </Country>");
      outputStream.println("<City> " + getCompanyCity(receipt) + " </City>");
      outputStream.println("<Street> " + getCompanyStreet(receipt) + " </Street>");
      outputStream.println("<Number> " + getCompanyNumber(receipt) + " </Number>");
      outputStream.println();
    }*/
    System.out.println("_______________IN generateTaxpayerReceipts : XMLINFOWRITER____________");
    System.out.println("informationOnAllReceipts: "+informationOnAllReceipts.size());
    for (int j=0; j < informationOnAllReceipts.size(); j++ ) {
      System.out.println(" j is:" + j);
      System.out.println("informationOnAllReceipts.get(j).size(): "+informationOnAllReceipts.get(j).size());
      for (int k=0,l=0; k < infoOnReceiptsMatrix.length; k=k+2,l++ ) {
        System.out.println(" k is:" + k);
        System.out.println(" l is:" + l);
        System.out.println(infoOnReceiptsMatrix[k] + informationOnAllReceipts.get(j).get(l) + infoOnReceiptsMatrix[k+1]);
        outputStream.println(infoOnReceiptsMatrix[k] + informationOnAllReceipts.get(j).get(l) + infoOnReceiptsMatrix[k+1]);
      }
      outputStream.println();
    }
  }

}