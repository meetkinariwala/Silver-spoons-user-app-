package com.example.resturentsilverspoons.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.resturentsilverspoons.CartActivity;
import com.example.resturentsilverspoons.HomeActivity;
import com.example.resturentsilverspoons.R;
import com.example.resturentsilverspoons.adapter.CuisinAdapter;
import com.example.resturentsilverspoons.adapter.MenuAdapter;
import com.example.resturentsilverspoons.api.Bannerapi;
import com.example.resturentsilverspoons.api.CountApi;
import com.example.resturentsilverspoons.api.CuisinAPi;
import com.example.resturentsilverspoons.api.MenuApi;
import com.example.resturentsilverspoons.model.BannerOutputModel;
import com.example.resturentsilverspoons.model.CuisinModel;
import com.example.resturentsilverspoons.model.CuisinOutputModel;
import com.example.resturentsilverspoons.model.MenuModel;
import com.example.resturentsilverspoons.model.MenuOutputModel;
import com.example.resturentsilverspoons.model.bannermodel;
import com.example.resturentsilverspoons.util.ConstantData;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

View view;
ImageSlider image_slider,img_dish;

TextView SeeAll;

RecyclerView rcylcuisin,rcylmenu;
Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     image_slider=view.findViewById(R.id.image_slider);
     rcylcuisin=view.findViewById(R.id.rcylcuisin);
     rcylmenu=view.findViewById(R.id.rcylmenu);
        SeeAll=view.findViewById(R.id.SeeAll);
        toolbar=view.findViewById(R.id.toolbar);
        new CountApi().getCount(HomeFragment.this);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.cart){
                    Intent intent=new Intent(getActivity(), CartActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

     new Bannerapi().Banner(this);
     new CuisinAPi().Cuisin(this);
     new MenuApi().Menu(this);

        SeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).openFragment(new AllProductFragment());
            }
        });



    }

    public  void setBanner(BannerOutputModel banner){

        List<SlideModel> imageList =new  ArrayList<SlideModel>();// Create image list
        ArrayList<bannermodel> bannermodels=banner.getBaneers();
        for (int i=0;i<bannermodels.size();i++){                        //bannner title remove
            imageList.add(new SlideModel(bannermodels.get(i).getPic(), ScaleTypes.FIT));

        }
        image_slider.setImageList(imageList);
    }

    public void setCuisin(CuisinOutputModel Cuisin){

        CuisinAdapter cuisinAdapter= new CuisinAdapter(Cuisin.getCuisins(), getContext(), new CuisinAdapter.onClickListener() {
            @Override
            public void onClick(int position, CuisinModel cuisinModel) {
                AllProductFragment dishFragment=new AllProductFragment();
                Bundle bundle=new Bundle();
                bundle.putString("ctype",cuisinModel.getId());
                dishFragment.setArguments(bundle);

                ((HomeActivity)getActivity()).openFragment(dishFragment);
            }
        });
        rcylcuisin.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        rcylcuisin.setAdapter(cuisinAdapter);
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
        rcylmenu.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rcylmenu.setLayoutManager(new GridLayoutManager(getContext(),2));
        rcylmenu.setAdapter(menuAdapter);


    }


    MenuItem menuItem;
    TextView notificationBadge;
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_carts,menu);
        menuItem=menu.findItem(R.id.cart);
        notificationBadge=view.findViewById(R.id.badge);
        if(ConstantData.COUNT.equals("0")){

            notificationBadge.setVisibility(View.GONE);

        }else{

            notificationBadge.setVisibility(View.VISIBLE);
            menuItem.setActionView(R.layout.layout_badge);
            View view=menuItem.getActionView();
            notificationBadge=view.findViewById(R.id.badge);
            notificationBadge.setText(ConstantData.COUNT);
//            updateCartCounter();
        }

    }

//    public void updateCartCounter() {
//        if(notificationBadge==null){
//
//            notificationBadge=view.findViewById(R.id.badge);
//}
//
//
////            notificationBadge.setVisibility(View.VISIBLE);
//            notificationBadge.setText(ConstantData.COUNT);
//
//    }

}