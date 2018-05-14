package com.example.alexa.projettest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class LocationActivity extends AppCompatActivity {






    ListView listView;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Intent intent = getIntent();
        int        taille = intent.getIntExtra("tailleTableau",0);
        int    loyers  [] = new int[taille];
        String adresses[] = new String[taille];
        int    apercus [] = new int[taille];
        loyers= intent.getIntArrayExtra("tableauLoyers");
        adresses = intent.getStringArrayExtra("tableauAdresses");
        apercus  = intent.getIntArrayExtra("tableauApercus");
        listView = (ListView) findViewById(R.id.listview);
        Adaptter adaptter = new Adaptter(LocationActivity.this,adresses,loyers,apercus);
        listView.setAdapter(adaptter);

    }

}
