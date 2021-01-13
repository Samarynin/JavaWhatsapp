package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity
{
    private Button agreeBtn;
    private TextView PrivacyLink;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        agreeBtn = (Button) findViewById(R.id.agree_button);
        PrivacyLink = (TextView)findViewById(R.id.privacy);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        PrivacyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacyIntent = new Intent(WelcomeActivity.this, PrivacyActivity.class);
                startActivity(privacyIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(currentUser != null)
        {
            Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(mainIntent);
        }
    }
}