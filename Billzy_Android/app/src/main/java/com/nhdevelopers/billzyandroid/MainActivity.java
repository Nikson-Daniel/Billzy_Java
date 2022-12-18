package com.nhdevelopers.billzyandroid;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;


import com.nhdevelopers.billzyandroid.R;
import com.nhdevelopers.billzyandroid.databinding.ActivityMainBinding;
import com.nhdevelopers.billzyandroid.databinding.CardBinding;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

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
    int serialNo;
    private CardBinding cardBinding;
    RewardedAd mRewardedads;
    ActionBarDrawerToggle toggle;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        userList = new ArrayList<ModelClass>();

        serialNo = -1;

        initRecyclerView();
        loadAds();



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
                    loadAds();
                    showAds();
            }
        });

        binding.tlbr.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawer.openDrawer(GravityCompat.START);

            }
        });


        cardBinding = CardBinding.inflate(getLayoutInflater());


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        binding.BannerAdView1.loadAd(adRequest);

//        binding.navDraw.aboutUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://billzyatnhdevelopers.blogspot.com/2022/12/privacy-policy.html"));
//                startActivity(i);
//
//            }
//        });
//
//        binding.navDraw.termsOfServices.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://billzyatnhdevelopers.blogspot.com/2022/12/terms-and-conditions.html"));
//                startActivity(i);
//
//            }
//        });
//
//        binding.navDraw.aboutUs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://nhdevelopersofficial.blogspot.com/2022/03/about-us.html"));
//                startActivity(i);
//            }
//        });

    }




    @Override
    public void onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }



    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
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

        if (sno == -5 && serialNo == -1 && adapter.userList.size()!=10){
            adapter.userList.add(new ModelClass(" ", "0", "0"));
            adapter.notifyItemChanged(serialNo);
        }
        else {
            Toast.makeText(this, "Cannot add more than 10 items", Toast.LENGTH_SHORT).show();
        }

    }

    private void showAds(){
        if (mRewardedads!=null){
            mRewardedads.show(MainActivity.this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                    userList = adapter.userList;
                    System.out.println(userList.size());


                    if(userList.size() == 0){
                        Toast.makeText(MainActivity.this, "You have to enter any values\n No values should be empty", Toast.LENGTH_SHORT).show();
                    }

                    else{

                        System.out.println(adapter.userList.get(0).getmProductName());
                        Intent intent = new Intent(MainActivity.this, ImageShow.class);
                        intent.putExtra("LIST", (Serializable) userList);
                        intent.putExtra("date", binding.mDateText.getText().toString());
                        intent.putExtra("ShopName", binding.mShopname.getText().toString());
                        intent.putExtra("RecipientsName", binding.mRecipientsName.getText().toString());
                        startActivity(intent);
                    }
                }

            });
        }
        else{
            userList = adapter.userList;
            System.out.println(userList.size());


            if(userList.size() == 0){
                Toast.makeText(MainActivity.this, "You have to enter any values\n No values should be empty", Toast.LENGTH_SHORT).show();
            }

            else{

                System.out.println(adapter.userList.get(0).getmProductName());
                Intent intent = new Intent(MainActivity.this, ImageShow.class);
                intent.putExtra("LIST", (Serializable) userList);
                intent.putExtra("date", binding.mDateText.getText().toString());
                intent.putExtra("ShopName", binding.mShopname.getText().toString());
                intent.putExtra("RecipientsName", binding.mRecipientsName.getText().toString());
                startActivity(intent);
            }
        }

        }


    private void loadAds(){

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-9797272882939142/7884064181", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mRewardedads = null;
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                super.onAdLoaded(rewardedAd);
                mRewardedads = rewardedAd;

                rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                    }
                });
            }
        });

    }

}