package com.example.billzyandroid;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataStructureClass {
    List<ModelClass> userList;
    Adapter adapter;
    int serialNo = 0;


    public void initData(){
        adapter = new Adapter(userList);
        userList = new ArrayList<>();
    }

    public void addCard(int position){

    }

    public void deleteData(int position){


    }

}
