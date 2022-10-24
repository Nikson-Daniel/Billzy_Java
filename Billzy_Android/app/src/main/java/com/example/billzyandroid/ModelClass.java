package com.example.billzyandroid;

public class ModelClass {

    private String mSerialNo;
    private String mProductName;
    private String mQuantity;
    private String mAmount;
    private String mTotal;

    public ModelClass(String mSerialNo, String mProductName, String mQuantity, String mAmount, String mTotal) {
        this.mSerialNo = mSerialNo;
        this.mProductName = mProductName;
        this.mQuantity = mQuantity;
        this.mAmount = mAmount;
        this.mTotal = mTotal;
    }

    public String getmSerialNo() {
        return mSerialNo;
    }

    public void setmSerialNo(String mSerialNo) {
        this.mSerialNo = mSerialNo;
    }

    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getmTotal() {
        return mTotal;
    }

    public void setmTotal(String mTotal) {
        this.mTotal = mTotal;
    }
}
