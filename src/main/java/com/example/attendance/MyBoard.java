package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyBoard extends AppCompatActivity {
    private CardView CV1, CV2, CV3, CV4, CV5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_board);
        CV1 = (CardView) findViewById(R.id.CV1);
        CV2 = (CardView) findViewById(R.id.CV2);
        CV3 = (CardView) findViewById(R.id.CV3);
        CV4 = (CardView) findViewById(R.id.CV4);
        CV5 = (CardView) findViewById(R.id.CV5);

        CV1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                // Creating explicit intent
                Intent i = new Intent(getApplicationContext(),
                        Attendance.class);
                startActivity(i);
            }
        });
        CV2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                // Creating explicit intent
                Intent j = new Intent(getApplicationContext(),
                        RollNo.class);
                startActivity(j);
            }
        });

        CV3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                // Creating explicit intent
                Intent i = new Intent(getApplicationContext(),
                        AddStudent.class);
                startActivity(i);
            }
        });

        CV4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                // Creating explicit intent
                Intent i = new Intent(getApplicationContext(),
                        Information.class);
                startActivity(i);
            }
        });

      CV5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                // Creating explicit intent
                Intent i = new Intent(getApplicationContext(),
                        Signup.class);
                startActivity(i);
            }
        });
    }
}
