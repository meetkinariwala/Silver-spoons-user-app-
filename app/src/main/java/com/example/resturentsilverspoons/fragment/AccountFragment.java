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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.ContactUsActivity;
import com.example.resturentsilverspoons.Edit_profile_Activity;
import com.example.resturentsilverspoons.HomeActivity;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.api.OrderApi;
import com.example.resturentsilverspoons.api.RegisterApi;
import com.example.resturentsilverspoons.model.MenuModel;
import com.example.resturentsilverspoons.model.PersonModel;
import com.example.resturentsilverspoons.model.PersonOutputModel;
import com.example.resturentsilverspoons.signinActivity;
import com.example.resturentsilverspoons.util.ConstantData;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

    Button btn_logout,btn_edit_profile,btncontactus,btnMyorder;

    TextView tvusername,tvPhone,tvEmail;

    CircleImageView profile_imageaccount;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_account, container, false);
        return view;




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);

       // PersonModel personModel= (PersonModel) getArguments().getSerializable("person");

        btn_logout=view.findViewById(R.id.btn_logout);
        btn_edit_profile=view.findViewById(R.id.btn_edit_profile);
        tvusername=view.findViewById(R.id.tvusername);
        tvPhone=view.findViewById(R.id.tvPhone);
        tvEmail=view.findViewById(R.id.tvEmail);
        btncontactus=view.findViewById(R.id.btncontactus);
        btnMyorder=view.findViewById(R.id.btnMyorder);
        profile_imageaccount=view.findViewById(R.id.profile_imageaccount);

        tvusername.setText(sharedPreferences.getString(ConstantData.KEY_USERNAME,""));
        tvEmail.setText(sharedPreferences.getString(ConstantData.KEY_EMAIL,""));
        tvPhone.setText(sharedPreferences.getString(ConstantData.KEY_PHONE,""));

        String pic=sharedPreferences.getString(ConstantData.KEY_PIC,"");


        if(pic.toString().trim().length()==0) {
            Glide.with(this).load(R.mipmap.profile).into(profile_imageaccount);
        } else {
            Glide.with(this).load(ConstantData.SERVERADDRESS+"/images/"+pic).into(profile_imageaccount);
        }





        btnMyorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ((HomeActivity)getContext()).openFragment(new MyOrderFragment());
            }
        });




        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(requireContext(), signinActivity.class);
                startActivity(intent);
            }
        });

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(requireContext(), Edit_profile_Activity.class);
                startActivity(intent);
            }
        });

        btncontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(requireContext(), ContactUsActivity.class);
                startActivity(intent);

            }
        });


    }

}