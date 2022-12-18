package com.nhdevelopers.billzyandroid;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<ModelClass> userList;

    MainActivity mainActivity;
    Integer tempQuantity, tempAmount;
    TextView prdName;
    EditText quantity, amount;
    public int positn;


    public Adapter(List<ModelClass> userList) {
        this.userList = userList;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);


        mainActivity = new MainActivity();



        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( Adapter.ViewHolder holder, int position) {

        String mProductName = userList.get(position).getmProductName();
        String mAmount = userList.get(position).getmAmount();
        String mQuantity = userList.get(position).getmQuantity();




        mainActivity = new MainActivity();


        holder.setData( mProductName, mAmount, mQuantity);

        prdName = holder.itemView.findViewById(R.id.productName);
        quantity = holder.itemView.findViewById(R.id.mQuantity);
        amount = holder.itemView.findViewById(R.id.mAmount);


        positn = holder.getBindingAdapterPosition();

        final ImageView mCloseButton = holder.itemView.findViewById(R.id.mCloseCard);

        initiateThat(position);







        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( position != userList.size()-1){

                    userList.remove(position);
//                    notifyItemRemoved(position);
                    notifyDataSetChanged();

                }






            }
        });

    }

    public void initiateThat(int position){

        prdName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userList.get(position).setmProductName(prdName.getText().toString());
            }
        });


        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                userList.get(position).setmQuantity(amount.getText().toString());
            }
        });



        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userList.get(position).setmAmount(quantity.getText().toString());
            }
        });

        quantity.setText( userList.get(position).getmAmount());
        amount.setText( userList.get(position).getmQuantity());
        prdName.setText( userList.get(position).getmProductName());

        userList.get(position).setmProductName(prdName.getText().toString());
        userList.get(position).setmQuantity(amount.getText().toString());
        userList.get(position).setmAmount(quantity.getText().toString());

    }

    public void privateInit(int position){
        userList.get(position).setmProductName(prdName.getText().toString());
        userList.get(position).setmQuantity(amount.getText().toString());
        userList.get(position).setmAmount(quantity.getText().toString());
    }




    @Override
    public int getItemCount() {
        return userList.size();
    }

        public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView  prdName, amount, quantity, total;

        public ViewHolder( View itemView) {
            super(itemView);

            prdName = itemView.findViewById(R.id.productName);
            amount = itemView.findViewById(R.id.mAmount);
            quantity = itemView.findViewById(R.id.mQuantity);

        }

        public void setData(String productName, String mQuantity, String mAmount) {
            prdName.setText(productName);
            amount.setText(mAmount);
            quantity.setText(mQuantity);
        }
    }
}
