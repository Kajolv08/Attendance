package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Attendance extends AppCompatActivity {
    private MySQlDatabseHelper Mysqldatabasehelper;
    private SQLiteDatabase sqLiteDatabase;
    CalendarView calender;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button button;
    String CurrentDate;
   // int  i=Integer.parseInt(CurrentDate);
    String selectedDate = "";

    TextView textView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        textView=findViewById(R.id.textview);
        calender=(CalendarView)findViewById(R.id.calendarView);
        button=(Button)findViewById(R.id.button);


        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Toast.makeText(getApplicationContext(), "Date is:"+dayOfMonth, 0).show();
                //i=dayOfMonth;// TODO Auto-generated method stub

                selectedDate = Integer.toString(year)+Integer.toString(month)+Integer.toString(dayOfMonth);
                ReadDataBase(view);

            }

        });
        try
        {
            Mysqldatabasehelper=new MySQlDatabseHelper(this,"CalenderDataBase",null,1);
            sqLiteDatabase=Mysqldatabasehelper.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE EventCalender(Date TEXT,Event TEXT)");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        textView=(TextView)findViewById(R.id.textview);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voll();

                int radioId = radioGroup.getCheckedRadioButtonId();

                if(radioId == radioGroup.getCheckedRadioButtonId()) {
                    radioButton = findViewById(radioId);
                    textView.setText("" + radioButton.getText());
                }
                else
                {
                    Toast.makeText(Attendance.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

        });


    }
    private  void  InsertDatabase(View view)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("Date",selectedDate);
        contentValues.put("Event ",textView.getText().toString());
        sqLiteDatabase.insert("add_student",null,contentValues);

    }
    public  void ReadDataBase(View view)
    {
        String query = "insert into  add_student (add_date)"+selectedDate;
        try
        {
            Cursor cursor=sqLiteDatabase.rawQuery(query,null);
            textView.setText(cursor.getString(0));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            textView.setText("");
        }
    }

        private void voll() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://192.168.43.51/Attendance/Insert.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(Attendance.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Attendance.this, "Error"+error, Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param=new HashMap<>();
                param.put("ROll_no",textView.getText().toString());
                return param;
            }
        };
        RequestQueue r= Volley.newRequestQueue(this);
        r.add(stringRequest);
    }
    public void checkButton(View v){
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this,"Selected Choice"+radioButton.getText(),Toast.LENGTH_SHORT).show();
    }


}