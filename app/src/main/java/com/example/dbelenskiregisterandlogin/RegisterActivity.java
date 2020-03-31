package com.example.dbelenskiregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    Validator validator;
    DatabaseHelper db;
    EditText fname;
    EditText lname;
    EditText email;
    EditText dob;
    EditText pass;
    EditText cnfpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = (Button) findViewById(R.id.registerButton);
        fname = (EditText) findViewById(R.id.fnameEditText);
        lname = (EditText) findViewById(R.id.lnameEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        dob = (EditText) findViewById(R.id.dobEditText);
        pass = (EditText) findViewById(R.id.regPassEditText);
        cnfpass = (EditText) findViewById(R.id.confPassEditText);
        db = new DatabaseHelper(this);
        validator = new Validator();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname.setError(null);
                lname.setError(null);
                email.setError(null);
                dob.setError(null);
                pass.setError(null);
                cnfpass.setError(null);

                Boolean fnameBool = validator.nameValidate(fname.getText().toString().trim());
                Boolean lnameBool = validator.nameValidate(lname.getText().toString().trim());
                Boolean emailBool = validator.emailValidate(email.getText().toString().trim());
                Boolean dobBool = validator.dateValidate(dob.getText().toString().trim());
                Boolean passBool = validator.registerPassValidate(pass.getText().toString().trim());
                Boolean conf = pass.getText().toString().trim().equals(cnfpass.getText().toString().trim());

                if (fnameBool && lnameBool && emailBool && dobBool && passBool && conf){
                    long res = db.addUser(email.getText().toString().trim(), pass.getText().toString().trim(), fname.getText().toString().trim());
                    if (res == -1){
                        Context context = getApplicationContext();
                        CharSequence message = "Registration error";
                        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence message = "Registered Successfully!";
                        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                        toast.show();
                        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(loginIntent);
                    }

                }
                else{
                    if(!fnameBool){
                        fname.setError("Please input a valid name.");
                    }
                    if(!lnameBool){
                        lname.setError("Please input a valid name.");
                    }
                    if(!emailBool){
                        email.setError("Please input a valid email.");
                    }
                    if(!dobBool){
                        dob.setError("Please input a valid Date of Birth.");
                    }
                    if(!passBool){
                        pass.setError("Please input a valid password.");
                    }
                    if(!conf){
                        pass.setError("Passwords do not match!");
                        cnfpass.setError("Passwords do not match!.");
                    }
                }
            }
        });
    }
}
