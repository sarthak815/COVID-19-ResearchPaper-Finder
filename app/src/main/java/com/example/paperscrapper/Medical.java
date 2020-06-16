package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.List;

/*public class Medical extends AppCompatActivity {
    private static final String url = "https://whispering-beyond-66252.herokuapp.com/data/all";
    RecyclerView listView;
    ArrayList<Researchpapers> researchpapersArrayList;
    ListViewAdapterMedical listViewAdapterMedical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
        listView = (RecyclerView) findViewById(R.id.listview1);
        researchpapersArrayList = new ArrayList<>();

        listView.setLayoutManager(new LinearLayoutManager(this));
        listViewAdapterMedical = new ListViewAdapter(listView, researchpapersArrayList, this);
        listView.setAdapter(ListViewAdapterMedical);


        listViewAdapterMedical.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (researchpapersArrayList.size() <= 20) {
                    researchpapersArrayList.add(null);
                    listViewAdapterMedical.notifyItemInserted(researchpapersArrayList.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            researchpapersArrayList.remove(researchpapersArrayList.size() - 1);
                            listViewAdapterMedical.notifyItemRemoved(researchpapersArrayList.size());

                            //Generating more data
                            int index = researchpapersArrayList.size();
                            int end = index + 10;

                            com.android.volley.toolbox.StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                                        for (int i = index; i < end; i++) {
                                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                            Researchpapers researchpapers = new Researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"),jsonObject1.getString("authors"),jsonObject1.getString("journal domain"),jsonObject1.getString("citations"),jsonObject1.getString("abstract"));
                                            researchpapersArrayList.add(researchpapers);

                                        }
                                        listViewAdapterMedical.notifyDataSetChanged();
                                        listViewAdapterMedical.setLoaded();

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

                        },5000);
                } else {
                    Toast.makeText(MainActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}*/
public class Medical extends AppCompatActivity {

    private static final String url = "https://whispering-beyond-66252.herokuapp.com/data/all";
    private List<Researchpapers> researchpapersArrayList;
    private ListViewAdapterMedical listViewAdapterMedical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
        researchpapersArrayList = new ArrayList<>();

        //find view by id and attaching adapter for the RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listview1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listViewAdapterMedical = new ListViewAdapterMedical(recyclerView, researchpapersArrayList, this);
        recyclerView.setAdapter(listViewAdapterMedical);

        //set load more listener for the RecyclerView adapter
        listViewAdapterMedical.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (researchpapersArrayList.size() <= 20) {
                    researchpapersArrayList.add(null);
                    listViewAdapterMedical.notifyItemInserted(researchpapersArrayList.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            researchpapersArrayList.remove(researchpapersArrayList.size() - 1);
                            listViewAdapterMedical.notifyItemRemoved(researchpapersArrayList.size());

                            //Generating more data
                            final int index = 10;
                            final int end = index + 10;
                            com.android.volley.toolbox.StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                                        for (int i = index; i < end; i++) {
                                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                            Researchpapers researchpapers = new Researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"), jsonObject1.getString("authors"), jsonObject1.getString("journal domain"), jsonObject1.getString("citations"), jsonObject1.getString("abstract"));
                                            researchpapersArrayList.add(researchpapers);

                                        }
                                        listViewAdapterMedical.notifyDataSetChanged();
                                        listViewAdapterMedical.setLoaded();

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
                            RequestQueue requestQueue = Volley.newRequestQueue(Medical.this);
                            requestQueue.add(stringRequest);
                        }

                    }, 5000);
                } else {
                    Toast.makeText(Medical.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}


