package com.example.dronepackagedelivery_app.Interface;

import com.example.dronepackagedelivery_app.Data.ProductData;

import java.util.HashMap;

public interface OnCartChangedListener {
    void onItemChangedListener(ProductData productData, int count);
    void onCartChangedListener(HashMap<ProductData,Integer> cart , float totalPrice);
    void onItemRemovedListener(ProductData productData);
    void onCartClearedListener();
}
