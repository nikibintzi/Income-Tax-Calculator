package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import incometaxcalculator.data.management.TaxpayerManager;

public class TXTLogWriter extends LogWriter {

  public TXTLogWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
    
    constantsAboutTaxpayer.add("Name: ");
    constantsAboutTaxpayer.add("AFM: ");
    constantsAboutTaxpayer.add("Income: ");
    constantsAboutTaxpayer.add("Basic Tax: ");
    constantsAboutTaxpayer.add("Tax Increase: ");
    constantsAboutTaxpayer.add("Total Tax: ");
    constantsAboutTaxpayer.add("TotalReceiptsGathered: ");
    constantsAboutTaxpayer.add("Entertainment: ");
    constantsAboutTaxpayer.add("Basic: ");
    constantsAboutTaxpayer.add("Travel: ");
    constantsAboutTaxpayer.add("Health: ");
    constantsAboutTaxpayer.add("Other: ");
  }

  @Override
  protected void printInFile(PrintWriter outputStream, ArrayList<String> constantsMatrix, ArrayList<String> taxpayerInformation) {
    //double taxpayerVariationTaxOnReceipts = Double.parseDouble(taxpayerInformation.get(4));
    if (Double.parseDouble(taxpayerInformation.get(4)) < 0) {
      constantsAboutTaxpayer.set(4,"Tax Decrease: ");    
    }
    for (int i=0; i < constantsMatrix.size(); i++ ) {
       outputStream.println(constantsMatrix.get(i) + taxpayerInformation.get(i));
    }
  }
  
}
