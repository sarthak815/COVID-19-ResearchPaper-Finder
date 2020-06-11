package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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
    private static final String url = "https://whispering-beyond-66252.herokuapp.com/data/all";
    ListView listView;
    ArrayList<researchpapers> researchpapersArrayList;
    private Object StringRequest;
    TextView length_paper;
    String num1;

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
                String authors1  = researchpapersArrayList.get(position).getAuthors().toString();
                String journal1  = researchpapersArrayList.get(position).getJournal().toString();
                String citations1  = researchpapersArrayList.get(position).getCitations().toString();
                String abstract2 = researchpapersArrayList.get(position).getAbstract1().toString();
                Intent intent = new Intent(NonMedical.this, paperview.class);
                intent.putExtra("ListViewClickedValue", TempListViewClickedValue);
                intent.putExtra("authors1", authors1 );
                intent.putExtra("journal1", journal1);
                intent.putExtra("citations1", citations1);
                intent.putExtra("link1", link1);
                intent.putExtra("abstract2", abstract2);
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
                        researchpapers researchpapers = new researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"),jsonObject1.getString("authors"),jsonObject1.getString("journal domain"),jsonObject1.getString("citations"),jsonObject1.getString("abstract"));
                        researchpapersArrayList.add(researchpapers);
                        ListViewAdapter adapter = new ListViewAdapter(researchpapersArrayList, getApplicationContext());
                        listView.setAdapter(adapter);
                        num1 = Integer.toString(i);
                    }
                    length_paper= (TextView) findViewById(R.id.num_paper);

                    length_paper.setText(num1);

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
