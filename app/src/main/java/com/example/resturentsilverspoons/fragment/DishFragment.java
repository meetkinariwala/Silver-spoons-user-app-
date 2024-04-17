package com.example.resturentsilverspoons.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.HomeActivity;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.api.OrderApi;
import com.example.resturentsilverspoons.model.MenuModel;
import com.example.resturentsilverspoons.model.OrderModel;
import com.example.resturentsilverspoons.signinActivity;
import com.example.resturentsilverspoons.util.ConstantData;

public class DishFragment extends Fragment {

    ImageView image_dish;
    ImageButton back_arrow;

    Button btnaddtocart,btnbuynow;
    TextView tvdname,tvdprice,tvdesc,tvingridient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MenuModel menuModel= (MenuModel) getArguments().getSerializable("food");

        image_dish=view.findViewById(R.id.image_dish);
        tvdprice=view.findViewById(R.id.tvdprice);
        tvdesc=view.findViewById(R.id.tvdesc);
        tvdname=view.findViewById(R.id.tvdname);
        tvingridient=view.findViewById(R.id.tvingridient);
        btnaddtocart=view.findViewById(R.id.btnaddtocart);

        back_arrow=view.findViewById(R.id.back_arrow);


        if (menuModel!=null){

            tvdesc.setText(menuModel.getDishdescription());
            tvdprice.setText("₹"+menuModel.getPrice());
            tvdname.setText(menuModel.getDishname());
            tvingridient.setText(menuModel.getIngredient());
            Glide.with(getContext()).load(menuModel.getPic1()).into(image_dish);

        }

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences=getActivity().getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);
                String Uid=sharedPreferences.getString(ConstantData.KEY_ID,"0");

                if (Uid.equals("0")){
                    Toast.makeText(getActivity(), "Login to continue..... ", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(), signinActivity.class);
                    startActivity(intent);
                }else{
                    OrderModel orderModel=new OrderModel("",Uid,menuModel.getId(),menuModel.getDishname(),menuModel.getPic1(),"","1",menuModel.getPrice(), menuModel.getPrice(),"","0","","");
                    new OrderApi().addOrder(getActivity(),orderModel);

                }

            }
    });



        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).removeFragment(DishFragment.this);
                ((HomeActivity)getActivity()).openFragment(new HomeFragment());

            }
        });



    }
}