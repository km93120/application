package com.example.khafi.myapplications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

       EditText username = (EditText) findViewById(R.id.username);
       EditText password = (EditText) findViewById(R.id.password);

    }


}
