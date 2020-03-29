package com.example.dbelenskiregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView registerTextView;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerTextView = (TextView) findViewById(R.id.registerTextView);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //TODO: validation code here
                Intent loginIntent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(loginIntent);
            }
        });

    }
}
