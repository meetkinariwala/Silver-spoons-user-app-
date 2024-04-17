package com.example.resturentsilverspoons.api;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.fragment.AllProductFragment;
import com.example.resturentsilverspoons.fragment.HomeFragment;
import com.example.resturentsilverspoons.model.MenuOutputModel;
import com.example.resturentsilverspoons.util.ConstantData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MenuApi {

    public void  Menu(HomeFragment activity){
        Dialog pd = new Dialog(activity.getContext());
        pd.setContentView(R.layout.custom_dialog);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setCancelable(false);
        pd.show();
        RequestQueue requestQueue= Volley.newRequestQueue(activity.getContext());
        String url= ConstantData.SERVERADDRESS+ ConstantData.Menuurl;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();
                        Gson gson = new Gson();
                        MenuOutputModel model=gson.fromJson(response,MenuOutputModel.class);
                        ((HomeFragment)activity).setMenu(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
//                Toast.makeText(activity.getContext(), "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(stringRequest);

    }
    public void  MenuAll(AllProductFragment activity){
        Dialog pd = new Dialog(activity.getContext());
        pd.setContentView(R.layout.custom_dialog);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setCancelable(false);
        pd.show();
        RequestQueue requestQueue= Volley.newRequestQueue(activity.getContext());
        String url= ConstantData.SERVERADDRESS+ ConstantData.Menuurl;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();
                        Gson gson = new Gson();
                        MenuOutputModel model=gson.fromJson(response,MenuOutputModel.class);
                        ((AllProductFragment)activity).setMenu(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
//                Toast.makeText(activity.getContext(), "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(stringRequest);

    }


    public void  MenuAllCat(AllProductFragment activity,String ctype){
        Dialog pd = new Dialog(activity.getContext());
        pd.setContentView(R.layout.custom_dialog);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setCancelable(false);
        pd.show();
        RequestQueue requestQueue= Volley.newRequestQueue(activity.getContext());
        String url= ConstantData.SERVERADDRESS+ ConstantData.GET_MENU_URL;

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();
                        Gson gson = new Gson();
                        MenuOutputModel model=gson.fromJson(response,MenuOutputModel.class);
                        ((AllProductFragment)activity).setMenu(model);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
//                Toast.makeText(activity.getContext(), "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map=new HashMap<>();
                map.put("ctype",ctype);
                return map;

            }
        };


        requestQueue.add(stringRequest);

    }

}
