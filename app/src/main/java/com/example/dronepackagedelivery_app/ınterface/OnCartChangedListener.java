package com.example.dronepackagedelivery_app.ınterface;

import com.example.dronepackagedelivery_app.data.ProductData;

import java.util.HashMap;

public interface OnCartChangedListener {
    void onItemChangedListener(ProductData productData, int count);
    void onCartChangedListener(HashMap<ProductData,Integer> cart , float totalPrice);
    void onItemRemovedListener(ProductData productData);
    void onCartClearedListener();
}
