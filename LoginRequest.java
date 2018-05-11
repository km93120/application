package com.example.khafi.myapplications;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest { // hérite de stringRequest , classe de la bibliotheque Volley, necessaire a l'utilsiation de l'architecture REST

    private static final String LOGIN_REQUEST_URL = "http://madadiatman.000webhostapp.com/login.php"; // l'url qui mène au fichier  PHP distant
    private Map<String,String> parametres;

    public LoginRequest (String username,String password, Response.Listener<String> listener)
    {
        super(Method.POST,LOGIN_REQUEST_URL,listener,null);      // on utilise un conteneur de type clé - valeur pour stocker les informations renseignées dans formulaire
        parametres = new HashMap<>();
        parametres.put("username",username);        // arguments passés sous leur nom ds la base de donnees, ne fonctionne pas
        parametres.put("password",password);


    }

    public Map<String, String> getParametres() {
        return parametres;
    }
}
