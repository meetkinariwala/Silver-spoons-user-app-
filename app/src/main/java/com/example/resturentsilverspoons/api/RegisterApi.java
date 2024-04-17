package com.example.resturentsilverspoons.api;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.resturentsilverspoons.HomeActivity;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.signinActivity;
import com.example.resturentsilverspoons.util.ConstantData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.resturentsilverspoons.model.PersonModel;
import com.example.resturentsilverspoons.model.PersonOutputModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RegisterApi {
    public void register_user(PersonModel model, Activity activity)
    {
        Dialog pd = new Dialog(activity);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setContentView(R.layout.custom_dialog);
        pd.setCancelable(false);
        pd.show();

        String URL= ConstantData.SERVERADDRESS + ConstantData.REGISTERURL;
        RequestQueue queue= Volley.newRequestQueue(activity);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                pd.dismiss();
                PersonOutputModel personOutputModel=gson.fromJson(response,PersonOutputModel.class);
                if (personOutputModel.isStatus()){
                    SharedPreferences sharedPreferences=activity.getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(ConstantData.KEY_USERNAME,personOutputModel.getPerson().getUsername());
                    editor.putString(ConstantData.KEY_EMAIL,personOutputModel.getPerson().getEmail());
                    editor.putString(ConstantData.KEY_PHONE,personOutputModel.getPerson().getPhno());
                    editor.putString(ConstantData.KEY_ID,personOutputModel.getPerson().getId());
                    editor.putString(ConstantData.KEY_PIC,personOutputModel.getPerson().getPic());
                    editor.commit();
                    Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(activity, signinActivity.class);
                    activity.startActivity(i);
                }
                Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(activity, response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(activity, "error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username", model.getUsername());
                map.put("password",model.getPass());
                map.put("email",model.getEmail());
                map.put("phone",model.getPhno());

                return map;
            }
        };


        queue.add(stringRequest);
}


    public void login_user(PersonModel model, Activity activity)
    {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.setMessage("loading");
        pd.setCancelable(false);

        pd.show();

        String URL= ConstantData.SERVERADDRESS + ConstantData.LOGIN_USERURL;
        RequestQueue queue= Volley.newRequestQueue(activity);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                pd.dismiss();

                PersonOutputModel personOutputModel=gson.fromJson(response,PersonOutputModel.class);
                if (personOutputModel.isStatus()){
                    SharedPreferences sharedPreferences=activity.getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(ConstantData.KEY_USERNAME,personOutputModel.getPerson().getUsername());
                    editor.putString(ConstantData.KEY_EMAIL,personOutputModel.getPerson().getEmail());
                    editor.putString(ConstantData.KEY_PHONE,personOutputModel.getPerson().getPhno());
                    editor.putString(ConstantData.KEY_ID,personOutputModel.getPerson().getId());
                    editor.commit();
                    Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(activity, HomeActivity.class);
                    activity.startActivity(i);
                }
                if (personOutputModel.isStatus()){
                    //session
                    Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(activity, HomeActivity.class);
                    activity.startActivity(i);
                }
                Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(activity, response, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(activity, "error:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username", model.getUsername());
                map.put("pass",model.getPass());

                return map;
            }
        };


        queue.add(stringRequest);
    }


    public void edit_user(PersonModel model, Activity activity)
    {
        Dialog pd = new Dialog(activity);
        pd.setContentView(R.layout.custom_dialog);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pd.setCancelable(false);
        pd.show();
        String URL= ConstantData.SERVERADDRESS + ConstantData.EDIT_PROFILE_METHOD;
        RequestQueue queue= Volley.newRequestQueue(activity);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Gson gson=new Gson();
                PersonOutputModel personOutputModel=gson.fromJson(response,PersonOutputModel.class);
                if (personOutputModel.isStatus()){
                    SharedPreferences sharedPreferences=activity.getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(ConstantData.KEY_USERNAME,personOutputModel.getPerson().getUsername());
                    editor.putString(ConstantData.KEY_EMAIL,personOutputModel.getPerson().getEmail());
                    editor.putString(ConstantData.KEY_PHONE,personOutputModel.getPerson().getPhno());
                    editor.putString(ConstantData.KEY_ID,personOutputModel.getPerson().getId());
                    editor.putString(ConstantData.KEY_PIC,personOutputModel.getPerson().getPic());

                    editor.commit();
                    Toast.makeText(activity, personOutputModel.getMessage(), Toast.LENGTH_SHORT).show();
                   activity.finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("JsonObject Error Response",error.toString());

                pd.dismiss();
                Toast.makeText(activity, "error:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("username", model.getUsername());
                map.put("pass",model.getPass());
                map.put("email",model.getEmail());
                map.put("phone",model.getPhno());
                map.put("pic",model.getPic());
                map.put("id",model.getId());
                return map;
            }
        };


        queue.add(stringRequest);
    }

}
