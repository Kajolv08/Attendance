package com.example.attendance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddStudent extends AppCompatActivity {
    Button submit;
    EditText name,std,ROll_no,Address,	phone,email;
    RecyclerView Rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        submit=(Button)findViewById(R.id.btn1);
        name=(EditText) findViewById(R.id.name);
        std=(EditText) findViewById(R.id.std);
        ROll_no=(EditText) findViewById(R.id.roll);
        Address=(EditText) findViewById(R.id.address);
        phone=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email);
        Rv=(RecyclerView) findViewById(R.id.rvlist);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voll();


            }
        });

        Intent i = new Intent(getApplicationContext(), Profile.class);
        startActivity(i);





    }
    private void voll() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.43.51/Attendance/Insert.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(AddStudent.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudent.this, "Error"+error, Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param=new HashMap<>();
                param.put("ROll_no",ROll_no.getText().toString());
                param.put("name",name.getText().toString());
                param.put("std",std.getText().toString());
                param.put("Address",Address.getText().toString());
                param.put("phone",	phone.getText().toString());
                param.put("email",email.getText().toString());
                return param;
            }
        };
        RequestQueue r= Volley.newRequestQueue(this);
        r.add(stringRequest);
    }

    /*public void submit(View view)
    {
        String url="http://192.168.0.104/Attendance/Connection.php";
        RequestQueue requestQueue= Volley`.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Roll_no", Roll_no.getText().toString());
                map.put("Name", Name.getText().toString());
                return map;
            }
        };




    }*/


}