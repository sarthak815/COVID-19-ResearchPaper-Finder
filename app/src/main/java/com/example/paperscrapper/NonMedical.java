package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.View;
import android.widget.Button;

public class NonMedical extends AppCompatActivity {

    public void previousActivity(View view){
        Intent maninMenu = new Intent(this, MainActivity.class);
        startActivity(maninMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_medical);
    }
}
