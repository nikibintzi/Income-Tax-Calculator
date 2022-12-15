package incometaxcalculator.data.management;

import incometaxcalculator.data.io.*;
import incometaxcalculator.exceptions.*;

import java.io.File;
import java.io.IOException;

import static incometaxcalculator.data.management.TaxpayerManager.taxpayerHashMap;
///////////////////////////////////////////////////////////////////////////

public class TaxpayerFactory {

    private TaxpayerManager theManager;

    public TaxpayerFactory(TaxpayerManager theManager) {
        super();
        this.theManager = theManager;
    }

    public void createTaxpayerFactory(String fullname, int taxRegistrationNumber, String status,
                                      float income) throws WrongTaxpayerStatusException {
        if (status.equals("Married Filing Jointly")) {
            taxpayerHashMap.put(taxRegistrationNumber,
                    new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income));
        } else if (status.equals("Married Filing Separately")) {
            taxpayerHashMap.put(taxRegistrationNumber,
                    new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income));
        } else if (status.equals("Single")) {
            taxpayerHashMap.put(taxRegistrationNumber,
                    new SingleTaxpayer(fullname, taxRegistrationNumber, income));
        } else if (status.equals("Head of Household")) {
            taxpayerHashMap.put(taxRegistrationNumber,
                    new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income));
        } else {
            throw new WrongTaxpayerStatusException();
        }
    }

    public FileWriter updateFilesFactory(int taxRegistrationNumber) throws IOException, WrongFileFormatException {
        if (new File(taxRegistrationNumber + "_INFO.xml").exists()) {
            new XMLInfoWriter(theManager).generateFile(taxRegistrationNumber,"xml");
        } else {
            new TXTInfoWriter(theManager).generateFile(taxRegistrationNumber,"txt");
            return null;
        }
        if (new File(taxRegistrationNumber + "_INFO.txt").exists()) {
            new TXTInfoWriter(theManager).generateFile(taxRegistrationNumber,"txt");
        }
        return null;
    }

    public FileWriter saveLogFileFactory(String fileFormat)
            throws WrongFileFormatException {
        if (fileFormat.equals("txt")) {
            return new TXTLogWriter(theManager);
        }
        else if (fileFormat.equals("xml")) {
            return new XMLLogWriter(theManager);
        }
        else {
            throw new WrongFileFormatException();
        }
    }
    public FileReader loadTaxpayerFactory(String fileName)
            throws NumberFormatException, WrongFileEndingException {

        String ending[] = fileName.split("\\.");
        if (ending[1].equals("txt")) {
            return new TXTFileReader();
        } else if (ending[1].equals("xml")) {
            return new XMLFileReader();
        } else {
            throw new WrongFileEndingException();
        }
    }
}