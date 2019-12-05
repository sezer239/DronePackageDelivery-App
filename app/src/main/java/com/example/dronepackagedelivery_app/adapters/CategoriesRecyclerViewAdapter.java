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
import com.example.dronepackagedelivery_app.common.Data;
import com.example.dronepackagedelivery_app.data.Category;
import com.example.dronepackagedelivery_app.data.Header;

import java.util.ArrayList;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter{

    private static final int VIEW_TYPE_IMAGE = 0;
    private static final int VIEW_TYPE_HEADER = 1;
    public static boolean recViewItemPressed;
    private Context context;

    ArrayList<Data> itemList = new ArrayList<>();

    int[] images = new int[]{
            R.drawable.cevizli_bugday1543585834_1,
            R.drawable.cok_tahilli1543084924_1,
            R.drawable.findikli_yaban_mersinli1543585628_4,
            R.drawable.sade_koy_ekmegi1543084082_4,
            R.drawable.sos_siyah_zeytin1543577383_atolye_1,
            R.drawable.sos_fistik_ezmesi1543577401_1,
            R.drawable.sos_muhammara1543577172_atolye_4,
            R.drawable.sos_pesto1543577109_atolye_3,
            R.drawable.kahveli_sekersiz_21569499717_pastane_3,
            R.drawable.uzumlu_yulafli_sekersiz_21569499802_pastane_4,
            R.drawable.meyveli_sekersiz_21569499770,
            R.drawable.susamli_kavilca_cubuk_21569499925_pastane_1
    };

    String[] categoryName = new String[]{
            "Ekmek Çeşitleri",
            "Ekmek Çeşitleri",
            "Ekmek Çeşitleri",
            "Ekmek Çeşitleri",
            "240 Derece Atölye",
            "240 Derece Atölye",
            "240 Derece Atölye",
            "240 Derece Atölye",
            "Pastane",
            "Pastane",
            "Pastane",
            "Pastane"
    };

    String[] productNames = {
            "Cevizli Buğday",
            "Çok Tahıllı Ekmek",
            "Fındıklı Yaban Mersinli",
            "Sade Köy Ekmeği",
            "Sos Siyah Zeytin",
            "Sos Fıstık Ezmesi",
            "Sos Muhammara",
            "Sos Pesto",
            "Kahveli Şekersiz Kurabiye",
            "Üzümlü Yulaflı Şekersiz",
            "Meyveli Şekersiz",
            "Susamlı Kavılca Çubuk"
    };

    String[] headers = new String[]{
            "Ekmek Çeşitleri",
            "240 Derece Atölye",
            "Pastane",
    };

    int[] viewTypes = new int[]{
        1,
        0,
        0,
        0,
        0,
        1,
        0,
        0,
        0,
        0,
        1,
        0,
        0,
        0,
        0,
    };

    public CategoriesRecyclerViewAdapter(Context context) {
        this.context = context;
        recViewItemPressed = false;
        initData();
    }

    private void initData() {
        int countImage = 0;
        int countHeader = 0;
        for (int i = 0 ; i < viewTypes.length ; i++) {
            if(viewTypes[i] == 1){
                Header header = new Header(1,headers[countHeader], countHeader);
                itemList.add(header);
                countHeader++;
            }else if(viewTypes[i] == 0){
                Category category = new Category(images[countImage], categoryName[countImage] , productNames[countImage],0);
                itemList.add(category);
                countImage++;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v;
//        v = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
//        return new ViewHolder(v);

        View v;
        if (viewType == VIEW_TYPE_IMAGE) {
            v = LayoutInflater.from(context).inflate(R.layout.categories_item, parent, false);
            return new ViewHolder(v);
        } else{
            v = LayoutInflater.from(context).inflate(R.layout.item_header_names, parent, false);
            return new ViewHolderHeader(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_IMAGE:
                Category category = (Category) itemList.get(position);
                final String category_name = category.getCategoryName();
                String productName = category.getProductName();
                int resourceId = category.getId();
                ((ViewHolder) holder).getmDataImgView().setImageResource(resourceId);
                ((ViewHolder) holder).getmTextView().setText(productName);
                ((ViewHolder) holder).getmDataImgView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(recViewItemPressed) return;
                        recViewItemPressed = true;
                        switchContent(category_name);
                    }
                });
                break;
            case VIEW_TYPE_HEADER:
                Header header = (Header) itemList.get(position);
                ((ViewHolderHeader) holder).mTextViewHeader.setText(header.getHeader());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void switchContent(String categoryName) {
        if (context == null)
            return;
        if (context instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.switchContent(categoryName);
        }

    }

    @Override
    public int getItemViewType(int position) {
        int type = itemList.get(position).getType();
        if(type == 0)
            return VIEW_TYPE_IMAGE;
        else
            return VIEW_TYPE_HEADER;
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

    class ViewHolderHeader extends RecyclerView.ViewHolder {
        private final TextView mTextViewHeader;

        public ViewHolderHeader(View v) {
            super(v);
            mTextViewHeader = v.findViewById(R.id.category_header_name);
        }

        public TextView getmTextView() {
            return mTextViewHeader;
        }


    }

}
