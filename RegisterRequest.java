package com.example.khafi.myapplications;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest      //extends StringRequest pour pouvoir utiliser les méthodes liées a Volley
{
    private static final String REGISTER_REQUEST_URL = "http://madadiatman.000webhostapp.com/register.php"; // l'url qui mène au fichier  PHP distant
    private Map<String,String> parametres;

    public RegisterRequest(String email, String username, String password, Response.Listener<String> listener)
    {
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);      // on utilise un conteneur de type clé - valeur pour stocker les informations renseignées dans formulaire
        parametres = new HashMap<>();
        parametres.put("email",email);
        parametres.put("username",username);
        parametres.put("password",password);


    }

    public Map<String, String> getParametres() {
        return parametres;
    }
}
