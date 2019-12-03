package com.example.dronepackagedelivery_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dronepackagedelivery_app.MainActivity;
import com.example.dronepackagedelivery_app.R;
import com.example.dronepackagedelivery_app.data.Category;

import java.util.ArrayList;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter{

    private Context context;

    ArrayList<Category> items = new ArrayList<>();

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

        initData();
    }

    private void initData() {

        for (int i = 0 ; i < images.length ; i++) {
            Category category = new Category(images[i], names[i]);
            items.add(category);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final String category_name = items.get(position).getCategoryName();
//        holder.cardView.setPreventCornerOverlap(false);

        int resourceId = items.get(position).getId();
        ((ViewHolder) holder).getmDataImgView().setImageResource(resourceId);
        ((ViewHolder) holder).getmTextView().setText(category_name);
        ((ViewHolder) holder).getmDataImgView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchContent(category_name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void switchContent(String categoryName) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.switchContent(categoryName);
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mDataImgView;
        private final TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mDataImgView = v.findViewById(R.id.image);
            mTextView = v.findViewById(R.id.category_name);
        }

        public TextView getmTextView() {
            return mTextView;
        }


        public ImageView getmDataImgView() {
            return mDataImgView;
        }
    }

}
