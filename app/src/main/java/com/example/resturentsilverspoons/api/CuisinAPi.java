package com.example.resturentsilverspoons.api;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.fragment.HomeFragment;
import com.example.resturentsilverspoons.model.CuisinOutputModel;
import com.example.resturentsilverspoons.util.ConstantData;
import com.google.gson.Gson;

public class CuisinAPi {
    public void  Cuisin(HomeFragment activity){
        Dialog pd = new Dialog(activity.getContext());
        pd.setContentView(R.layout.custom_dialog);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setCancelable(false);
        pd.show();
        RequestQueue requestQueue= Volley.newRequestQueue(activity.getContext());
        String url= ConstantData.SERVERADDRESS+ ConstantData.CuisinUrl;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();
                        Gson gson = new Gson();
                        CuisinOutputModel model=gson.fromJson(response,CuisinOutputModel.class);
                        ((HomeFragment)activity).setCuisin(model);
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
}
