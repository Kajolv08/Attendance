package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    protected String rollNumber, Name, Std, address,Phone,Email;
    TextView roll,name,std,add,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        roll=findViewById(R.id.tv_roll);
        name=findViewById(R.id.tv_name);
        std=findViewById(R.id.tv_std);
        add=findViewById(R.id.tv_address);
        phone=findViewById(R.id.tv_phone);
        email=findViewById(R.id.tv_email);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            rollNumber = bundle.getString("RollNo");
            Name = bundle.getString("Name");
            Std = bundle.getString("std");
            address = bundle.getString("Address");
            Phone = bundle.getString("Phone");
            Email = bundle.getString("Email");

            Log.e("test", ""+rollNumber);
            Log.e("test", ""+Name);
            Log.e("test", ""+Std);
            Log.e("test", ""+address);
            Log.e("test", ""+Phone);
            Log.e("test", ""+Email);

        }

        roll.setText(rollNumber);
        name.setText(Name);
        std.setText(Std);
        add.setText(address);
        phone.setText(Phone);
        email.setText(Email);

    }
}