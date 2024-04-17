package com.example.resturentsilverspoons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.resturentsilverspoons.model.PersonModel;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.yasserakbbach.phonenumberpicker.PhoneNumberPicker;

import java.util.concurrent.TimeUnit;

public class otp_sentActivity extends AppCompatActivity {

    Button btn_otp_sent;

    ProgressBar pg;

    FirebaseAuth mAuth;

    String mobile, verificationId;

EditText etphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_sent);

        btn_otp_sent=findViewById(R.id.btn_otp_sent);
        etphone=findViewById(R.id.etPhone);

        pg=findViewById(R.id.pg);


        mAuth=FirebaseAuth.getInstance();



        btn_otp_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent= new Intent(otp_sentActivity.this,otp_receiveActivity.class);
//                startActivity(intent);

                pg.setVisibility(View.VISIBLE);
                btn_otp_sent.setVisibility(View.GONE);
                mobile=etphone.getText().toString().trim();

                if(mobile.length()!=10){
                    Toast.makeText(otp_sentActivity.this, "please Enter correct number", Toast.LENGTH_SHORT).show();
                }
                else {


                    sendOTP();
                }


            }
        });

    }

    private void sendOTP() {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+mobile)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
            PersonModel personModel=(PersonModel)getIntent().getSerializableExtra("person");
            personModel.setPhno(mobile);

            Intent intent=new Intent(otp_sentActivity.this, otp_receiveActivity.class);
            intent.putExtra("phone",mobile);
            intent.putExtra("verify",verificationId);
            intent.putExtra("person",personModel);

            startActivity(intent);
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                pg.setVisibility(View.GONE);
                btn_otp_sent.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            pg.setVisibility(View.GONE);
            btn_otp_sent.setVisibility(View.VISIBLE);
            Toast.makeText(otp_sentActivity.this, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };
}