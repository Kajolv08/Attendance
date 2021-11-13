package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.attendance.Adapter.UpcomingAdapter;
import com.example.attendance.Constants.URLs;
import com.example.attendance.Constants.Utils;
import com.example.attendance.POJO.UpcomingPOJO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class RollNo extends AppCompatActivity {
    protected Utils utils;
    protected RecyclerView rv_RollNo;
    protected UpcomingAdapter upcomingAdapter;
    protected List<UpcomingPOJO> pojos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_no);

        rv_RollNo = findViewById(R.id.rv_RollNo);
        rv_RollNo.setHasFixedSize(true);
        rv_RollNo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_RollNo.invalidate();

        utils = new Utils();
        utils.pd= new ProgressDialog(this);
        utils.pd.setCancelable(false);
        utils.pd.setMessage("Please Wait....");
        utils.pd.show();

        pojos = new ArrayList<UpcomingPOJO>();


        loadDataInBackground();
    }

    public void loadDataInBackground(){
        new Thread(() -> {
            // do your stuff
            //pd.setProgress(+10);
            int jumpTime = 0;

            while(jumpTime < 100) {
                try {
                    sleep(200);
                    jumpTime += 5;
                    load();
                    //pd.setProgress(jumpTime);
                    /*dataBase = Contacts.getInstance(requireContext());*/
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            runOnUiThread(() -> {
                // do onPostExecute stuff
                //Toast.makeText(getContext(), "Hii PhoneBook", Toast.LENGTH_SHORT).show();
                upcomingAdapter = new UpcomingAdapter(pojos, getApplicationContext());
                rv_RollNo.setAdapter(upcomingAdapter);
                upcomingAdapter.notifyDataSetChanged();
                utils.pd.dismiss();
            });
        }).start();
    }

    private void load() {
       utils.sr = new StringRequest(Request.Method.GET, new URLs().ROLL_NUMBER_URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               Log.i("", response);
               dataLoad(response);
               Toast.makeText(RollNo.this, ""+response, Toast.LENGTH_SHORT).show();
               utils.pd.dismiss();

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.i("", error.toString());
               Toast.makeText(RollNo.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
               utils.pd.dismiss();
           }
       });
        utils.rq = Volley.newRequestQueue(this);
        utils.rq.add(utils.sr);
        utils.sr.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }
    private void dataLoad (String response){
        try {
            // Create the root JSONObject from the JSON string.
            JSONObject  jsonRootObject = new JSONObject(response);
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Students");
            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                UpcomingPOJO upcomingPOJO = new UpcomingPOJO();
                upcomingPOJO.setRoll_no(jsonObject.getString("ROll_no"));
                upcomingPOJO.setName(jsonObject.getString("name"));
                upcomingPOJO.setStd(jsonObject.getString("std"));
                upcomingPOJO.setAddress(jsonObject.getString("Address"));
                upcomingPOJO.setEmail(jsonObject.getString("email"));
                upcomingPOJO.setPhone(jsonObject.getString("phone"));
                pojos.add(upcomingPOJO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}