package com.example.resturentsilverspoons;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.resturentsilverspoons.api.RegisterApi;
import com.example.resturentsilverspoons.fragment.AccountFragment;
import com.example.resturentsilverspoons.model.PersonModel;
import com.example.resturentsilverspoons.util.ConstantData;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_profile_Activity extends AppCompatActivity {

    ImageButton back_arrow;

    TextView tvchangeimage;

    EditText tvetusername,tvetpass,tvetemail,tvetphone;
    CircleImageView profile_image;

    Button btnupdateprofile;
    private String base64String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        back_arrow = findViewById(R.id.back_arrow);
        profile_image = findViewById(R.id.profile_image);
        tvchangeimage=findViewById(R.id.tvchangeimage);
        tvetusername=findViewById(R.id.tvetusername);
        tvetpass=findViewById(R.id.tvetpass);
        tvetemail=findViewById(R.id.tvetemail);
        tvetphone=findViewById(R.id.tvetphone);
        btnupdateprofile=findViewById(R.id.btnupdateprofile);
        SharedPreferences sp=getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);

        String pic=sp.getString(ConstantData.KEY_PIC,"");

        tvetusername.setText(sp.getString(ConstantData.KEY_USERNAME,""));
        tvetemail.setText(sp.getString(ConstantData.KEY_EMAIL,""));
        tvetphone.setText(sp.getString(ConstantData.KEY_PHONE,""));


        if(pic.toString().trim().length()==0) {
            Glide.with(this).load(R.mipmap.profile).into(profile_image);
        } else {
            Glide.with(this).load(ConstantData.SERVERADDRESS+"/images/"+pic).into(profile_image);        }




        btnupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String email=tvetemail.getText().toString();
                    String phone=tvetphone.getText().toString();
                    String username=tvetusername.getText().toString();


                    if (phone.trim().length()==0) {
                        Toast.makeText(Edit_profile_Activity.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();

                    }
                   else if (email.trim().length()==0) {
                        Toast.makeText(Edit_profile_Activity.this, "Please Enter address", Toast.LENGTH_SHORT).show();

                    }else if (username.trim().length()==0) {
                        Toast.makeText(Edit_profile_Activity.this, "Please Enter username", Toast.LENGTH_SHORT).show();

                    }else{

                        SharedPreferences sp=Edit_profile_Activity.this.getSharedPreferences(ConstantData.LOGIN_REGISTRATION_PREFS, Context.MODE_PRIVATE);
                        String id=sp.getString(ConstantData.KEY_ID,"0");
                        PersonModel model=new PersonModel(id,username,email,"",phone,base64String);

                        new RegisterApi().edit_user(model,Edit_profile_Activity.this);
                    }

                }


        });


        tvchangeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Edit_profile_Activity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // BitMap is data structure of image file which store the image in memory
            Uri uri = data.getData();

            // Use Uri object instead of File to avoid storage permissions
            profile_image.setImageURI(uri);
            // Set the image in imageview for display
            base64String = getBase64(uri);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public String getBase64(Uri uri){
        String encodedImage="";
        try {
            InputStream input = getContentResolver().openInputStream(uri);
            Bitmap bm = BitmapFactory.decodeStream(input, null, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);


        }catch (Exception e){
            Toast.makeText(Edit_profile_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return encodedImage;
    }
}
