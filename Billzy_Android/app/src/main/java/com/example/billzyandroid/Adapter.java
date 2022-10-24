package com.example.billzyandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;

    public Adapter(List<ModelClass> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        String mSerialNo = userList.get(position).getmSerialNo();
        String mProductName = userList.get(position).getmProductName();
        String mAmount = userList.get(position).getmAmount();
        String mQuantity = userList.get(position).getmQuantity();
        String mTotal = userList.get(position).getmTotal();


        holder.setData(mSerialNo, mProductName, mAmount, mQuantity, mTotal);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView sNo, prdName, quantity, amount, total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sNo = itemView.findViewById(R.id.serialNo);
            prdName = itemView.findViewById(R.id.productName);
            quantity = itemView.findViewById(R.id.mQuantity);
            amount = itemView.findViewById(R.id.mAmount);
            total = itemView.findViewById(R.id.mTotalAmount);


        }

        public void setData(String serialNo,String productName, String mQuantity, String mAmount, String mTotal) {
            sNo.setText(serialNo);
            prdName.setText(productName);
            quantity.setText(mQuantity);
            amount.setText(mAmount);
            total.setText(mTotal);

        }
    }
}
