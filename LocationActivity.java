package com.example.khafi.myapplications;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

public class LocationActivity extends AppCompatActivity {






    ListView listView;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        SQLiteOpenHelper achatLocationDatabaseHelper = new AchatLocationDatabaseHelper(this);     //instanciation de l'assistant de gestion de base de donnees
        SQLiteDatabase db = achatLocationDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("LOCATION",              //on recupere tout le contenu dans la table
                new String[]{"LOYER", "ADRESSE","APERCU"},
                null,
                null,
                null,
                null,
                null
        );

        int [] loyers = new int[cursor.getCount()];
        int [] apercus = new int[cursor.getCount()];
        String [] adresses = new String[cursor.getCount()];
        cursor.moveToFirst();
        Log.d("cursor_count","" + cursor.getCount()); // probleme a ce niveau

        int i = 0;



        while (!cursor.isAfterLast() )
        {
            loyers  [i] = cursor.getInt(0);
            Log.d("cursor_count","" + loyers[i]);
            Log.d("cursor_count","" + loyers.length);
            adresses[i] = cursor.getString(1);
            apercus [i] = cursor.getInt(2);

            i++;
            cursor.moveToNext();
        }
        listView = findViewById(R.id.listview);
        Adaptter adaptter = new Adaptter(LocationActivity.this,adresses,loyers,apercus);
        listView.setAdapter(adaptter);

    }

}
