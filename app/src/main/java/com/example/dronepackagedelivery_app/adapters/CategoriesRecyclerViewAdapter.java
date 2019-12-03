package com.example.dronepackagedelivery_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dronepackagedelivery_app.MainActivity;
import com.example.dronepackagedelivery_app.R;
import com.example.dronepackagedelivery_app.fragments.ProductFragment;

import java.util.ArrayList;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter{

    private static final int VIEW_TYPE_IMAGE = 0;
    private static final int VIEW_TYPE_NAME = 1;

    private Context context;
    LayoutInflater layoutInflater;

    ArrayList<String[]> items = new ArrayList<>();

    int[] images = new int[]{
        R.drawable.fruits,
                R.drawable.baked_goods1,
                R.drawable.snacks,
                R.drawable.ice_cream,
                R.drawable.ready_to_eat,
                R.drawable.drinks,
                R.drawable.fit_food,
                R.drawable.daily,
                R.drawable.food,
                R.drawable.personal_care,
                R.drawable.home_care,
                R.drawable.home_living1,
                R.drawable.tech,
                R.drawable.pet_care1,
                R.drawable.baby_care,
                R.drawable.sex_health,
                R.drawable.apparel
    };

    String[] names = {
            "Meyve & Sebze",
            "Unlu Mamüller",
            "Atıştırmalık",
            "Dondurma",
            "Yiyecek",
            "İçeçek",
            "Fit & Form",
            "Süt & Kahvaltı",
            "Temel Gıda",
            "Kişisel Bakım",
            "Ev & Temizlik",
            "Ev & Yaşam",
            "Teknoloji",
            "Evcil Hayvan",
            "Bebek",
            "Cinsel Sağlık",
            "Giyim"
    };

    public CategoriesRecyclerViewAdapter(Context context) {
        this.context = context;

        initLayoutType();
    }

    private void initLayoutType() {

        int old = 0;
        int imageCount = 0;
        int nameCount = 0;
        boolean flag = false;
        for(int i = 0; i < (images.length + names.length - 1); i++){
            String[] row = new String[2];
            int current = i/2;
            if(old == current){
                if(!flag){
                    row[0] = "0";
                    row[1] = Integer.toString(images[imageCount++]);
                    items.add(row);

                }else{
                    row[0] = "1";
                    row[1] = names[nameCount++];
                    items.add(row);
                }
            }else{
                i--;
                old = current;
                flag = !flag;
            }

        }
        String[] row = new String[2];
        row[0] = "1";
        row[1] = "";
        items.add(row);
        String[] row1 = new String[2];
        row1[0] = "1";
        row1[1] = names[nameCount];
        items.add(row1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == VIEW_TYPE_IMAGE) {
            v = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
            return new ViewHolderImage(v);
        } else{
            v = LayoutInflater.from(context).inflate(R.layout.category_name_item, parent, false);
            return new ViewHolderName(v);
        }
//        v = layoutInflater.inflate(R.layout.categories_item_name, parent, false);
//        ViewHolderImage view_holder = new ViewHolderImage(v);

//        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

//        holder.cardView.setPreventCornerOverlap(false);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_IMAGE:
                int resourceId = Integer.parseInt(items.get(position)[1]);
                ((ViewHolderImage) holder).getmDataImgView().setImageResource(resourceId);
                ((ViewHolderImage) holder).getmDataImgView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String categoryName = names[position];
                        switchContent(categoryName);
                    }
                });
                break;
            case VIEW_TYPE_NAME:
                ((ViewHolderName) holder).textView.setText(items.get(position)[1]);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position)[0].equals("0"))
            return VIEW_TYPE_IMAGE;
        else
            return VIEW_TYPE_NAME;
    }

    public void switchContent(String categoryName) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.switchContent(categoryName);
        }

    }

    class ViewHolderImage extends RecyclerView.ViewHolder {
        private final ImageView mDataImgView;
//        CardView cardView;

        public ViewHolderImage(View v) {
            super(v);
//            cardView = v.findViewById(R.id.card_view);
            mDataImgView = v.findViewById(R.id.image);
        }

        public ImageView getmDataImgView() {
            return mDataImgView;
        }
//        public CardView getmDataCardView() {
//            return cardView;
//        }
    }

    class ViewHolderName extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolderName(View v) {
            super(v);
            textView = v.findViewById(R.id.category_name);
        }
    }




}
