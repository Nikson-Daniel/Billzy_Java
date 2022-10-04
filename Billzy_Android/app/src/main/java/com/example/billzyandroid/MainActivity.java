package com.example.billzyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;


import com.example.billzyandroid.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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

        binding.mCard.addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });

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

    private void addCard(){
        LinearLayout ll = binding.ll;
        View element = LayoutInflater.from(this).inflate(R.layout.card, ll, false);
        ll.addView(element);
    }
}