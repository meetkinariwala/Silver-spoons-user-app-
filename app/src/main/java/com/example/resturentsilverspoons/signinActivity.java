package com.example.resturentsilverspoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturentsilverspoons.api.RegisterApi;
import com.example.resturentsilverspoons.model.PersonModel;

public class signinActivity extends AppCompatActivity {

    TextView tvsignup;

    EditText etuname,etpassword;

    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        tvsignup=findViewById(R.id.tvsignup);
        btnsubmit=findViewById(R.id.btnsubmit);
        etuname=findViewById(R.id.etuname);
        etpassword=findViewById(R.id.etpassword);



        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(signinActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=etuname.getText().toString().trim();
                String password=etpassword.getText().toString().trim();

                if(username.length()==0){
                    Toast.makeText(signinActivity.this, "Enter valid Username", Toast.LENGTH_SHORT).show();
                } else if (password.length()==0) {

                    Toast.makeText(signinActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();

                }else {
                    PersonModel personModel=new PersonModel("",username,"",password,"","");
                        new RegisterApi().login_user(personModel,signinActivity.this);
                }

            }
        });
    }
}