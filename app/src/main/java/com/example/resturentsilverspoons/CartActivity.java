package com.example.resturentsilverspoons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturentsilverspoons.adapter.CartAdapter;
import com.example.resturentsilverspoons.api.CouponApi;
import com.example.resturentsilverspoons.api.OrderApi;
import com.example.resturentsilverspoons.fragment.CouponFragment;
import com.example.resturentsilverspoons.model.CouponOutputModel;
import com.example.resturentsilverspoons.model.OrderModel;
import com.example.resturentsilverspoons.model.OrderOutputModel;
import com.example.resturentsilverspoons.util.ConstantData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {

    RecyclerView rcvCart;

    ImageView cart_back;

    Button btnContinue;


    EditText edcoupon;

    TextView tvamount,tvGst,tvTotal_amount,tv_view_all_coupon,tvcoupon,btnapplycode;

    RadioGroup rdbpayment,rdbPay;

    String uid;

    RadioButton  rbdcod,rbdonline,rbtndine,rbtntake;
    Button btnShop;
    ConstraintLayout EmptyCartLayout;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        btnContinue=findViewById(R.id.btnContinue);
        btnShop=findViewById(R.id.btnShop);
        EmptyCartLayout=findViewById(R.id.EmptyCartLayout);
        rcvCart=findViewById(R.id.rcvCart);
        tvamount=findViewById(R.id.tvamount);
        tvGst=findViewById(R.id.tvGst);
        tvTotal_amount=findViewById(R.id.tvTotal_amount);
        rdbpayment=findViewById(R.id.rdbpayment);
        rbdcod=findViewById(R.id.rbdcod);
        rbdonline=findViewById(R.id.rbdonline);
        cart_back=findViewById(R.id.cart_back);
        tv_view_all_coupon=findViewById(R.id.tv_view_all_coupon);
        rdbPay=findViewById(R.id.rdbPay);
        rbtndine=findViewById(R.id.rbtndine);
        rbtntake=findViewById(R.id.rbtntake);
        tvcoupon=findViewById(R.id.tvcoupon);
        edcoupon=findViewById(R.id.edcoupon);
        btnapplycode=findViewById(R.id.btnapplycode);

        btnapplycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String couponcode=edcoupon.getText().toString();
                if(couponcode.trim().length()==0){
                    Toast.makeText(CartActivity.this, "Please Enter Code", Toast.LENGTH_SHORT).show();
                }
                else {
                    new CouponApi().GetCoupons(CartActivity.this,couponcode);
                }
            }
        });



        rdbPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rbtndine.isChecked()){

                    showDialog();


                } else if (rbtntake.isChecked()) {


                }else {

                }
            }
        });



        tv_view_all_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openFragment(new CouponFragment());
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        SharedPreferences sp=getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS,MODE_PRIVATE);
        uid=sp.getString(ConstantData.KEY_ID,"0");

        cart();



        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rbdcod.isChecked()){
                    new OrderApi().order_confirm(uid,"0",tvTotal_amount.getText().toString(),CartActivity.this);
                }
                else {
                    openRazorPay();
                }



            }
        });

        cart_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CartActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    public void openFragment(Fragment fragment){
         frameLayout=findViewById(R.id.frame);
        frameLayout.setVisibility(View.VISIBLE);
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment).commit();
    }

    public void visible(){
        frameLayout.setVisibility(View.GONE);
    }


    double tot = 0, gst = 0, total_amount = 0;
    public void  setcart_adapter(OrderOutputModel orderOutputModel){

        total_amount=0;
        gst=0;
        tot=0;

        if(orderOutputModel.getOrder().size()==0){
            EmptyCartLayout.setVisibility(View.VISIBLE);
        }else {
            EmptyCartLayout.setVisibility(View.GONE);


            for (int i = 0; i < orderOutputModel.getOrder().size(); i++) {
                double total = Double.parseDouble(orderOutputModel.getOrder().get(i).getTotal_amount());
                tot += total;
                gst = tot * 0.05;
                total_amount = tot + gst;
            }


            tvamount.setText(tot + "");
            tvGst.setText("+"+gst + "");
            tvTotal_amount.setText(total_amount + "");


            CartAdapter cartAdapter = new CartAdapter(orderOutputModel.getOrder(), new CartAdapter.OnClickListener() {
                @Override
                public void onClickPlus(OrderModel om) {
                    new OrderApi().update_qty_order(om.getId(), om.getQuantity(), CartActivity.this);
                }

                @Override
                public void onClickMinus(OrderModel om) {
                    new OrderApi().update_qty_order(om.getId(), om.getQuantity(), CartActivity.this);

                }

                @Override
                public void removeOrder(OrderModel om) {
                    new OrderApi().remove_order(om.getId(), CartActivity.this);
                }

            }, this);

            rcvCart.setLayoutManager(new LinearLayoutManager(this));
            rcvCart.setAdapter(cartAdapter);
        }
    }

    public  void cart(){


        if (uid.equals("0")){

        }else {
            new OrderApi().getOrder(uid,this);
        }

    }

    public void done() {

        Intent intent=new Intent(CartActivity.this,Order_place_Activity.class);
        startActivity(intent);
    }

    public void openRazorPay(){
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID("rzp_test_pIAyiT1rm3RwMs");

        // set image
        checkout.setImage(com.razorpay.R.drawable.rzp_logo);

        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "Silver Spoons");

            // put description
            object.put("description", "Test payment");

            // to set theme color
            object.put("theme.color", "");

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount", total_amount*100);

            // put mobile number
            object.put("prefill.contact", "8849297341");

            // put email
            object.put("prefill.email", "yashvitopiwala27@gmail.com");

            // open razorpay to checkout activity
            checkout.open(CartActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        new OrderApi().order_confirm(uid,"1",tvTotal_amount.getText().toString(),CartActivity.this);

    }

    @Override
    public void onPaymentError(int i, String s) {

    }

    public void showDialog()
    {
        final BottomSheetDialog dialog  = new BottomSheetDialog(CartActivity.this);
        dialog.setContentView(R.layout.tablenumber_bottomsheetdialog);

        RadioGroup rdg_numtable;
        RadioButton table1,table2,table3,table4,table5;

        rdg_numtable=dialog.findViewById(R.id.rdg_numtable);
        table1=dialog.findViewById(R.id.table1);
        table2=dialog.findViewById(R.id.table2);
        table3=dialog.findViewById(R.id.table3);
        table4=dialog.findViewById(R.id.table4);
        table5=dialog.findViewById(R.id.table5);

        dialog.show();
    }

    int i=0;
    public void CalculatePrice(CouponOutputModel model) {

        i++;
        if(i==1) {
            if (model.getCoupon().size() != 0) {


                Double ccode = Double.parseDouble(model.getCoupon().get(0).getCoupondiscount());

                tot = Double.parseDouble(tvamount.getText().toString());

                Double T = tot - (tot*(ccode/100)) + gst;

                tvcoupon.setText("-"+String.valueOf(tot*ccode/100));

                tvTotal_amount.setText(String.valueOf(T));
                total_amount=T;
            }
            }
        }
}