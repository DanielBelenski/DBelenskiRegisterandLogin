package com.example.dbelenskiregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// first activity of the app and has links to both login and register activities
public class LoginActivity extends AppCompatActivity {

    //instantiating elements I will need later
    TextView registerTextView;
    Button loginButton;
    EditText email;
    EditText password;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //this command gets rid of the title bar of the app
        getSupportActionBar().hide();

        //creating a database helper to interact with SQLite database
        db = new DatabaseHelper(this);

        //creating link to register activity
        registerTextView = (TextView) findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        //setting values for email and password references
        email = (EditText) findViewById(R.id.loginEmailEditText);
        password = (EditText) findViewById(R.id.passEditText);


        //creating link and conditional logic to get to the home activity
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //clear existing errors from EditTexts
                email.setError(null);
                password.setError(null);

                //checking email, password combination in database
                Boolean exists = db.checkUserExists(email.getText().toString().trim(), password.getText().toString().trim());

                //if combination exists
                if (exists) {
                    //change activities to the home activity
                    Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(loginIntent);
                }
                //if data is not found/ doesn't exist/ is incorrect
                else {
                    //toast an error message
                    Context context = getApplicationContext();
                    CharSequence message = "Please input valid login information.";
                    Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                    toast.show();
                    //set errors in login fields for a visual cue something is wrong
                    email.setError("Invalid username or password");
                    password.setError("Invalid username or password");
                }
            }
        });

    }
}
