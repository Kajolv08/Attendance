package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAcitivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);


            setupUI();
            setupListeners();


    }
    private void setupUI() {
        username = findViewById(R.id.Username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
    }
    private void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername();
                Intent i = new Intent(getApplicationContext(),
                        MyBoard.class);
                startActivity(i);            }
        });


    }
    void checkUsername()
    {
        boolean isValid=true;

        if(isValid == true){
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }

    }
}