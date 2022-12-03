package com.example.billzyandroid;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.billzyandroid.databinding.ActivityMainBinding;
import com.example.billzyandroid.databinding.CardBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;
    EditText qntity, amount;
    int mQuantity, mAmount, mTotal;
    int serialNo;
    String mProductName;
    private CardBinding cardBinding;
    DataStructureClass storeClass;

    public MainActivity(){

        this.serialNo = -1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        storeClass = new DataStructureClass();
        storeClass.initData();


        initRecyclerView();

        binding.mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        binding.tlbr.mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolBarChk();
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard(-5);
            }
        });

        binding.tlbr.mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList = adapter.userList;
                System.out.println(userList.size());

                if(userList.size() == 0){
                    Toast.makeText(MainActivity.this, "You have to enter any values\n No values should be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, ImageShow.class);
                    intent.putExtra("LIST", (Serializable) userList);
                    startActivity(intent);
                }
            }
        });

        cardBinding = CardBinding.inflate(getLayoutInflater());

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(storeClass.userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String showDate =  date+" / "+(month+1)+" / "+year;
                binding.mDateText.setText(showDate);
                binding.mDateButton.setText(R.string.change_date);
            }
        }, year, month, date);
        datePickerDialog.show();
    }

    private void toolBarChk(){

    }

    public void addCard(int sno){
        if(sno == -5){
            adapter.userList.add(new ModelClass(null, null, null));
            adapter.notifyItemChanged(serialNo);
            serialNo++;
            System.out.println(adapter.userList.get(0).getmProductName());
        }

        if (sno == -10){

        }

    }

}