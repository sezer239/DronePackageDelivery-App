package com.example.dronepackagedelivery_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dronepackagedelivery_app.dummy.DummyActivity;
import com.example.dronepackagedelivery_app.dummy.DummyContent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, DummyActivity.class));
        finish();
    }
}
