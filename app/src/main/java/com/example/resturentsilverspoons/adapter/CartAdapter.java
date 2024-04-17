package com.example.resturentsilverspoons.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.model.OrderModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    List<OrderModel> orderModels;
    OnClickListener clickListener;
    Context context;
    public CartAdapter(List<OrderModel> orderModels, CartAdapter.OnClickListener clickListener, Context context) {
        this.orderModels = orderModels;
        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.raw_cart,parent,false);
        CartViewHolder cartViewHolder=new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderModel orderModel=orderModels.get(position);
        holder.tvCartName.setText(orderModel.getPname());
        holder.tvCartPrice.setText(orderModel.getAmount());
//        holder.tvCartSize.setText(orderModel.getPsize());
        Glide.with(context).load(orderModel.getPpic()).into(holder.imgCart);
        holder.tvCartPrice.setText(orderModel.getAmount());
        holder.qty.setText(orderModel.getQuantity());
        holder.tvTotal.setText(orderModel.getTotal_amount());
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.removeOrder(orderModel);
            }
        });

        holder.tvCartPrice.setText(orderModel.getTotal_amount());
        holder.qty_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer count=Integer.parseInt(orderModel.getQuantity());



                if(count==10) {
                    Toast.makeText(context, "Quantity can not be excced...", Toast.LENGTH_SHORT).show();

                }
                else {
                    count = count + 1;
                    orderModel.setQuantity(count+"");
                    double total=count * Double.parseDouble(orderModel.getAmount());
                    holder.tvTotal.setText(orderModel.getTotal_amount());
                    holder.qty.setText(orderModel.getQuantity());
                    clickListener.onClickPlus(orderModel);
                    holder.qty.setText(count+"");
                    holder.tvCartPrice.setText(orderModel.getAmount());

                }
            }
        });

        holder.qty_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer count=Integer.parseInt(orderModel.getQuantity());



                if(count==1) {
                    Toast.makeText(context, "Quantity can not be less then 1...", Toast.LENGTH_SHORT).show();

                }
                else {
                    count = count - 1;
                    orderModel.setQuantity(count+"");
                    double total=count *  Double.parseDouble(orderModel.getAmount());
                    holder.tvTotal.setText(orderModel.getTotal_amount());
                    holder.qty.setText(orderModel.getQuantity());
                    clickListener.onClickPlus(orderModel);
                    holder.qty.setText(count+"");
                    holder.tvCartPrice.setText(orderModel.getAmount());


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCart,btnDeleteCart,imgRemove ;
        TextView tvCartName,tvCartPrice,tvTotal,qty_less,qty_add,qty,tvCartColor,tvCartSize,tvRemove;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCart=itemView.findViewById(R.id.imgcart);

            tvCartName=itemView.findViewById(R.id.tvCartName);
            tvCartPrice=itemView.findViewById(R.id.tvCartPrice);
            qty_less=itemView.findViewById(R.id.qty_less);
            qty_add=itemView.findViewById(R.id.qty_add);
            qty=itemView.findViewById(R.id.qty);
            tvTotal=itemView.findViewById(R.id.tvTotal);
            imgRemove=itemView.findViewById(R.id.imgRemove);

        }
    }
    public  interface  OnClickListener{
        public void onClickPlus(OrderModel orderModel);
        public void onClickMinus(OrderModel orderModel);

        public void removeOrder(OrderModel orderModel);

    }

}