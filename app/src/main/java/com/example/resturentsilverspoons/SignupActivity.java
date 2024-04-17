package com.example.resturentsilverspoons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturentsilverspoons.model.PersonModel;

public class SignupActivity extends AppCompatActivity {

    TextView tvsignin;
    Button btnregister;
    EditText etuname,etpassword,etcpassword,etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etuname=findViewById(R.id.etuname);
        etpassword=findViewById(R.id.etpassword);
        etcpassword=findViewById(R.id.etcpassword);
        etemail=findViewById(R.id.etemail);


        tvsignin=findViewById(R.id.tvsignin);
        btnregister=findViewById(R.id.btnregister);









        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignupActivity.this,signinActivity.class);
                startActivity(intent);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String username=etuname.getText().toString().trim();
                String password=etpassword.getText().toString().trim();
                String cpassword=etcpassword.getText().toString().trim();
                String email=etemail.getText().toString().trim();

                if(username.length()==0){
                    Toast.makeText(SignupActivity.this, "Enter valid Username", Toast.LENGTH_SHORT).show();
                } else if (password.length()==0) {

                    Toast.makeText(SignupActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();

                } else if (cpassword.length()==0) {
                    Toast.makeText(SignupActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();

                } else if (password.length() != cpassword.length()) {

                    Toast.makeText(SignupActivity.this, "Password doenot match", Toast.LENGTH_SHORT).show();

                } else if (email.length()==0) {
                    Toast.makeText(SignupActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                }
                else {
                    PersonModel model=new PersonModel("",username,email,password,"","");
                    Intent intent=new Intent(SignupActivity.this,otp_sentActivity.class);
                    intent.putExtra("person",model);
                    startActivity(intent);

                }





            }
        });




    }
}