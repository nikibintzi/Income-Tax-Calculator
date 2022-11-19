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
  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;

  public abstract double calculateBasicTax();
  //my stuff
  public final String[] receiptKindArray = new String[]{"Entertainment", "Basic", "Travel", "Health", "Other"};
  public final double[] basicTaxMultiplier = new double[]{0.08, 0.04, 0.15, 0.3};
  public final double[] variationCheck = new double[]{0.2, 0.4, 0.6};

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
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

  /*public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    if (receipt.getKind().equals("Entertainment")) {
      amountPerReceiptsKind[ENTERTAINMENT] += receipt.getAmount();
    } else if (receipt.getKind().equals("Basic")) {
      amountPerReceiptsKind[BASIC] += receipt.getAmount();
    } else if (receipt.getKind().equals("Travel")) {
      amountPerReceiptsKind[TRAVEL] += receipt.getAmount();
    } else if (receipt.getKind().equals("Health")) {
      amountPerReceiptsKind[HEALTH] += receipt.getAmount();
    } else if (receipt.getKind().equals("Other")) {
      amountPerReceiptsKind[OTHER] += receipt.getAmount();
    } else {
      throw new WrongReceiptKindException();
    }
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }*/

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

  /*public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    if (receipt.getKind().equals("Entertainment")) {
      amountPerReceiptsKind[ENTERTAINMENT] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Basic")) {
      amountPerReceiptsKind[BASIC] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Travel")) {
      amountPerReceiptsKind[TRAVEL] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Health")) {
      amountPerReceiptsKind[HEALTH] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Other")) {
      amountPerReceiptsKind[OTHER] -= receipt.getAmount();
    } else {
      throw new WrongReceiptKindException();
    }
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }*/

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

  //Original
  /*public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    if (totalAmountOfReceipts < 0.2 * income) {
      return calculateBasicTax() * 0.08;
    } else if (totalAmountOfReceipts < 0.4 * income) {
      return calculateBasicTax() * 0.04;
    } else if (totalAmountOfReceipts < 0.6 * income) {
      return -calculateBasicTax() * 0.15;
    } else {
      return -calculateBasicTax() * 0.3;
    }
  }*/

  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for(int i = 1;i < 4;i++){
      if(totalAmountOfReceipts < income * variationCheck[i]){
        //System.out.println(income);
        //System.out.println(basicTaxMultiplier[i]);
        //System.out.println(income * basicTaxMultiplier[i]);
        System.out.println(calculateBasicTax());
        System.out.println(basicTaxMultiplier[i]);
        System.out.println(calculateBasicTax() * basicTaxMultiplier[i]);
        System.out.println(535 * 0.04);
        return (calculateBasicTax() * basicTaxMultiplier[i]);
      }
    }
    return calculateBasicTax() * basicTaxMultiplier[4];
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