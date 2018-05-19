package com.example.khafi.myapplications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;

public class aRegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText email, username,password;
    private RequestQueue queue;
    private aMyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_register);
        btnRegister = (Button) findViewById(R.id.creationcpte);
        email =  findViewById(R.id.eml);
        username =  findViewById(R.id.usrname);
        password =  findViewById(R.id.passwd);

        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new aMyRequest(this,queue);
        Log.d("email","z");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ee","e");
                String eml = email.toString();
                String usrname = username.toString();
                String pwd = password.toString();

                Log.d("email",eml);
                Log.d("email",usrname);

                Log.d("email",pwd);


                request.register(eml,usrname,pwd);

            }
        });

    }
}
