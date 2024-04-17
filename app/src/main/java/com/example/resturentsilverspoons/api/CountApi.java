package com.example.resturentsilverspoons.api;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.fragment.HomeFragment;
import com.example.resturentsilverspoons.model.CountOutputModel;
import com.example.resturentsilverspoons.util.ConstantData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class CountApi {
    public void getCount(HomeFragment context){
        Dialog pd = new Dialog(context.getContext());
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();


        RequestQueue queue= Volley.newRequestQueue(context.getContext());

        String url= ConstantData.SERVERADDRESS + ConstantData.COUNT_URL;


        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Gson gson=new Gson();
                CountOutputModel bannerOutputModel=gson.fromJson(response,CountOutputModel.class);
//                Toast.makeText(context, bannerOutputModel.getCount(), Toast.LENGTH_SHORT).show();
                ConstantData.COUNT=bannerOutputModel.getCount();

//                ((HomeFragment)context).updateCartCounter();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(context.getContext(), "error"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences sharedPreferences = context.getContext().getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);

                String uid=sharedPreferences.getString(ConstantData.KEY_ID,"0");
                HashMap<String,String> map=new HashMap<>();
                map.put("uid",uid);
                return map;
            }
        };

        queue.add(stringRequest);
    }

}
