package com.niloy.varx;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.SeekBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class BotControllerActivity extends AppCompatActivity {
    private WebView webView = null;
    private SeekBar slider1;
    private SeekBar slider2;

    private Button front;
    private Button back;
    private Button left;
    private Button right;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_controller);

        hideActionBar();
        uiInit();

        webviewFunction();

        sliderOneFunction();
        sliderTwoFunction();

        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frontAPI();
            }
        });
//        backAPI();
//        rightAPI();
//        leftAPI();
//        stopAPI();
    }

    private void frontAPI() {
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
    private void backAPI() {
    }
    private void rightAPI() {
    }
    private void leftAPI() {
    }
    private void stopAPI() {
    }

    private void webviewFunction() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(new WebViewClientImpl(this));

        webView.loadUrl("https://www.w3schools.com/");
    }

    private void sliderTwoFunction() {
        slider2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void sliderOneFunction() {
        slider1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void uiInit(){
        webView = (WebView) findViewById(R.id.webview);
        slider1 = findViewById(R.id.slider1);
        slider2 = findViewById(R.id.slider2);
        front = findViewById(R.id.go_forward);
        back = findViewById(R.id.go_backward);
        left = findViewById(R.id.go_right);
        right = findViewById(R.id.go_left);
        stop = findViewById(R.id.stop);
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }



}