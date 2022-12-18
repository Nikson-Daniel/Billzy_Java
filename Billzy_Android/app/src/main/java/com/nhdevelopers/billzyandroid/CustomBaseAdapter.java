package com.nhdevelopers.billzyandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhdevelopers.billzyandroid.R;

public class CustomBaseAdapter extends BaseAdapter {
    Context ctx;

    String[] productName;
    String[] amount;
    String[] quantity;
    String[] total, serialNo;
    LayoutInflater layoutInflater;

    public CustomBaseAdapter(Context context, String[] serialNo, String[] productName, String[] amount, String[] quantity, String[] total) {
        this.ctx = context;
        this.serialNo = serialNo;
        this.productName = productName;
        this.amount = amount;
        this.quantity = quantity;
        this.total = total;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return serialNo.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = layoutInflater.inflate(R.layout.bill_list_image_card, null);

        TextView mProductName = (TextView) convertView.findViewById(R.id.prdNme);
        TextView mAmount = (TextView) convertView.findViewById(R.id.amt);
        TextView mSerialNumber = (TextView) convertView.findViewById(R.id.serialNo);
        TextView mQuantity = (TextView) convertView.findViewById(R.id.qntity);
        TextView mTotal = (TextView) convertView.findViewById(R.id.ttl);

        mSerialNumber.setText((serialNo[position]));
        mProductName.setText((productName[position]));
        mAmount.setText((amount[position]));
        mQuantity.setText((quantity[position]));
        mTotal.setText((total[position]));

        return convertView;
    }
}
