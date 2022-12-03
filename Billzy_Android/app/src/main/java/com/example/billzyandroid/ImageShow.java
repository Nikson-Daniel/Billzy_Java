package com.example.billzyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;

import com.example.billzyandroid.databinding.ActivityImageShowBinding;
import com.example.billzyandroid.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class ImageShow extends AppCompatActivity {

    private ActivityImageShowBinding binding;
    Adapter adapter;
    MainActivity mainActivity;
    DataStructureClass dataStructureClass;
    List<ModelClass> userList;
    String[] serialNo;
    String[] quantity, amount, total, productName;


    public ImageShow() {




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageShowBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        Intent i = getIntent();
        userList = (List<ModelClass>) i.getSerializableExtra("LIST");


        serialNo = new String[userList.size()];
        quantity = new String[userList.size()];
        amount = new String[userList.size()];
        total = new String[userList.size()];
        productName = new String[userList.size()];






        for (int j = 0; j<userList.size(); j++){
            serialNo[j] = String.valueOf(j+1);
        }
        System.out.println(serialNo[0]);

        for (int j = 0; j<userList.size(); j++){
            quantity[j] = userList.get(j).getmQuantity();
        }


        for (int j = 0; j<userList.size(); j++){
            productName[j] = userList.get(j).getmProductName();
        }


        for (int j = 0; j<userList.size(); j++){
            amount[j] = userList.get(j).getmAmount();
        }


        for (int j = 0; j<userList.size(); j++){
            total[j] = String.valueOf(Integer.parseInt(userList.get(j).getmAmount())*Integer.parseInt(userList.get(j).getmQuantity()));
        }


        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),serialNo, productName, amount, quantity, total);
        binding.billListImg.setAdapter(customBaseAdapter);
    }


}