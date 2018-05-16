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
import android.util.Log;
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