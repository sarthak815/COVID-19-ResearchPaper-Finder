package com.example.paperscrapper;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
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
    TextView numberTextView, loading_text1;
    //variable for setting the number of papers in the view
    String numberOfPapers;
    ArrayList<Researchpapers> researchpapersArrayList;
    String nonMed;
    String med;
    private ProgressBar spinner;
    Button search;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        spinner=(ProgressBar)findViewById(R.id.progressBar_search);
        loading_text1 = (TextView)findViewById(R.id.loading_text_search);



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


        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    search(search);
                    return true;
                }
                return false;
            }
        });



        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String TempListViewClickedValue = researchpapersArrayList.get(position).getTitle().toString();
                String link1 = researchpapersArrayList.get(position).getLink().toString();
                String authors1 = researchpapersArrayList.get(position).getAuthors().toString();
                String journal1 = researchpapersArrayList.get(position).getJournal().toString();
                String citations1 = researchpapersArrayList.get(position).getCitations().toString();
                int f = (int) Float.parseFloat(citations1);
                citations1 = String.valueOf(f);
                String abstract2 = researchpapersArrayList.get(position).getAbstract1().toString();
                Intent paperView = new Intent(SearchActivity.this, paperview.class);
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    //this function closes the keyboard when it is called
    private void closeKeyboard() {

        spinner.setVisibility(View.GONE);
        loading_text1.setVisibility(View.GONE);

        View view = getCurrentFocus(); //gets the view currently in focus

        //check if something is in focus or not
        if (view != null) {

            //get the input manager and close the keyboard
            InputMethodManager manageKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manageKeyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }





    //The variable "domain" contains a string that contains "med" or "non-med" to search in the respective Data Bases
    //domain = med / non-med
    String domain;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void search(View view) {
        spinner.setVisibility(View.VISIBLE);
        loading_text1.setVisibility(View.VISIBLE);
        med = "med";
        nonMed = "non-med";

        //calling the function closeKeyboard to close keyboard

        closeKeyboard();
        //get the search query and store it in the variable searchQuery
        String searchQuery = searchEditText.getText().toString();

        //setting up the search request
        String jsonInputString = "{\"search\": \"" + searchQuery + "\"}" ;
        Log.i("Searched", jsonInputString.toString());//adding the searchQuery to the log

        //URL type variable to store the url to Post Request
        URL url = null;
        //to open up the connection
        HttpURLConnection connection = null;

        //checking for what database to look the search query into
        if(domain.equals(nonMed)) {
            //set the url for non-med database
            try {
                url = new URL("https://testingdeploy1307.herokuapp.com/data/NonMedical/postreqALL");
            } catch(Exception e) {
                closeKeyboard();
                e.printStackTrace();
            }

        } else if (domain.equals(med)) {
            try {
                url = new URL("https://testingdeploy1307.herokuapp.com/data/Medical/postreqALL");
            } catch(Exception e) {
                closeKeyboard();
                e.printStackTrace();
            }
        } else {
            closeKeyboard();
            //Showing the error message to the log and toast
            Log.i("Error", "The database " + domain +" does not exist");
            Toast.makeText(getApplicationContext(), "The database " + domain +" does not exist", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            spinner.setVisibility(View.VISIBLE);
            loading_text1.setVisibility(View.VISIBLE);
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
                    Researchpapers researchpapers = new Researchpapers(jsonObject1.getString("Title"), jsonObject1.getString("link"),jsonObject1.getString("authors"),jsonObject1.getString("journal domain"),jsonObject1.getString("citations"),jsonObject1.getString("abstract"));
                    researchpapersArrayList.add(researchpapers);
                    searchListViewAdapter adapter = new searchListViewAdapter(researchpapersArrayList, getApplicationContext());
                    searchListView.setAdapter(adapter);
                    closeKeyboard();
                }
                numberOfPapers = Integer.toString(i);

                numberTextView.setText(numberOfPapers);



            } catch (JSONException e) {
                closeKeyboard();
                e.printStackTrace();
                spinner.setVisibility(View.GONE);
                loading_text1.setVisibility(View.GONE);
            }
        } catch (IOException e) {
            closeKeyboard();
            e.printStackTrace();
        }

    }



}