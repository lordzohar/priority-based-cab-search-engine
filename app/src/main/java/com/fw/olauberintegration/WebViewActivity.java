package com.fw.olauberintegration;

//import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String url = getIntent().getStringExtra("url");
        //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        myWebView = (WebView) findViewById(R.id.loader);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(WebViewActivity.this,MainActivity.class));
        finish();
    }
 
}
