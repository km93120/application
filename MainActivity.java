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

    Button btnAgence;
    Button btnLouer;
    public Button btnVendre;
    public Button btnAcheter;
    Button btnLoginCompte;
    Button btnCreationCompte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAgence = findViewById(R.id.button);
        btnAgence.setOnClickListener(new View.OnClickListener(){
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


        btnLouer = findViewById(R.id.button2);
        btnLouer.setOnClickListener(new View.OnClickListener(){
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




        btnVendre = findViewById(R.id.button3);
        btnVendre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(
                        MainActivity.this,
                        SaleActivity.class
                );



                startActivity(intent);

            }

        });

        btnLoginCompte = findViewById(R.id.button5);
        btnLoginCompte.setOnClickListener(new View.OnClickListener(){
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


        btnCreationCompte = findViewById(R.id.button6);
        btnCreationCompte.setOnClickListener(new View.OnClickListener(){
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