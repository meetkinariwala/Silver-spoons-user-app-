package com.example.resturentsilverspoons.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.CartActivity;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.model.CouponModel;
import com.example.resturentsilverspoons.model.CuisinModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder>{

    public ArrayList<CouponModel> list;
    public Context context;

    public  onClickListener onClickListener;

    public CouponAdapter(ArrayList<CouponModel> list, Context context, CouponAdapter.onClickListener onClickListener) {
        this.list = list;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.raw_coupon, parent, false);
        CouponViewHolder viewHolder = new CouponViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {


      Glide.with(context).load(list.get(position).getPic()).into(holder.img_Coupon);
        holder.tvcouponid.setText(list.get(position).getId());
        holder.tvCoupontitle.setText(list.get(position).getCoupontitle());
        holder.tvCoupnCode.setText(list.get(position).getCouponcode());
        holder.tvcoupondesc.setText(list.get(position).getCoupondesc());
        holder.tvcoupondiscount.setText(list.get(position).getCoupondiscount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  CouponViewHolder extends RecyclerView.ViewHolder{


        ImageView img_Coupon;

        ImageButton btncodecopy;
        TextView tvCoupontitle,tvCoupnCode,tvcoupondesc,tvcouponid,tvcoupondiscount;


        public CouponViewHolder(@NonNull View itemView) {
            super(itemView);
            img_Coupon=itemView.findViewById(R.id.img_Coupon);
            tvcouponid=itemView.findViewById(R.id.tvcouponid);
            tvCoupontitle=itemView.findViewById(R.id.tvCoupontitle);
            tvCoupnCode=itemView.findViewById(R.id.tvCoupnCode);
            tvcoupondesc=itemView.findViewById(R.id.tvcoupondesc);
            tvcoupondiscount=itemView.findViewById(R.id.tvcoupondiscount);
            btncodecopy=itemView.findViewById(R.id.btncodecopy);

            btncodecopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the coupon code from tvCoupnCode
                    if(context instanceof CartActivity){
                        ((CartActivity)context).visible();
                        String couponCode = tvCoupnCode.getText().toString();

                        // Copy the coupon code to the clipboard
                        ClipboardManager clipboardManager = (ClipboardManager) itemView.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("Coupon Code", couponCode);
                        clipboardManager.setPrimaryClip(clipData);

                        // Show a toast or perform any other actions if needed
                        Toast.makeText(itemView.getContext(), "Coupon code copied", Toast.LENGTH_SHORT).show();
                    }
                    String couponCode = tvCoupnCode.getText().toString();

                    // Copy the coupon code to the clipboard
                    ClipboardManager clipboardManager = (ClipboardManager) itemView.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("Coupon Code", couponCode);
                    clipboardManager.setPrimaryClip(clipData);

                    // Show a toast or perform any other actions if needed
                    Toast.makeText(itemView.getContext(), "Coupon code copied", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public  interface  onClickListener{

        public void onClick(int position,CouponModel  couponModel);
    }

}
