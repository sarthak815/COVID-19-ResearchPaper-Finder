package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<researchpapers> researchpapersArrayList;
    private Object StringRequest;

    public void getNewActivity(View view) {
        try {
            if(Integer.parseInt( view.getTag().toString()) == 1) {
                Intent nonMedical = new Intent(this, NonMedical.class);
                startActivity(nonMedical);
            } else if(Integer.parseInt( view.getTag().toString()) == 0) {
                Intent medical = new Intent(this, Medical.class);
                startActivity(medical);
            }
        } catch (Exception e) {
            Log.i("Exception Ocurred", "Look Below");
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //geeksforgeeks.org/android-creating-multiple-screen-app/
    }



}
