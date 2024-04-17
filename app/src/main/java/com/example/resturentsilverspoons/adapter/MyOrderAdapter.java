package com.example.resturentsilverspoons.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.model.OrderModel;
import com.transferwise.sequencelayout.SequenceLayout;
import com.transferwise.sequencelayout.SequenceStep;

import java.util.ArrayList;
import java.util.List;



public class MyOrderAdapter  extends RecyclerView.Adapter<MyOrderAdapter.CartViewHolder> {
    List<OrderModel> orderModels;
    CartAdapter.OnClickListener clickListener;
    ArrayList<String> steps;
    Context context;

    public MyOrderAdapter(List<OrderModel> orderModels, CartAdapter.OnClickListener clickListener, Context context) {
        this.orderModels = orderModels;
        this.clickListener = clickListener;
        this.context = context;
        steps=new ArrayList<>();
        steps.add("Received");
        steps.add("Process");
        steps.add("Delivered");
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_orders, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderModel orderModel = orderModels.get(position);
        Glide.with(context).load(orderModel.getPpic()).into(holder.imgCart);
        holder.tvCartName.setText(orderModel.getPname());
        holder.tvCartPrice.setText(orderModel.getAmount());
        holder.tvTotal.setText(orderModel.getTotal_amount());
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.removeOrder(orderModel);
            }
        });


        holder.llytMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.VISIBLE);
            }
        });

        holder.tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.GONE);

            }
        });
        holder.step1.setSubtitle("Order Received from Silver Spoons");
        holder.step2.setSubtitle("Order is being Process");
        holder.step3.setSubtitle("Order Completed");






        if(orderModel.getStatus().equals("1")){
            holder.step1.setActive(true);
        }else if(orderModel.getStatus().equals("2")){
            holder.step2.setActive(true);

        }else{
            holder.step3.setActive(true);

        }



    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCart, btnDeleteCart, imgRemove;
        SequenceLayout tracker;
        LinearLayout llytMyOrders;
        SequenceStep step1,step2,step3,step4,step5;
        TextView tvCartName, tvCartPrice, tvTotal, tvQty, tvSize;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.imgcart);

            tvCartName = itemView.findViewById(R.id.tvCartName);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
//            tvCartSize=itemView.findViewById(R.id.cartShoesSize);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            imgRemove = itemView.findViewById(R.id.imgRemove);
            step1=itemView.findViewById(R.id.step1);
            step2=itemView.findViewById(R.id.step2);
            step3=itemView.findViewById(R.id.step3);

            tracker=itemView.findViewById(R.id.tracker);
            llytMyOrders=itemView.findViewById(R.id.llytMyOrders);



        }
    }

    public interface OnClickListener {
        public void onClickPlus(OrderModel orderModel);

        public void onClickMinus(OrderModel orderModel);

        public void removeOrder(OrderModel orderModel);

    }

}