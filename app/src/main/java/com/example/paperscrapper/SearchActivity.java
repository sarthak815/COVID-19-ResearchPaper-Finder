package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class SearchActivity extends AppCompatActivity {

    EditText searchEditText;
    ListView searchListView;
    TextView numberTextView;
    //variable for setting the number of papers in the view
    String numberOfPapers;
    ArrayList<Researchpapers> researchpapersArrayList;



    private void closeKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager manageKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manageKeyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //The variable "domain" contains a string that contains "med" or "non-med" to search in the respective Data Bases
    //domain = med / non-med
    String domain;

    public void search(View view) {

        //calling the function closeKeyboard to close keyboard
        closeKeyboard();

        //get the search query and store it in the variable searchQuery
        String searchQuery = searchEditText.getText().toString();

        //setting up the search request
        String jsonInputString = "{\"search\": \"" + searchQuery + "\"}" ;
        Log.i("Searched", jsonInputString.toString());//adding the searchQuery to the log

        //URL type variable to store the url to Post Request
        URL url;
        //to open up the connection
        HttpURLConnection connection = null;

        //checking for what database to look the search query into
        if(domain == "non-med") {
            //set the url for non-med database
            url = new URL("https://whispering-beyond-66252.herokuapp.com/data/postreqALL");

        } else if (domain == "med") {

        } else {
            //Showing the error message to the log and toast
            Log.i("Error", "The database " + domain +" does not exist");
            Toast.makeText(getApplicationContext(), "The database " + domain +" does not exist", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            //opening the url connection
            //connection = (HttpURLConnection) url.openConnection();
            connection = (HttpsURLConnection) url.openConnection();

            //setting up a post request method
            connection.setRequestMethod("POST");

            //setting up a request type
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

        } catch (Exception e) {

            e.printStackTrace();
        }


        try {
            assert connection != null;
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch(Exception e) {
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            //code to print the response in a list view
            //System.out.println(response.toString());
            researchpapersArrayList.clear();

            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                int i;
                for (i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    Researchpapers researchpapers = new researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"),jsonObject1.getString("authors"),jsonObject1.getString("journal domain"),jsonObject1.getString("citations"),jsonObject1.getString("abstract"));
                    researchpapersArrayList.add(researchpapers);
                    ListViewAdapter adapter = new ListViewAdapter(researchpapersArrayList, getApplicationContext());
                    searchListView.setAdapter(adapter);
                }
                numberOfPapers = Integer.toString(i);

                numberTextView.setText(numberOfPapers);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //EditText to take in search query
        searchEditText = (EditText) findViewById(R.id.searchEditText);

        //ListView for showing the search results
        searchListView = (ListView) findViewById(R.id.searchListView);

        //TextView for showing the number of papers
        numberTextView = (TextView) findViewById(R.id.numberTextView);
        numberTextView.setText("0"); //setting the initial value as 0

        //getting the variable domain from the previoud activity
        domain = getIntent().getExtras().get("domain").toString();




        researchpapersArrayList = new ArrayList<>();



        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String TempListViewClickedValue = researchpapersArrayList.get(position).getTitle().toString();
                String link1 = researchpapersArrayList.get(position).getLink().toString();
                String authors1 = researchpapersArrayList.get(position).getAuthors().toString();
                String journal1 = researchpapersArrayList.get(position).getJournal().toString();
                String citations1 = researchpapersArrayList.get(position).getCitations().toString();
                String abstract2 = researchpapersArrayList.get(position).getAbstract1().toString();
                Intent paperView = new Intent(NonMedical.this, paperview.class);
                paperView.putExtra("ListViewClickedValue", TempListViewClickedValue);
                paperView.putExtra("authors1", authors1 );
                paperView.putExtra("journal1", journal1);
                paperView.putExtra("citations1", citations1);
                paperView.putExtra("link1", link1);
                paperView.putExtra("abstract2", abstract2);
                startActivity(paperView);
            }
        });




        //I actually don't know what these lines do but these are needed for sending the post request
        //and receiving the response
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }
}