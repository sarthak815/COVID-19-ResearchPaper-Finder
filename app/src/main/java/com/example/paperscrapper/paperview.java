package com.example.paperscrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class paperview extends AppCompatActivity {
    Button btn_open;
    TextView title,ed_author,ed_journal,ed_citation, ed_abstract;
    String titletext,url,authortext,journaltext,citationtext,abstracttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paperview);
        btn_open = (Button) findViewById(R.id.open) ;
        title = (TextView) findViewById(R.id.title1);
        ed_author = (TextView) findViewById(R.id.author);
        ed_journal = (TextView) findViewById(R.id.journal);
        ed_citation = (TextView) findViewById(R.id.citations);
        ed_abstract = (TextView) findViewById(R.id.abstract1);
        authortext = getIntent().getExtras().get("authors1").toString();
        journaltext = getIntent().getExtras().get("journal1").toString();
        citationtext = getIntent().getExtras().get("citations1").toString();
        abstracttext = getIntent().getExtras().get("abstract2").toString();
        titletext = getIntent().getExtras().get("ListViewClickedValue").toString();
        title.setText(titletext);
        ed_author.setText(authortext);
        ed_journal.setText(journaltext);
        ed_citation.setText(citationtext);
        ed_abstract.setText(abstracttext);
    }


    public void browser1(View view) {
        url = getIntent().getExtras().get("link1").toString();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
