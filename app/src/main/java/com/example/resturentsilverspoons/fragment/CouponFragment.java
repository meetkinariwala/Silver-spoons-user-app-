package com.example.resturentsilverspoons.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.adapter.CouponAdapter;
import com.example.resturentsilverspoons.adapter.CuisinAdapter;
import com.example.resturentsilverspoons.api.CouponApi;
import com.example.resturentsilverspoons.model.CouponModel;
import com.example.resturentsilverspoons.model.CouponOutputModel;
import com.example.resturentsilverspoons.model.CuisinModel;


public class CouponFragment extends Fragment {


    View view;

    RecyclerView rcylcoupon;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_coupon, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcylcoupon=view.findViewById(R.id.rcylcoupon);
        new CouponApi().Coupon(CouponFragment.this);
    }

    public void setCoupon(CouponOutputModel coupon){

        CouponAdapter couponAdapter= new CouponAdapter(coupon.getCoupon(), getContext(), new CouponAdapter.onClickListener() {
            @Override
            public void onClick(int position, CouponModel couponModel) {

            }
        });
        rcylcoupon.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rcylcoupon.setAdapter(couponAdapter);

    }
}