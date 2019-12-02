package com.example.dronepackagedelivery_app.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;

import com.example.dronepackagedelivery_app.Adapter.FragPageAdapter;
import com.example.dronepackagedelivery_app.Adapter.ProductRecyclerViewAdapter;
import com.example.dronepackagedelivery_app.Data.Cart;
import com.example.dronepackagedelivery_app.Data.ProductData;
import com.example.dronepackagedelivery_app.Interface.OnCartChangedListener;
import com.example.dronepackagedelivery_app.Interface.OnProductCountChangedListenner;
import com.example.dronepackagedelivery_app.R;

import java.util.HashMap;

public class ShopFragment extends Fragment {

    private static final String TEST_ARG = "test-arg";

    private OnFragmentInteractionListener mListener;
    private FragPageAdapter fragmentPagerAdapter;
    private String mTestArg;

    private Cart mCartRef;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance(String testArg,Cart cartref) {
        ShopFragment fragment = new ShopFragment();
        fragment.mCartRef = cartref;
        Bundle args = new Bundle();
        args.putString(TEST_ARG, testArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTestArg = getArguments().getString(TEST_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop, container, false);
        ViewPager vpPager = (ViewPager) v.findViewById(R.id.vpPager);
        fragmentPagerAdapter = new FragPageAdapter(getFragmentManager());
        vpPager.setAdapter(fragmentPagerAdapter);


   //     if(mTestArg.equals("1")){
            ProductFragment foodProdFrag = ProductFragment.newInstance(2,"Food",mCartRef);
            ProductFragment drinkProdFrag = ProductFragment.newInstance(2,"Drink",mCartRef);
            fragmentPagerAdapter.addPage(foodProdFrag);
            fragmentPagerAdapter.addPage(drinkProdFrag);
       // }else{
            ProductFragment firutProdFrag = ProductFragment.newInstance(2,"Firut",mCartRef);
            ProductFragment asdProdFrag = ProductFragment.newInstance(2,"ASD",mCartRef);
            ProductFragment cccProdFrag = ProductFragment.newInstance(2,"CCC",mCartRef);
            fragmentPagerAdapter.addPage(firutProdFrag);
            fragmentPagerAdapter.addPage(asdProdFrag);
            fragmentPagerAdapter.addPage(cccProdFrag);
     //   }

        fragmentPagerAdapter.notifyDataSetChanged();
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
