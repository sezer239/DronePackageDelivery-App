package com.example.dronepackagedelivery_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dronepackagedelivery_app.adapters.ProductRecyclerViewAdapter;
import com.example.dronepackagedelivery_app.data.Pair;
import com.example.dronepackagedelivery_app.data.ProductData;
import com.example.dronepackagedelivery_app.ınterfaces.OnCartChangedListener;
import com.example.dronepackagedelivery_app.R;
import com.example.dronepackagedelivery_app.data.Cart;
import com.example.dronepackagedelivery_app.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProductFragment extends Fragment implements ProductRecyclerViewAdapter.OnProductButtonPressed , OnCartChangedListener {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_PRODUCT_CATEGORY = "product-category";

    private int mColumnCount = 3;

    private Cart mCartRef;

    private String mProductCatagory;
    private OnListFragmentInteractionListener mListener;
    private ProductRecyclerViewAdapter productRecyclerViewAdapter;

    public ProductFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mProductCatagory = getArguments().getString(ARG_PRODUCT_CATEGORY);

        }
    }

    public static ProductFragment newInstance(int columnCount,
                                              String productCategory,
                                              Cart cartRef) {
        ProductFragment fragment = new ProductFragment();
        fragment.mCartRef = cartRef;
        fragment.mProductCatagory = productCategory;
        fragment.mColumnCount = columnCount;
      //  Bundle args = new Bundle();
      //  args.putInt(ARG_COLUMN_COUNT, columnCount);
      ////  args.putString(ARG_PRODUCT_CATEGORY, productCategory);
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);


        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, mColumnCount);

                recyclerView.setLayoutManager(gridLayoutManager);
            }

            //TODO: FOR DEBUG
            switch (mProductCatagory){
                case "Food":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.FOOD_PROD, mListener,this);
                    break;
                case "Drink":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.DRINK_PROD, mListener,this);
                    break;
                case "Firut":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.FIRUT_PROD, mListener,this);
                    break;
                case "ASD":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.ASD_PROD, mListener,this);
                    break;
                case "CCC":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.CCC_PROD, mListener,this);
                    break;
                case "BUYUK FOOD":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.BUYUK_PROD, mListener,this);
                    break;
                case "MEDUIM FOOD":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.MEDIUM_PROD, mListener,this);
                    break;
                case "KUCUK YEMEK":
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.KUCUK_PROD, mListener,this);
                    break;
                default:
                    productRecyclerViewAdapter = new ProductRecyclerViewAdapter(DummyContent.FOOD_PROD, mListener,this);
                    break;
            }
            ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
            //INIT WITH DUMMY DATA
            recyclerView.setAdapter(productRecyclerViewAdapter);
        }
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("FFF", toString() + " Resume");
        List<Pair<ProductData, Integer>> products = new ArrayList<>();
        HashMap<ProductData, Integer> cart = mCartRef.getCart();

        for(ProductData key : cart.keySet()){
            if(key.getmCategory().equals(mProductCatagory)){
                products.add(new Pair<ProductData, Integer>(key, cart.get(key)));
            }
        }

        productRecyclerViewAdapter.updateListProductCount(products);
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("FFF", toString() + " Pause");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }

        mCartRef.addOnCartChangedListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mCartRef.removeOnCartChangedListener(this);
    }

    @Override
    public void onPlusButtonPressed(ProductData product, int index) {
        int itemPurchsed = mCartRef.getItemCount(product) + 1;
        mCartRef.putItem(product, itemPurchsed);
    }

    @Override
    public void onMinusButtonPressed(ProductData product, int index) {
        int itemPurchsed = mCartRef.getItemCount(product) - 1;
        if(itemPurchsed <= 0) {
            mCartRef.removeItem(product);
        }else{
            mCartRef.putItem(product, itemPurchsed);
        }
    }

    @Override
    public void onItemChangedListener(ProductData productData, int count) {
        if(productData.getmCategory().equals(mProductCatagory)){
            productRecyclerViewAdapter.updateListProductCount(productData, count);
        }
    }

    @Override
    public void onCartChangedListener(HashMap<ProductData, Integer> cart, float totalPrice) {

    }

    @Override
    public void onItemRemovedListener(ProductData productData) {
        if(productData.getmCategory().equals(mProductCatagory)){
            productRecyclerViewAdapter.updateListProductCount(productData, 0);
        }
    }

    @Override
    public void onCartClearedListener() {
        productRecyclerViewAdapter.clearBuyCounts();
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(ProductData productData);
    }

    @Override
    public String toString() {
        return mProductCatagory;
    }

}
