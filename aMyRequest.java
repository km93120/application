package com.example.khafi.myapplications;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class aMyRequest{


    private Context context;
    private RequestQueue queue;

    public aMyRequest(Context context, RequestQueue queue)
    {
        this.context = context;
        this.queue = queue;
    }

    public void register(final String email, final String username, final String password)
    {
        String url = "http://madadiatman.000webhostapp.com/aregister.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("deeeee",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("d", "ERROR" + error);
            }
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError
            {
                Map<String,String> map = new HashMap<>();
                map.put("email",email);
                map.put("username",username);
                map.put("password",password);
                return super.getParams();
            }

        };

        queue.add(request);
    }
}
