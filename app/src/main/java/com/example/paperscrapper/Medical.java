package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
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

public class Medical extends AppCompatActivity {
    private static final String url = "https://whispering-beyond-66252.herokuapp.com/data/all";
    private ProgressBar spinner;
    RecyclerView listView;
    ArrayList<Researchpapers> researchpapersArrayList;

    TextView length_paper, loading_text, loading_text2;
    String num1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
        listView = (RecyclerView) findViewById(R.id.listview1);
        researchpapersArrayList = new ArrayList<>();
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        loading_text = (TextView)findViewById(R.id.loading_text);
        loading_text2 = (TextView)findViewById(R.id.loading_text2_med);
        spinner.setVisibility(View.GONE);
        loading_text2.setVisibility(View.GONE);
        loading_text.setVisibility(View.GONE);
        loadresearchpapersList();



    }
    private void loadresearchpapersList() {
        spinner.setVisibility(View.VISIBLE);
        loading_text.setVisibility(View.VISIBLE);

        final View v = loading_text; // your view
        loading_text.postDelayed(new Runnable() {
            public void run() {
                TranslateAnimation animate = new TranslateAnimation(0, v.getWidth(),0,0);
                animate.setDuration(500);
                animate.setFillAfter(true);
                v.startAnimation(animate);
                v.setVisibility(View.GONE);

                loading_text2.setVisibility(View.VISIBLE);
            }

        }, 7000);

        com.android.volley.toolbox.StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Researchpapers researchpapers = new Researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"),jsonObject1.getString("authors"),jsonObject1.getString("journal domain"),jsonObject1.getString("citations"),jsonObject1.getString("abstract"));
                        researchpapersArrayList.add(researchpapers);
                        ListViewAdapterMedical adapter = new ListViewAdapterMedical(researchpapersArrayList, getApplicationContext());
                        listView.setAdapter(adapter);
                        listView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        num1 = Integer.toString(i+1);
                    }
                    length_paper= (TextView) findViewById(R.id.num_paper1);

                    length_paper.setText(num1);
                    spinner.setVisibility(View.GONE);
                    loading_text.setVisibility(View.GONE);
                    loading_text2.setVisibility(View.GONE);
                } catch (JSONException e) {
                    spinner.setVisibility(View.GONE);
                    loading_text.setVisibility(View.GONE);
                    loading_text2.setVisibility(View.GONE);
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
