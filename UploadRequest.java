package com.example.khafi.myapplications;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UploadRequest extends StringRequest {

    private static final String UPLOAD_REQUEST_URL = "http://madadiatman.000webhostapp.com/register.php"; // l'url qui mène au fichier  PHP distant
    private Map<String,String> parametres;

    public UploadRequest(String description, int prix, int image, Response.Listener<String> listener)
    {
        super(Method.POST,UPLOAD_REQUEST_URL,listener,null);      // on utilise un conteneur de type clé - valeur pour stocker les informations renseignées dans formulaire
        parametres = new HashMap<>();

        parametres.put("description",description);
        parametres.put("prix","" + prix);
        parametres.put("image","" + image);


    }

    public Map<String, String> getParametres() {
        return parametres;
    }
}
