package com.example.dronepackagedelivery_app.ınterface;

import com.example.dronepackagedelivery_app.data.ProductData;

public interface OnProductCountChangedListenner {
        void onProductIncerementedListener(ProductData productData);
        void onProductDecrementedListener(ProductData productData);
        void onProductCountChangedListener(ProductData productData, int count);
        void onProductRemovedListener(ProductData productData);

}
