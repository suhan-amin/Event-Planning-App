package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,MainActivityLogin.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}