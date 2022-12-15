package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import incometaxcalculator.data.management.TaxpayerManager;

public class XMLLogWriter extends LogWriter {

  public XMLLogWriter(TaxpayerManager newTaxpayerManager) {
    super(newTaxpayerManager);
    
    constantsAboutTaxpayer.add("<Name> ");
    constantsAboutTaxpayer.add(" </Name>");
    constantsAboutTaxpayer.add("<AFM> ");
    constantsAboutTaxpayer.add(" </AFM>");
    constantsAboutTaxpayer.add("<Income> ");
    constantsAboutTaxpayer.add(" </Income>");
    constantsAboutTaxpayer.add("<BasicTax> ");
    constantsAboutTaxpayer.add(" </BasicTax>");
    constantsAboutTaxpayer.add("<TaxIncrease> ");
    constantsAboutTaxpayer.add(" </TaxIncrease>");
    constantsAboutTaxpayer.add("<TotalTax> ");
    constantsAboutTaxpayer.add(" </TotalTax>");
    constantsAboutTaxpayer.add("<TotalReceiptsGathered> ");
    constantsAboutTaxpayer.add(" </TotalReceiptsGathered>");
    constantsAboutTaxpayer.add("<Entertainment> ");
    constantsAboutTaxpayer.add(" </Entertainment>");
    constantsAboutTaxpayer.add("<Basic> ");
    constantsAboutTaxpayer.add(" </Basic>");
    constantsAboutTaxpayer.add("<Travel> ");
    constantsAboutTaxpayer.add(" </Travel>");
    constantsAboutTaxpayer.add("<Health> ");
    constantsAboutTaxpayer.add(" </Health>");
    constantsAboutTaxpayer.add("<Other> ");
    constantsAboutTaxpayer.add(" </Other>");
  }

  @Override
  protected void printInFile(PrintWriter outputStream, ArrayList<String> constantsMatrix, ArrayList<String> taxpayerInformation) {
    if (Double.parseDouble(taxpayerInformation.get(4)) < 0) {
      constantsAboutTaxpayer.set(8,"<TaxDecrease> ");
      constantsAboutTaxpayer.set(9," </TaxDecrease>");    
    }
    for (int i=0,j=0; i < constantsMatrix.size() && j < taxpayerInformation.size() ; i=i+2,j++ ){
       outputStream.println(constantsMatrix.get(i) + taxpayerInformation.get(j)+ constantsMatrix.get(i+1));
    }
  }

}
