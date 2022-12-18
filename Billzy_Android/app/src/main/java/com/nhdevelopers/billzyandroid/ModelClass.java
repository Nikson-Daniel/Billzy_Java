package com.nhdevelopers.billzyandroid;

import java.io.Serializable;

public class ModelClass implements Serializable {


    private String mProductName;
    private String mAmount;
    private String mQuantity;


    public ModelClass(String mProductName, String mAmount,String mQuantity) {

        this.mProductName = mProductName;
        this.mAmount = mAmount;
        this.mQuantity = mQuantity;
    }


    public String getmProductName() {
        return mProductName;
    }

    public void setmProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(String mQuantity) {
        this.mQuantity = mQuantity;
    }


}
