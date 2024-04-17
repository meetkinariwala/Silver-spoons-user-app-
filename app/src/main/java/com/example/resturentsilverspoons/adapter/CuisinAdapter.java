package com.example.resturentsilverspoons.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.model.CuisinModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CuisinAdapter extends RecyclerView.Adapter<CuisinAdapter.CuisinViewHolder>{

    public ArrayList<CuisinModel> list;
    public Context context;

    public  onClickListener onClickListener;

    public CuisinAdapter(ArrayList<CuisinModel> list, Context context, CuisinAdapter.onClickListener onClickListener) {
        this.list = list;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CuisinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.raw_cuisin, parent, false);
        CuisinViewHolder viewHolder = new CuisinViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CuisinViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getPic()).into(holder.img_cuisin);
        holder.tvcuisinid.setText(list.get(position).getId());
        holder.tvcuisnname.setText(list.get(position).getCname());
        holder.img_cuisin.setOnClickListener(new View.OnClickListener() {
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

    public  class  CuisinViewHolder extends RecyclerView.ViewHolder{

    CircleImageView img_cuisin;
    TextView tvcuisinid,tvcuisnname;


    public CuisinViewHolder(@NonNull View itemView) {
        super(itemView);
        img_cuisin=itemView.findViewById(R.id.img_cuisin);
        tvcuisinid=itemView.findViewById(R.id.tvcuisinid);
        tvcuisnname=itemView.findViewById(R.id.tvcuisnname);


    }
}

public  interface  onClickListener{

        public void onClick(int position,CuisinModel  cuisinModel);
}

}
