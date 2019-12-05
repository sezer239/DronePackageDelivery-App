package com.example.dronepackagedelivery_app.data;


import com.example.dronepackagedelivery_app.common.Data;

public class Category extends Data {

    private int id;

    private String categoryName;

    private String productName;

    public Category() {
    }

    public Category(int id, String categoryName, String productName,int type) {
        this.id = id;
        this.categoryName = categoryName;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
