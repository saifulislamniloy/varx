package com.niloy.varx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeControlActivity extends AppCompatActivity {
    private Button fan;
    private Button light;
    private TextView fanStatus;
    private TextView lightStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_control);
        uiInit();
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fanAPI();
            }
        });

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightAPI();
            }
        });
    }

    private void uiInit() {
        fan = findViewById(R.id.fan);
        light = findViewById(R.id.light);
        fanStatus = findViewById(R.id.fan_status);
        lightStatus = findViewById(R.id.light_status);
    }

    private void fanAPI() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://jsonplaceholder.typicode.com/posts" ;
        System.out.println(url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        System.out.println(jsonObject.getString("title"));

                    } catch (JSONException e) {
                        System.out.println(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
    }

    private void lightAPI() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://jsonplaceholder.typicode.com/posts" ;
        System.out.println(url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        System.out.println(jsonObject.getString("title"));

                    } catch (JSONException e) {
                        System.out.println(e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
    }
}