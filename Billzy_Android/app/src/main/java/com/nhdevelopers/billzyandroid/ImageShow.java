package com.nhdevelopers.billzyandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.nhdevelopers.billzyandroid.databinding.ActivityImageShowBinding;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ImageShow extends AppCompatActivity {

    private ActivityImageShowBinding binding;


    List<ModelClass> userList;
    String[] serialNo;
    String[] quantity, amount, total, productName;
    String date, shopName, recipientName;
    int sum;
    private Bitmap bitmap;
    RewardedAd mRewardedads;
    private static final int REQUEST_CODE_PERMISSION = 2;

    private ConstraintLayout layout;


    public ImageShow() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImageShowBinding.inflate(getLayoutInflater());

        String[] mPermission = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};


        View view = binding.getRoot();
        setContentView(view);
        loadAds();

        sum = 0;

        Intent i = getIntent();
        userList = (List<ModelClass>) i.getSerializableExtra("LIST");
        date = i.getStringExtra("date");
        shopName = i.getStringExtra("ShopName");
        recipientName = i.getStringExtra("RecipientsName");




        serialNo = new String[userList.size()];
        quantity = new String[userList.size()];
        amount = new String[userList.size()];
        total = new String[userList.size()];
        productName = new String[userList.size()];

        binding.mQuantityImg.setText(String.valueOf(userList.size()));
        binding.mDateImg.setText(date);
        binding.mShopImg.setText(shopName);
        binding.mRecipientImg.setText("To, "+recipientName);

        binding.mDownloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadAds();
                showAds();
            }
        });


        for (int j = 0; j<userList.size(); j++){
            serialNo[j] = String.valueOf(j+1);
        }


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

        for (int k = 0; k < total.length; k++) {
            sum = sum + Integer.parseInt(total[k]);
        }

        binding.mGrandTotalImg.setText(sum+".00");

        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),serialNo, productName, amount, quantity, total);
        binding.billListImg.setAdapter(customBaseAdapter);
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;



        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String uniqueString = UUID.randomUUID().toString();
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+uniqueString+".pdf";

        File filePath;
        filePath = new File(path);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "Your bill Downloaded on\n/storage/Downloads", Toast.LENGTH_SHORT).show();



    }

    private void showAds(){
        if (mRewardedads!=null){
            mRewardedads.show(ImageShow.this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    bitmap = loadBitmapFromView(binding.bill, binding.bill.getWidth(), binding.bill.getHeight());
                    createPdf();
                }
            });
        }
        else{
            bitmap = loadBitmapFromView(binding.bill, binding.bill.getWidth(), binding.bill.getHeight());
            createPdf();
        }
    }

    private void loadAds(){

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-9797272882939142/1785559694", adRequest, new RewardedAdLoadCallback() {
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