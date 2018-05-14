package com.example.alexa.projettest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.alexa.projettest.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email =(EditText) findViewById(R.id.cemail);
        final EditText username =(EditText) findViewById(R.id.cusername);
        final EditText password = (EditText) findViewById(R.id.cpassword);
        Button register = (Button) findViewById(R.id.creationcompte);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();
                String usrname = username.getText().toString();             // on recupere les infos renseignees dans le formulaire
                String passwd = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonReponse = new JSONObject(response);          // comme on a encodé la reponse en json on cree un onject de type JSOn
                            boolean success = jsonReponse.getBoolean("success");

                            if(success)
                            {
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                intent.putExtra("username",username.toString());
                                RegisterActivity.this.startActivity(intent);            //si on reussit a creer un compte on retourne dans l'activité principale avec affiché "nom de compte"


                            }
                            else
                            {
                                AlertDialog.Builder builder =new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("La création de compte a échoué......")
                                        .setNegativeButton("Ressayer",null)
                                        .create()
                                        .show();


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(mail,usrname,passwd,responseListener);    // le passage d'arguments se fait ici
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);         // on execute la requete d'acces a la base de donnees.

            }
        });
    }
}
