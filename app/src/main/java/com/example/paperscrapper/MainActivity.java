package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://api.jsonbin.io/b/5ed2a8bf7741ef56a564d888";
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
        listView = findViewById(R.id.listview);
        researchpapersArrayList = new ArrayList<>();
        loadresearchpapersList();
    }

    private void loadresearchpapersList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("researchpapers");
                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        researchpapers researchpapers = new researchpapers(jsonObject1.getString("title"), jsonObject1.getString("link"));
                        researchpapersArrayList.add(researchpapers);
                        ListViewAdapter adapter = new ListViewAdapter(researchpapersArrayList, getApplicationContext());
                        listView.setAdapter(adapter);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        RequestQueue.add(stringRequest);
    }


}
