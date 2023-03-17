package incometaxcalculator.data.management;


import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static incometaxcalculator.data.management.TaxpayerManager.taxpayerHashMap;
import static org.junit.jupiter.api.Assertions.*;

class TaxpayerTest {

    @Test
    void addReceiptTest() throws WrongReceiptDateException, WrongReceiptKindException {
        SingleTaxpayer singletaxpayer1 = new SingleTaxpayer("Avgoustinos Zhgos",123456798, 10000.5F);
            Receipt receipt1 = new Receipt(1,"12/12/2012",1000.5F,"Basic",new Company("Lol2","Greece", "Ioannina", "Zerva", 1));

            float startingReceipts = 0;
            int startingTotalReceiptsGathered = 0;
            HashMap<Integer, Receipt> receiptHashBeforeAdd;
            receiptHashBeforeAdd = singletaxpayer1.getReceiptHashMap();

            singletaxpayer1.addReceipt(receipt1);

            startingReceipts += 1000.5;
            startingTotalReceiptsGathered += 1;
            HashMap<Integer, Receipt> receiptHashAfterAdd;
            receiptHashAfterAdd = singletaxpayer1.getReceiptHashMap();

            assertEquals(startingReceipts,singletaxpayer1.getAmountOfReceiptKind((short) 1));
            assertEquals(startingTotalReceiptsGathered,singletaxpayer1.getTotalReceiptsGathered());
            assertEquals (receiptHashBeforeAdd.get(1),receiptHashAfterAdd.get(1));
    }

        @Test
    void removeReceipt() throws WrongReceiptDateException, WrongReceiptKindException {
            SingleTaxpayer singletaxpayer1 = new SingleTaxpayer("Avgoustinos Zhgos",123456798, 10000.5F);
                Receipt receipt1 = new Receipt(1,"12/12/2012",1000.5F,"Basic",new Company("Lol2","Greece", "Ioannina", "Zerva", 1));

                float startingReceipts = 0;
                int startingTotalReceiptsGathered = 0;

                singletaxpayer1.addReceipt(receipt1);

                startingReceipts += singletaxpayer1.getAmountOfReceiptKind((short) 1);
                startingTotalReceiptsGathered += singletaxpayer1.getTotalReceiptsGathered();
                HashMap<Integer, Receipt> receiptHashAfterAdd;
                receiptHashAfterAdd = singletaxpayer1.getReceiptHashMap();

                singletaxpayer1.removeReceipt(receipt1.getId());

                HashMap<Integer, Receipt> receiptHashAfterRemove;
                receiptHashAfterRemove = singletaxpayer1.getReceiptHashMap();

                startingReceipts -= 1000.5;
                startingTotalReceiptsGathered -= 1;
                receiptHashAfterAdd.remove(1);

                assertEquals(startingReceipts,singletaxpayer1.getAmountOfReceiptKind((short) 1));
                assertEquals(startingTotalReceiptsGathered,singletaxpayer1.getTotalReceiptsGathered());
                assertEquals(receiptHashAfterAdd.get(1),receiptHashAfterRemove.get(1));
    }

    @Test
    void getVariationTaxOnReceipts() {
        SingleTaxpayer singleTaxpayer = new SingleTaxpayer("Avgoustinos Zigos", 123456780, 5000);
        try {
            Receipt receipt1 = new Receipt(1,"12/12/2012",1001.5F,"Other",new Company("Lol2","Greece", "Ioannina", "Zerva", 1));
            Receipt receipt2 = new Receipt(2,"11/11/2013",1002.5F,"Basic",new Company("Lol2","Greece", "Ioannina", "Zerva", 1));
            float variationTaxOnReceipts = (float) (0.15 * 0.0535 * 5000);

            singleTaxpayer.addReceipt(receipt1);
            singleTaxpayer.addReceipt(receipt2);

            assertEquals(variationTaxOnReceipts, singleTaxpayer.getVariationTaxOnReceipts());

        } catch (WrongReceiptDateException e) {
            throw new RuntimeException(e);
        } catch (WrongReceiptKindException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createSingleTaxpayerTest() throws WrongTaxpayerStatusException {

        TaxpayerManager taxpayerManager = new TaxpayerManager();
        taxpayerManager.createTaxpayer("Avgoustinos Zigos", 987654321, "Single", 10000000.5F);

        Taxpayer singletaxp1 =  taxpayerHashMap.get(987654321);

        assertEquals(singletaxp1.taxRegistrationNumber , 987654321);
        assertEquals(singletaxp1.income , 10000000.5F);
        assertEquals(singletaxp1.fullname , "Avgoustinos Zigos");
    }

    @Test
    void createHeadOfHouseholdTaxpayerTest() throws WrongTaxpayerStatusException {

        TaxpayerManager taxpayerManager = new TaxpayerManager();
        taxpayerManager.createTaxpayer("Avgoustinos Zigos", 987654321, "Head of Household", 10000000.5F);

        Taxpayer singletaxp1 =  taxpayerHashMap.get(987654321);

        assertEquals(singletaxp1.taxRegistrationNumber , 987654321);
        assertEquals(singletaxp1.income , 10000000.5F);
        assertEquals(singletaxp1.fullname , "Avgoustinos Zigos");
    }

    @Test
    void createMarriedFilingJointlyTaxpayerTest() throws WrongTaxpayerStatusException {

        TaxpayerManager taxpayerManager = new TaxpayerManager();
        taxpayerManager.createTaxpayer("Avgoustinos Zigos", 987654321, "Married Filing Jointly", 10000000.5F);

        Taxpayer singletaxp1 =  taxpayerHashMap.get(987654321);

        assertEquals(singletaxp1.taxRegistrationNumber , 987654321);
        assertEquals(singletaxp1.income , 10000000.5F);
        assertEquals(singletaxp1.fullname , "Avgoustinos Zigos");
    }

    @Test
    void createMarriedFilingSeparatelyTaxpayerTest() throws WrongTaxpayerStatusException {

        TaxpayerManager taxpayerManager = new TaxpayerManager();
        taxpayerManager.createTaxpayer("Avgoustinos Zigos", 987654321, "Married Filing Separately", 10000000.5F);

        Taxpayer singletaxp1 =  taxpayerHashMap.get(987654321);

        assertEquals(singletaxp1.taxRegistrationNumber , 987654321);
        assertEquals(singletaxp1.income , 10000000.5F);
        assertEquals(singletaxp1.fullname , "Avgoustinos Zigos");
    }

    @Test
    void calculateBasicTaxHeadOfHouseholdTaxpayerTest(){

        Taxpayer avgoustinos = new HeadOfHouseholdTaxpayer("Avgoustinos Zigos", 987654321, 100000);
        assertEquals(5828.38 + 0.0705 * (100000 - 90000) , avgoustinos.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxMarriedFilingSeparatelyTaxpayerTest(){

        Taxpayer avgoustinos = new MarriedFilingSeparatelyTaxpayer("Avgoustinos Zigos", 987654321, 100000);
        assertEquals(6184.88 + 0.0785 * (100000 - 90000) , avgoustinos.calculateBasicTax());
    }

    @Test
    void calculateBasicTaxMarriedFilingJointlyTaxpayerTest(){

        Taxpayer avgoustinos = new MarriedFilingJointlyTaxpayer("Avgoustinos Zigos", 987654321, 100000);
        assertEquals(5731.64 + 0.0705 * (100000 - 90000) , avgoustinos.calculateBasicTax());
    }

    @Test
    void calculateBasicSingleTaxpayerTest(){

        Taxpayer avgoustinos = new SingleTaxpayer("Avgoustinos Zigos", 987654321, 100000);
        assertEquals(5996.80 + 0.0785 * (100000 - 90000) , avgoustinos.calculateBasicTax());
    }
}