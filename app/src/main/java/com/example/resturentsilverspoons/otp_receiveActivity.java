package com.example.resturentsilverspoons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturentsilverspoons.api.RegisterApi;
import com.example.resturentsilverspoons.model.PersonModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class otp_receiveActivity extends AppCompatActivity {

    TextView otpsend,tvtimer;


    Button btn_otp_receive;

    EditText et1,et2,et3,et4,et5,et6;

    ProgressBar pg1;

    String  verificationId;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startTimer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_receive);

       otpsend=findViewById(R.id.otpsend);
       btn_otp_receive=findViewById(R.id.btn_otp_receive);
       et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);
        tvtimer=findViewById(R.id.tvtimer);

        mAuth=FirebaseAuth.getInstance();

        pg1=findViewById(R.id.pg1);

        verificationId=getIntent().getStringExtra("verify");

        setEdittext();

        btn_otp_receive.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               String code=et1.getText().toString().trim()+
                           et2.getText().toString().trim()+
                           et3.getText().toString().trim()+
                           et4.getText().toString().trim()+
                           et5.getText().toString().trim()+
                           et6.getText().toString().trim();

               if(code.length()!=6){
                   Toast.makeText(otp_receiveActivity.this, "Please Enter valid otp", Toast.LENGTH_SHORT).show();
               }
               else {
                   verifyCode(code);
               }



           }
       });

       otpsend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                startTimer();
           }
       });

    }

    public void setEdittext(){
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                et2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                et3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                et4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                et5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                et6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                btn_otp_receive.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }
    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            PersonModel personModel=(PersonModel)getIntent().getSerializableExtra("person");
                            new RegisterApi().register_user(personModel,otp_receiveActivity.this);
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.

                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(otp_receiveActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

    public void startTimer() {
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvtimer.setVisibility(View.VISIBLE);
                otpsend.setVisibility(View.GONE);
                tvtimer.setText((millisUntilFinished / 1000) + " Sec");
            }

            @Override
            public void onFinish() {
                tvtimer.setVisibility(View.GONE);
                otpsend.setVisibility(View.VISIBLE);
            }
        }.start();
            }


}