package com.example.resturentsilverspoons.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resturentsilverspoons.HomeActivity;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.adapter.CuisinAdapter;
import com.example.resturentsilverspoons.adapter.MenuAdapter;
import com.example.resturentsilverspoons.api.MenuApi;
import com.example.resturentsilverspoons.model.CuisinModel;
import com.example.resturentsilverspoons.model.MenuModel;
import com.example.resturentsilverspoons.model.MenuOutputModel;

public class AllProductFragment extends Fragment {

RecyclerView rcylmenu;

ImageView menu_back;
TextView tvcuisintype;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_all_product, container, false);
        return view;

    }

    String ctype="0",cuisinname;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcylmenu=view.findViewById(R.id.rcylmenu);
        menu_back=view.findViewById(R.id.menu_back);
        tvcuisintype=view.findViewById(R.id.tvcuisintype);

        tvcuisintype.setText(cuisinname);


        menu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).removeFragment(AllProductFragment.this);
                ((HomeActivity)getActivity()).openFragment(new HomeFragment());
            }
        });

        try {
            ctype=getArguments().getString("ctype","0");
            cuisinname=getArguments().getString("cuisinname","0");



        }catch (Exception e){}
        if(ctype.equals("0"))
            new MenuApi().MenuAll(this);
        else
            new MenuApi().MenuAllCat(this,ctype);
    }

    public  void setMenu(MenuOutputModel Menu){

        MenuAdapter menuAdapter=new MenuAdapter(Menu.getMenu(), getContext(), new MenuAdapter.onClickListener() {
            @Override
            public void onClick(int position, MenuModel menuModel) {
                DishFragment dishFragment=new DishFragment();
                Bundle bundle=new Bundle();
                bundle.putSerializable("food",menuModel);
                dishFragment.setArguments(bundle);

                ((HomeActivity)getActivity()).openFragment(dishFragment);
            }
        });
        rcylmenu.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        rcylmenu.setLayoutManager(new GridLayoutManager(getContext(),2));
        rcylmenu.setAdapter(menuAdapter);


    }
}