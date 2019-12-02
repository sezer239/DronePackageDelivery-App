package com.example.dronepackagedelivery_app.dummy;

import com.example.dronepackagedelivery_app.Data.Pair;
import com.example.dronepackagedelivery_app.Data.ProductData;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {


    public static final List<Pair<ProductData,Integer>> FOOD_PROD = new ArrayList<Pair<ProductData,Integer>>();
    public static final List<Pair<ProductData,Integer>> DRINK_PROD = new ArrayList<Pair<ProductData,Integer>>();
    public static final List<Pair<ProductData,Integer>> FIRUT_PROD = new ArrayList<Pair<ProductData,Integer>>();
    public static final List<Pair<ProductData,Integer>> ASD_PROD = new ArrayList<Pair<ProductData,Integer>>();
    public static final List<Pair<ProductData,Integer>> CCC_PROD = new ArrayList<Pair<ProductData,Integer>>();
    public static final List<Pair<ProductData,Integer>> BUYUK_PROD = new ArrayList<Pair<ProductData,Integer>>();
    public static final List<Pair<ProductData,Integer>> MEDIUM_PROD = new ArrayList<Pair<ProductData,Integer>>();
    public static final List<Pair<ProductData,Integer>> KUCUK_PROD = new ArrayList<Pair<ProductData,Integer>>();


    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            FOOD_PROD.add(createDummyItem(i,"Food"));
        }
        for (int i = COUNT; i < COUNT * 2; i++) {
            DRINK_PROD.add(createDummyItem(i,"Drink"));
        }
        for (int i = COUNT * 2; i < COUNT * 3; i++) {
            FIRUT_PROD.add(createDummyItem(i,"Firut"));
        }

        for (int i = COUNT * 3; i < COUNT * 4; i++) {
            ASD_PROD.add(createDummyItem(i,"ASD"));
        }

        for (int i = COUNT * 4; i < COUNT * 5; i++) {
            CCC_PROD.add(createDummyItem(i,"CCC"));
        }

        for (int i = COUNT * 5; i < COUNT * 6; i++) {
            BUYUK_PROD.add(createDummyItem(i,"BUYUK FOOD"));
        }

        for (int i = COUNT * 6; i < COUNT * 7; i++) {
            MEDIUM_PROD.add(createDummyItem(i,"MEDUIM FOOD"));
        }

        for (int i = COUNT * 7; i < COUNT * 8; i++) {
            KUCUK_PROD.add(createDummyItem(i,"KUCUK YEMEK"));
        }
    }

    private static Pair<ProductData,Integer> createDummyItem(int position, String category) {
        Pair<ProductData,Integer> pair = new Pair<>();
        pair.first = new ProductData(String.valueOf(position), category + " " + position , Float.parseFloat ( Integer.toString(position) + ".57"),category);
        pair.second = 0;
        return pair;
    }
}
