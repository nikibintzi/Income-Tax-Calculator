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
  
  private String[] constantsAboutTaxpayerTXT = {"Name: ","AFM: ","Status: ","Income: ","Receipts: "};
  private String[] constantsAboutTaxpayerXML = {"<Name> "," </Name>","<AFM> "," </AFM>","<Status> "," </Status>","<Income> "," </Income>","<Receipts> "};
  private String[] constantsAboutReceiptsTXT = {"Receipt ID: ","Date: ","Kind: ","Amount: ","Company: ","Country: ","City: ","Street: ","Number: "};
  private String[] constantsAboutReceiptsXML = {"<ReceiptID> "," </ReceiptID>","<Date> "," </Date>","<Kind> "," </Kind>","<Amount> "," </Amount>","<Company> "," </Company>","<Country> "," </Country>","<City> "," </City>","<Street> "," </Street>","<Number> "," </Number>"};  
  
  protected abstract void generateTaxpayerReceipts(int taxRegistrationNumber,
      PrintWriter outputStream, String[] infoOnReceiptsMatrix,ArrayList<ArrayList> informationOnAllReceipts);

  protected abstract void printInFile(PrintWriter outputStream, String[] constantsMatrix,
      ArrayList<String> informationToPrint);
  
  public InfoWriter(TaxpayerManager newTaxpayerManager) {
    theManager = newTaxpayerManager;
  }
  
  @Override
  public void generateFile(int taxRegistrationNumber, String fileFormat) throws IOException, WrongFileFormatException {
    ArrayList<String> informationOnTaxpayer = new ArrayList<String>();
    informationOnTaxpayer=extractTaxpayerInfoInList(taxRegistrationNumber, informationOnTaxpayer);
      
    ArrayList<ArrayList> informationOnAllReceipts = new ArrayList<ArrayList>();
    informationOnAllReceipts =extractReceiptsInfoInList(taxRegistrationNumber, informationOnAllReceipts);
    if (fileFormat.equals("txt")) {
      generateOutputStream(fileFormat,taxRegistrationNumber, informationOnTaxpayer,
          informationOnAllReceipts,constantsAboutTaxpayerTXT,constantsAboutReceiptsTXT);
    } else if (fileFormat.equals("xml")) {
        generateOutputStream(fileFormat,taxRegistrationNumber, informationOnTaxpayer,
            informationOnAllReceipts,constantsAboutTaxpayerXML,constantsAboutReceiptsXML);
    } else {
      throw new WrongFileFormatException();
    } 
  }

  protected ArrayList<String> extractTaxpayerInfoInList(int taxRegistrationNumber,
      ArrayList<String> informationOnTaxpayer) {
    informationOnTaxpayer.add(theManager.getTaxpayerName(taxRegistrationNumber));
    informationOnTaxpayer.add(Integer.toString(taxRegistrationNumber));
    informationOnTaxpayer.add(theManager.getTaxpayerStatus(taxRegistrationNumber));
    informationOnTaxpayer.add(theManager.getTaxpayerIncome(taxRegistrationNumber));
    return informationOnTaxpayer;
  }
  
  protected ArrayList<ArrayList> extractReceiptsInfoInList(int taxRegistrationNumber,
      ArrayList<ArrayList> informationOnAllReceipts) {
    HashMap<Integer, Receipt> receiptsHashMap = getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      ArrayList<String> informationOnReceipt = new ArrayList<String>();
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      informationOnReceipt.add(Integer.toString(getReceiptId(receipt)));
      informationOnReceipt.add(getReceiptIssueDate(receipt));
      informationOnReceipt.add(getReceiptKind(receipt));
      informationOnReceipt.add(Float.toString(getReceiptAmount(receipt)));
      informationOnReceipt.add(getCompanyName(receipt));
      informationOnReceipt.add(getCompanyCountry(receipt));
      informationOnReceipt.add(getCompanyCity(receipt));
      informationOnReceipt.add(getCompanyStreet(receipt));
      informationOnReceipt.add(Integer.toString(getCompanyNumber(receipt)));
      informationOnAllReceipts.add(informationOnReceipt);
    }
    return informationOnAllReceipts;
  }

  protected void generateOutputStream(String fileFormat, int taxRegistrationNumber,
      ArrayList<String> informationOnTaxpayer, ArrayList<ArrayList> informationOnAllReceipts,String[] constantsAboutTaxpayer,String[] constantsAboutReceipts)
      throws IOException {
    PrintWriter outputStream = new PrintWriter(
      new java.io.FileWriter(taxRegistrationNumber + "_INFO."+fileFormat));
    printInFile(outputStream, constantsAboutTaxpayer, informationOnTaxpayer);
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream, constantsAboutReceipts, informationOnAllReceipts);
    outputStream.close();
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
