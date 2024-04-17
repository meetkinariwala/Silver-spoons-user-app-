package com.example.resturentsilverspoons;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.resturentsilverspoons.fragment.AccountFragment;
import com.example.resturentsilverspoons.fragment.CouponFragment;
import com.example.resturentsilverspoons.fragment.HomeFragment;
import com.example.resturentsilverspoons.fragment.TableFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeActivity extends AppCompatActivity {

    ChipNavigationBar bnvmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bnvmain=findViewById(R.id.bnvmain);
        openFragment(new HomeFragment());

        //created a openfragment function then you hav to create the navigation on itemselected listener

        bnvmain.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                if(i==R.id.menu_home){
                    openFragment(new HomeFragment());
                } else if (i==R.id.menu_offer) {
                    openFragment(new CouponFragment());

                }
                else if (i==R.id.menu_table_booking) {
                    openFragment(new TableFragment());

                }
                else if (i==R.id.menu_account) {
                    openFragment(new AccountFragment());

                } else {
                    openFragment(new HomeFragment());
                }

            }
        });


    }

    //first you have to crate that openfragment function
    public void openFragment(Fragment fragment){
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment).commit();
    }

    public void removeFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }


    public void replaceFragment(Fragment fragment){
        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment).commit();
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}