package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);

  public final String[] receiptKindArray = new String[]{"Entertainment", "Basic", "Travel", "Health", "Other"};
  public final double[] basicTaxMultiplier = new double[]{0.08, 0.04, 0.15, 0.3};
  public final double[] variationCheck = new double[]{0.2, 0.4, 0.6};

  protected double[] incomeComparisonArray = new double[4];
  protected double[] returnVarArray = new double[5];

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
  }

  public double calculateBasicTax(){
    if (income < incomeComparisonArray[0]) {
      return returnVarArray[0];
    } else if (income < incomeComparisonArray[1]) {
      return returnVarArray[1];
    } else if (income < incomeComparisonArray[2]) {
      return returnVarArray[2];
    } else if (income < incomeComparisonArray[3]) {
      return returnVarArray[3];
    } else {
      return returnVarArray[4];
    }
  }

  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    for (int i = 0; i < 5; i++) {
      if (receipt.getKind().equals(receiptKindArray[i])) {
        amountPerReceiptsKind[i] += receipt.getAmount();
        i = 5;
      }
      if(i == 4)
      {
        throw new WrongReceiptKindException();
      }
    }
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }

  public void removeReceipt(int receiptId) throws WrongReceiptKindException{
    Receipt receipt = receiptHashMap.get(receiptId);
    for (int i = 0; i < 5; i++) {
      if (receipt.getKind().equals(receiptKindArray[i])) {
        amountPerReceiptsKind[i] -= receipt.getAmount();
        i = 5;
      }
      if(i == 4)
      {
        throw new WrongReceiptKindException();
      }
    }
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for(int i = 0;i < 3;i++){
      if(totalAmountOfReceipts < income * variationCheck[i]){
        return (calculateBasicTax() * basicTaxMultiplier[i]);
      }
    }
    return calculateBasicTax() * basicTaxMultiplier[3];
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }

}