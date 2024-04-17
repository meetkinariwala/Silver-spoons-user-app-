package com.example.resturentsilverspoons.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.resturentsilverspoons.adapter.CartAdapter;
import com.example.resturentsilverspoons.adapter.MyOrderAdapter;
import com.example.resturentsilverspoons.api.OrderApi;
import com.example.resturentsilverspoons.model.OrderModel;
import com.example.resturentsilverspoons.model.OrderOutputModel;
import com.example.resturentsilverspoons.util.ConstantData;
import com.google.android.material.shadow.ShadowRenderer;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class MyOrderFragment extends Fragment {


    RecyclerView rcylOrders;






    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_order, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcylOrders=view.findViewById(R.id.rcylOrders);


        rcylOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sp=getActivity().getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);
        String uid=sp.getString(ConstantData.KEY_ID,"0");
        new OrderApi().getMyOrder(uid,this);
    }

    public void setAdapter(OrderOutputModel model){

        MyOrderAdapter myOrderAdapter=new MyOrderAdapter(model.getOrder(), new CartAdapter.OnClickListener() {
            @Override
            public void onClickPlus(OrderModel orderModel) {

            }

            @Override
            public void onClickMinus(OrderModel orderModel) {

            }

            @Override
            public void removeOrder(OrderModel orderModel) {

            }
        },getContext());

        rcylOrders.setAdapter(myOrderAdapter);
    }

}