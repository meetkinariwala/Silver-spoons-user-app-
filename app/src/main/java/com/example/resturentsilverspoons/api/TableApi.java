package com.example.resturentsilverspoons.api;

import android.app.Activity;
import android.app.Dialog;
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
import com.example.resturentsilverspoons.model.OrderOutputModel;
import com.example.resturentsilverspoons.model.TableModel;
import com.example.resturentsilverspoons.model.TableOutputModel;
import com.example.resturentsilverspoons.util.ConstantData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class TableApi {

    public void addtable(Activity activity, TableModel model){

        Dialog pd = new Dialog(activity);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL = ConstantData.SERVERADDRESS + ConstantData.TABLE_BOOKINGURL;
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                pd.dismiss();

                //this orderoutputmodel is use for refrence
//                OrderOutputModel personOutputModel = gson.fromJson(response, OrderOutputModel.class);

                TableOutputModel tableOutputModel=gson.fromJson(response,TableOutputModel.class);

                Toast.makeText(activity, tableOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(activity, response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(activity, "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("guest",model.getGuest());
                map.put("date",model.getDate());
                map.put("time",model.getTime());
                map.put("tnumber",model.getTnumber());
                return map;
            }
        };


        queue.add(stringRequest);

    }


}
