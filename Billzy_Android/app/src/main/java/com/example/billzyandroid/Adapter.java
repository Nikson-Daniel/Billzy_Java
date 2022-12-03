package com.example.billzyandroid;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public List<ModelClass> userList;
    DataStructureClass dataStructureClass;
    MainActivity mainActivity;
    Integer tempQuantity, tempAmount;
    public int positn;


    public Adapter(List<ModelClass> userList) {
        this.userList = userList;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        dataStructureClass = new DataStructureClass();
        mainActivity = new MainActivity();



        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        String mProductName = userList.get(position).getmProductName();
        String mAmount = userList.get(position).getmAmount();
        String mQuantity = userList.get(position).getmQuantity();

        dataStructureClass = new DataStructureClass();


        mainActivity = new MainActivity();


        holder.setData( mProductName, mAmount, mQuantity);

        final TextView prdName = holder.itemView.findViewById(R.id.productName);
        final EditText quantity = holder.itemView.findViewById(R.id.mQuantity);
        final EditText amount = holder.itemView.findViewById(R.id.mAmount);


        positn = holder.getBindingAdapterPosition();

        final ImageView mCloseButton = holder.itemView.findViewById(R.id.mCloseCard);


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





        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainActivity = new MainActivity();

                int sizePst = userList.size()-1;


                if(sizePst > 0 && (position != 0 && position != 1)){
                    mainActivity.addCard(-10);
                    userList.remove(position);

                    notifyItemRemoved(position);

                    for(int i = 0 ; i == userList.size()-1; i++){
                        notifyItemChanged(i);
                        notifyItemRemoved(i);
                    }
                }
                else if ((position == 0 || position == 1)){

                }
                System.out.println(sizePst);


            }
        });

    }



    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView  prdName,  amount,quantity, total;

        public ViewHolder(@NonNull View itemView) {
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
