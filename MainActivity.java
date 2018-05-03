package com.example.khafi.myapplications;

import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    Button Agence;
    Button Louer;
    public Button Vendre;
    public Button Acheter;
    Button LoginCompte;
    Button CreationCompte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        final int [] loyers = new int[cursor.getCount()];
        final int [] apercus = new int[cursor.getCount()];
        final String [] adresses = new String[cursor.getCount()];
        cursor.moveToFirst();


        int i = 0;



        while (!cursor.isAfterLast() )
        {
            loyers  [i] = cursor.getInt(0);
            adresses[i] = cursor.getString(1);
            apercus [i] = cursor.getInt(2);

            i++;
            cursor.moveToNext();
        }

        Agence = findViewById(R.id.button);
        Agence.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(
                        MainActivity.this,
                        MapsActivity.class
                );



                startActivity(intent);

            }

        });


        Louer = findViewById(R.id.button2);
        Louer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent
                        (
                        MainActivity.this,
                        LocationActivity.class
                );
                intent.putExtra("tableauLoyers",loyers);
                intent.putExtra("tableauApercus",apercus);
                intent.putExtra("tableauAdresses",adresses);


                startActivity(intent);

            }



        });


        LoginCompte = findViewById(R.id.button5);
       LoginCompte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(
                        MainActivity.this,
                       LoginActivity.class
                );


                startActivity(intent);

            }



        });


        CreationCompte = findViewById(R.id.button6);
        CreationCompte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(
                        MainActivity.this,
                        RegisterActivity.class
                );


                startActivity(intent);

            }



        });
    }
}