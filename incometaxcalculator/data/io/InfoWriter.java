package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;

public abstract class InfoWriter implements FileWriter{

  private TaxpayerManager theManager;
  
  private String[] infoConstantsTXT = {"Name: ","AFM: ","Status: ","Income: ","Receipts: "};
  private String[] infoConstantsXML = {"<Name> "," </Name>","<AFM> "," </AFM>","<Status> "," </Status>","<Income> "," </Income>","<Receipts> "};
  private String[] infoOnReceiptsTXT = {"Receipt ID: ","Date: ","Kind: ","Amount: ","Company: ","Country: ","City: ","Street: ","Number: "};
  private String[] infoOnReceiptsXML = {"<ReceiptID> "," </ReceiptID>","<Date> "," </Date>","<Kind> "," </Kind>","<Amount> "," </Amount>","<Company> "," </Company>","<Country> "," </Country>","<City> "," </City>","<Street> "," </Street>","<Number> "," </Number>"};  
  
  protected abstract void generateTaxpayerReceipts(int taxRegistrationNumber,
      PrintWriter outputStream, String[] infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts);

  protected abstract void generateFile(PrintWriter outputStream, String[] constantsMatrix,
      ArrayList<String> informationToPrint);
  
  public InfoWriter(TaxpayerManager newTaxpayerManager) {
    theManager = newTaxpayerManager;
  }
  
  //@Override
  public void generateFile(int taxRegistrationNumber, String fileFormat) throws IOException, WrongFileFormatException {
    System.out.println("_______IN GENERATEFILES : INFOWRITER____");
    ArrayList<String> informationOnTaxpayer = new ArrayList<String>();
    informationOnTaxpayer.add(theManager.getTaxpayerName(taxRegistrationNumber));
    informationOnTaxpayer.add(Integer.toString(taxRegistrationNumber));
    informationOnTaxpayer.add(theManager.getTaxpayerStatus(taxRegistrationNumber));
    informationOnTaxpayer.add(theManager.getTaxpayerIncome(taxRegistrationNumber));
      
  
    //ArrayList<String> informationOnReceipt = new ArrayList<String>();
    ArrayList<ArrayList> informationOnAllReceipts = new ArrayList<ArrayList>();
    //
    int temp=0;
    //
    HashMap<Integer, Receipt> receiptsHashMap = getReceiptHashMap(taxRegistrationNumber);
    //
    System.out.println("receiptsHashMap.size() :"+receiptsHashMap.size());
    System.out.println("receiptsHashMap :"+receiptsHashMap);
    //
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      ArrayList<String> informationOnReceipt = new ArrayList<String>();
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      //temp++;
      //
      informationOnReceipt.add(Integer.toString(getReceiptId(receipt)));
      informationOnReceipt.add(getReceiptIssueDate(receipt));
      informationOnReceipt.add(getReceiptKind(receipt));
      informationOnReceipt.add(Float.toString(getReceiptAmount(receipt)));
      informationOnReceipt.add(getCompanyName(receipt));
      informationOnReceipt.add(getCompanyCountry(receipt));
      informationOnReceipt.add(getCompanyCity(receipt));
      informationOnReceipt.add(getCompanyStreet(receipt));
      informationOnReceipt.add(Integer.toString(getCompanyNumber(receipt)));
      //
      for (String el : informationOnReceipt){
        System.out.println("element :"+el);
      }
      //System.out.println("temp: "+temp);
      System.out.println("_______________________");
      //
      informationOnAllReceipts.add(informationOnReceipt);
      //informationOnReceipt.clear();
    }
    //
    System.out.println("informationOnAllReceipts: "+informationOnAllReceipts.size());
    //
    
    if (fileFormat.equals("txt")) {
      PrintWriter outputStream = new PrintWriter(
          new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
      generateFile(outputStream, infoConstantsTXT, informationOnTaxpayer);
      generateTaxpayerReceipts(taxRegistrationNumber, outputStream, infoOnReceiptsTXT, informationOnAllReceipts);
      outputStream.close();
    } else if (fileFormat.equals("xml")) 
      {
        PrintWriter outputStream = new PrintWriter(
          new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
        generateFile(outputStream, infoConstantsXML, informationOnTaxpayer);
        generateTaxpayerReceipts(taxRegistrationNumber, outputStream, infoOnReceiptsXML, informationOnAllReceipts);
        outputStream.close();
    } else {
      throw new WrongFileFormatException();
    } 
  }

  //////////////////////////////////////////////////
  public int getReceiptId(Receipt receipt) {
    return receipt.getId();
  }

  public String getReceiptIssueDate(Receipt receipt) {
    return receipt.getIssueDate();
  }

  public String getReceiptKind(Receipt receipt) {
    return receipt.getKind();
  }

  public float getReceiptAmount(Receipt receipt) {
    return receipt.getAmount();
  }
  /////////////////////////////////////////////////
  public String getCompanyName(Receipt receipt) {
    return receipt.getCompany().getName();
  }

  public String getCompanyCountry(Receipt receipt) {
    return receipt.getCompany().getCountry();
  }

  public String getCompanyCity(Receipt receipt) {
    return receipt.getCompany().getCity();
  }

  public String getCompanyStreet(Receipt receipt) {
    return receipt.getCompany().getStreet();
  }

  public int getCompanyNumber(Receipt receipt) {
    return receipt.getCompany().getNumber();
  }

  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    return theManager.getReceiptHashMap(taxRegistrationNumber);
  }



  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
