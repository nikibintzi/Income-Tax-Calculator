package incometaxcalculator.data.io;

import java.io.IOException;
import java.util.HashMap;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;

public interface FileWriter {
 
  public void generateFile(int taxRegistrationNumber,String fileFormat) throws IOException, WrongFileFormatException;

}
