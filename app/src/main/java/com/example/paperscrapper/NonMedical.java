package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

import java.util.ArrayList;

public class NonMedical extends AppCompatActivity {
    private static final String url = "https://whispering-beyond-66252.herokuapp.com/data/all";
    ListView listView;
    ArrayList<researchpapers> researchpapersArrayList;
    private Object StringRequest;

    public void previousActivity(View view){
        Intent maninMenu = new Intent(this, MainActivity.class);
        startActivity(maninMenu);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_medical);
        listView = findViewById(R.id.listview);
        researchpapersArrayList = new ArrayList<>();
        loadresearchpapersList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String TempListViewClickedValue = researchpapersArrayList.get(position).getTitle().toString();
                String link1  = researchpapersArrayList.get(position).getLink().toString();
                Intent intent = new Intent(NonMedical.this, paperview.class);
                intent.putExtra("ListViewClickedValue", TempListViewClickedValue);
                intent.putExtra("link1", link1);
                startActivity(intent);
            }
        });

    }
    private void loadresearchpapersList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        researchpapers researchpapers = new researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"));
                        researchpapersArrayList.add(researchpapers);
                        ListViewAdapter adapter = new ListViewAdapter(researchpapersArrayList, getApplicationContext());
                        listView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "There is an error", Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}
