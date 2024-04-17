package com.example.resturentsilverspoons.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.api.OrderApi;
import com.example.resturentsilverspoons.model.CuisinModel;
import com.example.resturentsilverspoons.model.MenuModel;
import com.example.resturentsilverspoons.model.OrderModel;
import com.example.resturentsilverspoons.signinActivity;
import com.example.resturentsilverspoons.util.ConstantData;

import java.util.ArrayList;

import co.ankurg.expressview.ExpressView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    public ArrayList<MenuModel> list;
    public Context context;

    public  onClickListener onClickListener;

    public MenuAdapter(ArrayList<MenuModel> list, Context context, onClickListener
            onClickListener) {
        this.list = list;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.raw_dishes, parent, false);
        MenuViewHolder viewHolder = new MenuViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getPic1()).into(holder.img_dish);
        holder.tvmenuid.setText(list.get(position).getId());
        holder.tvdishname.setText(list.get(position).getDishname());
       // holder.tvdescription.setText(list.get(position).getDishdescription());
        holder.tvprice.setText("Rs."+list.get(position).getPrice());
        holder.tvctype.setText(list.get(position).getCtype());


        holder.img_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position,list.get(position));
            }
        });









    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  MenuViewHolder extends RecyclerView.ViewHolder{

        ImageView img_dish;

        Button btn_cart;
        TextView tvmenuid,tvdescription,tvdishname,tvprice,tvctype;

        ExpressView likeButton;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            img_dish=itemView.findViewById(R.id.img_dish);
            tvmenuid=itemView.findViewById(R.id.tvmenuid);
//            tvdescription=itemView.findViewById(R.id.tvdescription);
            tvdishname=itemView.findViewById(R.id.tvdishname);
            tvprice=itemView.findViewById(R.id.tvprice);
            tvctype=itemView.findViewById(R.id.tvctype);
            likeButton=itemView.findViewById(R.id.likeButton);
//            btn_cart=itemView.findViewById(R.id.btn_cart);




        }
    }

    public  interface  onClickListener{

        public void onClick(int position,MenuModel  menuModel);
    }

}
