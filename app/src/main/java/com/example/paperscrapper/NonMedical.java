package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.FieldClassification;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private static final String url = "https://raw.githubusercontent.com/R13D13/sturdy-umbrella/master/NonMedicalData.json";
    RecyclerView listView;
    private ProgressBar spinner;
    ArrayList<Researchpapers> researchpapersArrayList;

    TextView length_paper, loading_text1;
    String num1;

    public void search(View view) {
        Intent searchActivity = new Intent(NonMedical.this, SearchActivity.class);
        searchActivity.putExtra("domain", "non-med");
        startActivity(searchActivity);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_medical);
        listView = (RecyclerView)findViewById(R.id.listview);
        researchpapersArrayList = new ArrayList<>();
        spinner=(ProgressBar)findViewById(R.id.progressBar1);
        loading_text1 = (TextView)findViewById(R.id.loading_text1);

        spinner.setVisibility(View.VISIBLE);
        loading_text1.setVisibility(View.VISIBLE);

        loadresearchpapersList();


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
                        Researchpapers researchpapers = new Researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"),jsonObject1.getString("authors"),jsonObject1.getString("journal domain"),jsonObject1.getString("citations"),jsonObject1.getString("abstract"));
                        researchpapersArrayList.add(researchpapers);
                        ListViewAdapter adapter = new ListViewAdapter(researchpapersArrayList, getApplicationContext());
                        listView.setAdapter(adapter);
                        listView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        num1 = Integer.toString(i+1);
                    }
                    spinner.setVisibility(View.GONE);
                    loading_text1.setVisibility(View.GONE);
                    length_paper= (TextView) findViewById(R.id.num_paper);

                    length_paper.setText(num1);

                } catch (JSONException e) {
                    spinner.setVisibility(View.GONE);
                    loading_text1.setVisibility(View.GONE);
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
