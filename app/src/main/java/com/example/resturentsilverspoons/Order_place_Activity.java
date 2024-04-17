package com.example.resturentsilverspoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Order_place_Activity extends AppCompatActivity {

    Button btn_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place);
        btn_order=findViewById(R.id.btn_order);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Order_place_Activity.this,HomeActivity.class);
                startActivity(intent);
            }
        });




//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                  Intent intent=new Intent(Order_place_Activity.this,HomeActivity.class);
//                  startActivity(intent);
//                    finish();
//                }
//            },3000);


        }
    }
