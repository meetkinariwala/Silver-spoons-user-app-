package com.example.resturentsilverspoons.api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import com.example.resturentsilverspoons.CartActivity;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.fragment.MyOrderFragment;
import com.example.resturentsilverspoons.model.OrderModel;
import com.example.resturentsilverspoons.model.OrderOutputModel;
import com.example.resturentsilverspoons.model.PersonOutputModel;
import com.example.resturentsilverspoons.signinActivity;
import com.example.resturentsilverspoons.util.ConstantData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class OrderApi {

    public void addOrder(Activity activity, OrderModel model) {
        Dialog pd = new Dialog(activity);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL = ConstantData.SERVERADDRESS + ConstantData.OrderUrl;
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                pd.dismiss();
                OrderOutputModel personOutputModel = gson.fromJson(response, OrderOutputModel.class);


                if (personOutputModel.isStatus()) {
                    Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(activity, response, Toast.LENGTH_SHORT).show();


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
                map.put("uid",model.getUid());
                map.put("pid",model.getPid());
                map.put("ppic",model.getPpic());
                map.put("pname",model.getPname());
                map.put("amount",model.getAmount());
                map.put("total_amount",model.getTotal_amount());
                return map;
            }
        };


        queue.add(stringRequest);

    }


    public void getOrder(String uid, CartActivity activity) {
        Dialog pd = new Dialog(activity);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL = ConstantData.SERVERADDRESS + ConstantData.GetOrderUrl;
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                pd.dismiss();
                OrderOutputModel orderOutputModel = gson.fromJson(response, OrderOutputModel.class);

                if (orderOutputModel.isStatus()) {
                    ((CartActivity)activity).setcart_adapter(orderOutputModel);

                    Toast.makeText(activity, orderOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                }



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
                map.put("uid",uid);

                return map;
            }
        };


        queue.add(stringRequest);

    }


    public void getMyOrder(String uid, MyOrderFragment activity) {
        Dialog pd = new Dialog(activity.getContext());
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL = ConstantData.SERVERADDRESS + ConstantData.MyOrderUrl;
        RequestQueue queue = Volley.newRequestQueue(activity.getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                pd.dismiss();
                OrderOutputModel orderOutputModel = gson.fromJson(response, OrderOutputModel.class);

                ((MyOrderFragment)activity).setAdapter(orderOutputModel);
                if (orderOutputModel.isStatus()) {
                    Toast.makeText(activity.getContext(), orderOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(activity.getContext(), "error:" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("uid",uid);

                return map;
            }
        };


        queue.add(stringRequest);

    }

    public void remove_order(String id, CartActivity activity) {
        Dialog pd = new Dialog(activity);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL = ConstantData.SERVERADDRESS + ConstantData.REMOVE_ORDER_URL;
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                pd.dismiss();
                OrderOutputModel orderOutputModel = gson.fromJson(response, OrderOutputModel.class);

                if (orderOutputModel.isStatus()) {
                    ((CartActivity)activity).cart();
                }



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
                map.put("id",id);

                return map;
            }
        };


        queue.add(stringRequest);

    }


    public void update_qty_order(String id,String qty, CartActivity activity) {
        Dialog pd = new Dialog(activity);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL = ConstantData.SERVERADDRESS + ConstantData.QUANTITY_ORDER_URL;
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                pd.dismiss();
                OrderOutputModel orderOutputModel = gson.fromJson(response, OrderOutputModel.class);

                ((CartActivity)activity).setcart_adapter(orderOutputModel);
                if (orderOutputModel.isStatus()) {
                    ((CartActivity)activity).cart();
                }



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
                map.put("id",id);
                map.put("quantity",qty);

                return map;
            }
        };


        queue.add(stringRequest);

    }


    public void order_confirm(String uid,String payment_type,String total, CartActivity activity) {
        Dialog pd = new Dialog(activity);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL = ConstantData.SERVERADDRESS + ConstantData.CONFIRM_ORDER_URL;
        RequestQueue queue = Volley.newRequestQueue(activity);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                pd.dismiss();
                OrderOutputModel orderOutputModel = gson.fromJson(response, OrderOutputModel.class);

                if (orderOutputModel.isStatus()) {
                    ((CartActivity)activity).done();
                }



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
                map.put("uid",uid);
                map.put("payment_type",payment_type);
                map.put("total_amount",total);

                return map;
            }
        };


        queue.add(stringRequest);

    }

}
