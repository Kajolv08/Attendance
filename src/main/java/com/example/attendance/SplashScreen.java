package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView text;
    ImageView img;
    Animation topAnim, bottomAnime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        text=(TextView)findViewById(R.id.textSplash);
        topAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_anime);
        bottomAnime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_anime);
        img = (ImageView) findViewById(R.id.imgv);


        img.startAnimation(topAnim);
        text.startAnimation(bottomAnime);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,Signup.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, 3000);

    }
}