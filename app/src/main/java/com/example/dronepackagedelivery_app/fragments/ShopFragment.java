package com.example.dronepackagedelivery_app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dronepackagedelivery_app.adapters.FragPageAdapter;
import com.example.dronepackagedelivery_app.data.Cart;
import com.example.dronepackagedelivery_app.R;
import com.google.android.material.tabs.TabLayout;

public class ShopFragment extends Fragment implements
        com.example.dronepackagedelivery_app.ınterfaces.OnBackPressed {

    private static final String ARG_CATEGORY = "category";

    private OnFragmentInteractionListener mListener;
    private FragPageAdapter fragmentPagerAdapter;
    private String mArgCategory;

    private Cart mCartRef;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance(String category,Cart cartref) {
        ShopFragment fragment = new ShopFragment();
        fragment.mCartRef = cartref;
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mArgCategory = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop, container, false);
        fragmentPagerAdapter = new FragPageAdapter(getFragmentManager());
        ViewPager vpPager = (ViewPager) v.findViewById(R.id.vpPager);
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);


        ProductFragment foodProdFrag = ProductFragment.newInstance(2,"Food",mCartRef);
        ProductFragment drinkProdFrag = ProductFragment.newInstance(2,"Drink",mCartRef);
        ProductFragment firutProdFrag = ProductFragment.newInstance(2,"Firut",mCartRef);
        ProductFragment asdProdFrag = ProductFragment.newInstance(1,"ASD",mCartRef);
        ProductFragment cccProdFrag = ProductFragment.newInstance(2,"CCC",mCartRef);
        ProductFragment buyukFoodFrag = ProductFragment.newInstance(1,"BUYUK FOOD",mCartRef);
        ProductFragment mediumFoodFrag = ProductFragment.newInstance(2,"MEDUIM FOOD",mCartRef);
        ProductFragment kucukYemekFrag = ProductFragment.newInstance(2,"KUCUK YEMEK",mCartRef);

        fragmentPagerAdapter.addPage(foodProdFrag);
        fragmentPagerAdapter.addPage(drinkProdFrag);
        fragmentPagerAdapter.addPage(firutProdFrag);
        fragmentPagerAdapter.addPage(asdProdFrag);
        fragmentPagerAdapter.addPage(cccProdFrag);
        fragmentPagerAdapter.addPage(buyukFoodFrag);
        fragmentPagerAdapter.addPage(kucukYemekFrag);
        fragmentPagerAdapter.addPage(mediumFoodFrag);


        vpPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(vpPager);
        fragmentPagerAdapter.notifyDataSetChanged();

        Log.d("HHH" , "argCAT "+ mArgCategory);
        if(mArgCategory.equals("Meyve & Sebze")){
            tabLayout.getTabAt(0).select();
        }else if(mArgCategory.equals("Unlu Mamüller")){
            Log.d("HHH" , "UNLU MAMUL");
            tabLayout.getTabAt(1).select();
        }else if(mArgCategory.equals("Yiyecek")){
            tabLayout.getTabAt(2).select();
        }

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

  @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
