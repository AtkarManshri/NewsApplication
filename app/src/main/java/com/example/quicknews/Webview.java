package com.example.quicknews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {
Toolbar toolbar;
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        toolbar=findViewById(R.id.toolbar);
        webView=findViewById(R.id.webview);
        setSupportActionBar(toolbar);
        //to get url which is pasing using adapterClass
        Intent intent=getIntent();
        //to load url in web view
        String url=intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}