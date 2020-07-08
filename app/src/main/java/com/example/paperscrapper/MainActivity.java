package com.example.paperscrapper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button contribute, yes, no,cancel;


    public void getNewActivity(View view) {
        try {
            if(Integer.parseInt( view.getTag().toString()) == 1) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent nonMedical = new Intent(this, NonMedical.class);
                startActivity(nonMedical);
            } else if(Integer.parseInt( view.getTag().toString()) == 0) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent medical = new Intent(this, Medical.class);
                startActivity(medical);
            }
        } catch (Exception e) {
            Log.i("Exception Ocurred", "Look Below");
            e.printStackTrace();
        }
    }

    public void openDialog() {

        final Dialog dialog = new Dialog(this, R.style.FilterDialogTheme); // Context, this, etc.
        dialog.setContentView(R.layout.dialog_box);
        dialog.setTitle(R.string.dialog_title);
        dialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //geeksforgeeks.org/android-creating-multiple-screen-app/
    }


    public void contribute(View view) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Contribute/Rectify the database");
        builder1.setMessage("You can suggest rectifications for the app. \n" +
                "For example - Missing app data, incorrect information , etc.\n" +
                "You can also contribute to the database by giving information on paper that you want to be added and help the users to search through");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Proceed",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String url = "https://testingdeploy1307.herokuapp.com/data/form";
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }



}
