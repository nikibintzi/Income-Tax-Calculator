package incometaxcalculator.data.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import incometaxcalculator.data.management.Company;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.SingleTaxpayer;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

class WriterTest {

  private static Path workingDir;
  private static String taxRegistrationNumberFile;
  private static HashMap<Integer, Taxpayer> taxpayerHashMap = new HashMap<Integer, Taxpayer>(0);
  private static TaxpayerManager taxpayerManager = new TaxpayerManager();
  private static HashMap<Integer, Integer> receiptOwnerTRN = new HashMap<Integer, Integer>(0);
  
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    workingDir = Path.of("", "C:\\Users\\nikib\\Downloads\\SoftDevII-ProjectMaterial-2023\\SoftDevII-ProjectMaterial-2023\\2023-IncomeTaxCalculatorProject");
  }
  
  @Test
  void existsTaxpayerManager() throws IOException {
    assertNotNull(taxpayerManager);
  }
  
  @Test
  void existsTaxpayerHashMap() throws IOException {
    assertNotNull(taxpayerHashMap);
  }
  
  @Test
  void loadInfoTXTFile() throws IOException, NumberFormatException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    taxRegistrationNumberFile = "123456789_INFO.txt";
    taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
    int receiptsNum=taxpayerManager.getTaxpayerTotalReceiptsGathered(123456789);
    
    File text = new File("C:\\Users\\nikib\\Downloads\\SoftDevII-ProjectMaterial-2023\\SoftDevII-ProjectMaterial-2023\\2023-IncomeTaxCalculatorProject\\123456789_INFO.txt");
    Scanner scnr = new Scanner(text);
    int lineNumber = 1;
    int AFM=0;
    int succesfulLoad = 0;
    String name="";
    String status="";
    String lookFor= "Receipt ID: "+1;
    while(scnr.hasNextLine()){
        String line = scnr.nextLine();
        if(lineNumber == 1){
          name = line.replace("Name: ", "");
        }
        if(lineNumber == 2){
          String replace = line.replace("AFM: ", "");
          AFM =  Integer.valueOf(replace);
        }
        if(lineNumber == 3){
          status = line.replace("Status: ", "");
        }
        if(line.indexOf(lookFor) != -1){
            succesfulLoad=1;
          }
        lineNumber++;
    }
    assertEquals(true, taxpayerManager.containsTaxpayer());
    assertEquals(name,taxpayerManager.getTaxpayerName(123456789));
    assertEquals(AFM,123456789);
    assertEquals(status,taxpayerManager.getTaxpayerStatus(123456789));
    assertEquals(succesfulLoad,1);
  }
  
  @Test
  void saveLogTXTFile() throws IOException, NumberFormatException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    taxRegistrationNumberFile = "123456789_INFO.txt";
    taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
    taxpayerManager.saveLogFile(123456789,"txt");
    File text = new File("C:\\Users\\nikib\\Downloads\\SoftDevII-ProjectMaterial-2023\\SoftDevII-ProjectMaterial-2023\\2023-IncomeTaxCalculatorProject\\123456789_LOG.txt");
    Scanner scnr = new Scanner(text);
    int receiptsGathered = 0;
    int lineNumber = 1;
    while(scnr.hasNextLine()){
        String line = scnr.nextLine();
        if(lineNumber == 7){
            String replace = line.replace("TotalReceiptsGathered: ", "");
            receiptsGathered =  Integer.valueOf(replace);
        }
        lineNumber++;
    }
    taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
    assertEquals(true,taxpayerManager.containsTaxpayer());
    assertEquals(receiptsGathered,taxpayerManager.getTaxpayerTotalReceiptsGathered(123456789));
  }
  
  
  @Test
  void loadInfoXMLFile() throws IOException, NumberFormatException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    taxRegistrationNumberFile = "123456789_INFO.xml";
    taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
    int receiptsNum=taxpayerManager.getTaxpayerTotalReceiptsGathered(123456789);
    File text = new File("C:\\Users\\nikib\\Downloads\\SoftDevII-ProjectMaterial-2023\\SoftDevII-ProjectMaterial-2023\\2023-IncomeTaxCalculatorProject\\123456789_INFO.xml");
    Scanner scnr = new Scanner(text);
    int lineNumber = 1;
    int AFM=0;
    int succesfulLoad = 0;
    String name="";
    String status="";
    String lookFor= "<ReceiptID> "+1;
    while(scnr.hasNextLine()){
        String line = scnr.nextLine();
        if(lineNumber == 1){
          String[] arrOfStr = line.split(" ", 4);
          name = arrOfStr[1]+" "+arrOfStr[2];
        }
        if(lineNumber == 2){
          String[] arrOfStr = line.split(" ", 3);
          AFM =  Integer.valueOf(arrOfStr[1]);
        }
        if(lineNumber == 3){
          String[] arrOfStr = line.split(" ", 5);
          status = arrOfStr[1]+" "+arrOfStr[2]+" "+arrOfStr[3];
        }
        if(line.indexOf(lookFor) != -1){
            succesfulLoad=1;
          }
        lineNumber++;
    }
    assertEquals(true, taxpayerManager.containsTaxpayer());
    assertEquals(name,taxpayerManager.getTaxpayerName(123456789));
    assertEquals(AFM,123456789);
    assertEquals(status,taxpayerManager.getTaxpayerStatus(123456789));
    assertEquals(succesfulLoad,1);
    
  }
  
  
  @Test
  void saveLogXMLFile() throws IOException, NumberFormatException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    taxRegistrationNumberFile = "123456789_INFO.xml";
    taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
    taxpayerManager.saveLogFile(123456789,"xml");
    File text = new File("C:\\Users\\nikib\\Downloads\\SoftDevII-ProjectMaterial-2023\\SoftDevII-ProjectMaterial-2023\\2023-IncomeTaxCalculatorProject\\123456789_LOG.xml");
    Scanner scnr = new Scanner(text);
    int receiptsGathered = 0;
    int lineNumber = 1;
    while(scnr.hasNextLine()){
        String line = scnr.nextLine();
        if(lineNumber == 7){
          String[] arrOfStr = line.split(" ", 3);
          receiptsGathered =  Integer.valueOf(arrOfStr[1]);
        }
        lineNumber++;
    }
    taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
    assertEquals(true,taxpayerManager.containsTaxpayer());
    assertEquals(receiptsGathered,taxpayerManager.getTaxpayerTotalReceiptsGathered(123456789));
  }

  
  @Test
  void addReceiptUpdateInfo() throws IOException, WrongFileFormatException, WrongReceiptKindException, NumberFormatException, WrongFileEndingException, WrongTaxpayerStatusException {
    try {
        taxpayerManager.loadTaxpayer("4435_INFO.txt");
        int receiptsNum=taxpayerManager.getTaxpayerTotalReceiptsGathered(4435);
        //System.out.printf("ReceiptsGathered 4435: %d\n",receiptsNum);
        
        Receipt receipt1 = new Receipt(receiptsNum+1,"12/12/2012",1001.5F,"Other",new Company("Lol","Greece", "Ioannina", "Zerva", 1));
        Receipt receipt2 = new Receipt(receiptsNum+2,"11/11/2013",1002.5F,"Basic",new Company("Oasis","Greece", "Ioannina", "Zerva", 3));
        
        taxpayerManager.getTaxpayer(4435).addReceipt(receipt1);
        taxpayerManager.getTaxpayer(4435).addReceipt(receipt2);
        //System.out.printf("ReceiptsGathered 4435 after: %d\n",taxpayerManager.getTaxpayerTotalReceiptsGathered(4435));
        taxpayerManager.updateFiles(4435);
        
        File text = new File("C:\\Users\\nikib\\Downloads\\SoftDevII-ProjectMaterial-2023\\SoftDevII-ProjectMaterial-2023\\2023-IncomeTaxCalculatorProject\\4435_INFO.txt");
        Scanner scnr = new Scanner(text);
        int succesfulAdd = 0;
        String lookFor= "Receipt ID: "+(receiptsNum+1);
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            if(line.indexOf(lookFor) != -1){
              lookFor= "Receipt ID: "+(receiptsNum+2);
            }
            if(line.indexOf(lookFor) != -1){
              succesfulAdd=1;
            }
        }
        
        assertEquals(true,taxpayerManager.containsTaxpayer(4435));
        assertEquals(receiptsNum+2,taxpayerManager.getTaxpayerTotalReceiptsGathered(4435));
        assertEquals(succesfulAdd,1);
    } catch (WrongReceiptDateException e) {
        throw new RuntimeException(e);
    }
  }
  
  @Test
  void deleteReceiptUpdateInfo() throws IOException, WrongFileFormatException, WrongReceiptKindException, NumberFormatException, WrongFileEndingException, WrongTaxpayerStatusException {
    try {
        taxpayerManager.loadTaxpayer("123456789_INFO.txt");
        int receiptsNum=taxpayerManager.getTaxpayerTotalReceiptsGathered(123456789);
        System.out.printf("ReceiptsGathered 123456789: %d\n",receiptsNum);
        if (receiptsNum>1) {
          taxpayerManager.removeReceipt(receiptsNum);
        }
        System.out.printf("ReceiptsGathered 123456789 after: %d\n",taxpayerManager.getTaxpayerTotalReceiptsGathered(123456789));

        File text = new File("C:\\Users\\nikib\\Downloads\\SoftDevII-ProjectMaterial-2023\\SoftDevII-ProjectMaterial-2023\\2023-IncomeTaxCalculatorProject\\123456789_INFO.txt");
        Scanner scnr = new Scanner(text);
        int succesfulDel = 0;
        String lookFor= "Receipt ID: "+receiptsNum;
        //System.out.printf("LookFor: %s",lookFor);
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            if(line.indexOf(lookFor) == -1)
            {
              succesfulDel=1;
            }
        }
        assertEquals(true,taxpayerManager.containsTaxpayer(123456789));
        assertEquals(receiptsNum-1,taxpayerManager.getTaxpayerTotalReceiptsGathered(123456789));
        assertEquals(succesfulDel,1);
    } catch (WrongReceiptDateException e) {
        throw new RuntimeException(e);
    }
  }

}
