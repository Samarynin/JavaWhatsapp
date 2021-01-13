package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class PrivacyActivity extends AppCompatActivity {

    WebView privacyWebView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        privacyWebView = (WebView)findViewById(R.id.webview);
        backButton = (Button)findViewById(R.id.back_button);

        privacyWebView.loadUrl("https://docs.google.com/document/d/1meFhST2H1NziG-94JrMt28mN5SD92W6rEbKV0wy4DO4/edit");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacyIntent = new Intent(PrivacyActivity.this, WelcomeActivity.class);
                startActivity(privacyIntent);
            }
        });
        
    }
}