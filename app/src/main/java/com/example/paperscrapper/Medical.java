package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Medical extends AppCompatActivity {

    public void previousActivity(View view){
        Intent maninMenu = new Intent(this, MainActivity.class);
        startActivity(maninMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
    }
}
