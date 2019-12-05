package com.example.dronepackagedelivery_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dronepackagedelivery_app.data.Cart;
import com.example.dronepackagedelivery_app.data.ProductData;
import com.example.dronepackagedelivery_app.dummy.DummyActivity;
import com.example.dronepackagedelivery_app.fragments.CartFragment;
import com.example.dronepackagedelivery_app.fragments.CategoriesFragment;
import com.example.dronepackagedelivery_app.fragments.LoginFragment;
import com.example.dronepackagedelivery_app.fragments.ProductFragment;
import com.example.dronepackagedelivery_app.fragments.ShopFragment;
import com.example.dronepackagedelivery_app.map.MapsActivity;
import com.example.dronepackagedelivery_app.ınterfaces.OnCartChangedListener;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements CategoriesFragment.OnFragmentInteractionListener,
        ShopFragment.OnFragmentInteractionListener,ProductFragment.OnListFragmentInteractionListener,
        OnCartChangedListener, CartFragment.OnFragmentInteractionListener , CartFragment.OnItemsPurchased,
        LoginFragment.OnLoginListener {

    private TextView totalPrice;
    private ImageButton cartButton;
    private ShopFragment shopFragment;
    private Cart shoppingCart;

    private ImageButton trackOrderButton;
    private ImageView orderCountIcon;

    //TODO: PLACEHOLDER

    public boolean hasOrderIsGiven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shoppingCart = new Cart();
        setContentView(R.layout.activity_main);

        trackOrderButton = findViewById(R.id.track_order_button);
        trackOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasOrderIsGiven){
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, "Herhangi bir siparişiniz yok", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


        orderCountIcon = findViewById(R.id.order_count_icon);



        totalPrice = findViewById(R.id.cart_price);
        cartButton = findViewById(R.id.cart_button);

        trackOrderButton.setVisibility(View.INVISIBLE);
        orderCountIcon.setVisibility(View.INVISIBLE);
        totalPrice.setVisibility(View.INVISIBLE);
        cartButton.setVisibility(View.INVISIBLE);

        getSupportFragmentManager().beginTransaction().add(R.id.container,
                LoginFragment.newInstance(), "LOGIN_FRAGMENT").commit();

        /*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new CategoriesFragment());
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();*/

    }

    @Override
    public void onBackPressed() {

        CartFragment cartFragment = (CartFragment) getSupportFragmentManager().findFragmentByTag("CART_FRAGMENT");
        Fragment sop = getSupportFragmentManager().findFragmentByTag("SHOP_FRAG");
        Fragment cat = getSupportFragmentManager().findFragmentByTag("CAT_FRAG");

        if(cartFragment != null){
            getSupportFragmentManager().beginTransaction().remove(cartFragment).commit();
            getSupportFragmentManager().beginTransaction().show(shopFragment).commit();
        }else if(sop != null){
            getSupportFragmentManager().beginTransaction().remove(sop).commit();
            getSupportFragmentManager().beginTransaction().show(cat).commit();
        }else if(cat != null){
            finish();
        }

      /*  List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(Fragment f : fragments){
            Log.d("HHH" , "frag name " + f.toString());
            if(f instanceof ShopFragment){
                getSupportFragmentManager().beginTransaction().remove(f).commit();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, new CategoriesFragment());
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                Log.d("HHH" , "BURAA");
                return;
            }else{
                super.onBackPressed();
                Log.d("HHH" , "KAPA");
                finish();
            }

        }*/

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



    public void switchContent(String categoryName) {
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);
//        this.finish();
/*
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        shopFragment = ShopFragment.newInstance(categoryName,shoppingCart);
        ft.replace(R.id.container, shopFragment, "SHOP_FRAG");
        ft.addToBackStack(null);
        ft.commit(); */
        Log.d("HHH" , "TAAAAG "+ categoryName);
        Fragment cat = getSupportFragmentManager().findFragmentByTag("CAT_FRAG");
        ShopFragment sf = ShopFragment.newInstance(categoryName,shoppingCart);
        getSupportFragmentManager().beginTransaction().hide(cat).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container, sf, "SHOP_FRAG").commit();
    }

    @Override
    public void onListFragmentInteraction(ProductData productData) {

    }

    private void printHashMap(HashMap<ProductData, Integer> cart){
        for (ProductData name: cart.keySet()){
            String key = name.getmProductName();
            String value = cart.get(name).toString();
            Log.d("FFF" , key + " " + value);
        }
    }

    @Override
    public void onItemsPurchased() {
        /*Toast toast = Toast.makeText(this, "Thanks for your purhcase", Toast.LENGTH_LONG);
        toast.show();
        shoppingCart.clear();
        CartFragment cartFragment = (CartFragment) getSupportFragmentManager().findFragmentByTag("CART_FRAGMENT");
        getSupportFragmentManager().beginTransaction().remove(cartFragment).commit();
        getSupportFragmentManager().beginTransaction().show(shopFragment).commit();
        */
        hasOrderIsGiven = true;
        orderCountIcon.setVisibility(View.VISIBLE);
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginCorrect() {
        totalPrice.setVisibility(View.VISIBLE);
        cartButton.setVisibility(View.VISIBLE);
        trackOrderButton.setVisibility(View.VISIBLE);

        shoppingCart = new Cart();
        shoppingCart.addOnCartChangedListener(this);
        shopFragment = ShopFragment.newInstance("1",shoppingCart);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment cartFragment = (CartFragment) getSupportFragmentManager().findFragmentByTag("CART_FRAGMENT");
                if(cartFragment == null){
                    getSupportFragmentManager().beginTransaction().hide(shopFragment).commit();
                    getSupportFragmentManager().beginTransaction().add(R.id.container, CartFragment.newInstance(shoppingCart, MainActivity.this), "CART_FRAGMENT").commit();
                }else{
                    getSupportFragmentManager().beginTransaction().remove(cartFragment).commit();
                    getSupportFragmentManager().beginTransaction().show(shopFragment).commit();
                }
            }
        });

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("LOGIN_FRAGMENT");
        getSupportFragmentManager().beginTransaction().remove(loginFragment).commit();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new CategoriesFragment(),"CAT_FRAG");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();


    }

    @Override
    public void onLoginWrong() {

    }

    @Override
    public void onItemChangedListener(ProductData productData, int count) {

    }

    @Override
    public void onCartChangedListener(HashMap<ProductData, Integer> cart, float totalPrice) {
        printHashMap(cart);
        Log.d("FFF", "totalPrice = " + totalPrice);
        this.totalPrice.setText( String.valueOf(totalPrice) + " TL" );
    }

    @Override
    public void onItemRemovedListener(ProductData productData) {
        this.totalPrice.setText( String.valueOf(shoppingCart.getTotalCartPrice()) + " TL" );
    }

    @Override
    public void onCartClearedListener() {

    }
}