package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class paperview extends AppCompatActivity {
    Button btn_open;
    EditText ed_author,ed_journal,ed_citation;
    TextView title;
    String titletext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paperview);

    }
}
