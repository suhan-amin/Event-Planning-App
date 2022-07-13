package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityBillPage extends AppCompatActivity {
    TextView bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bill_page);
        getSupportActionBar().hide();

        bill=findViewById(R.id.textView20);

        Intent t=getIntent();
        String extra1=t.getStringExtra("load1");
        String extra2=t.getStringExtra("load2");
        String extra3=t.getStringExtra("load3");
        String extra4=t.getStringExtra("load4");
        String extra5=t.getStringExtra("load5");
        String extra6=t.getStringExtra("load6");
        bill.setText("Event Type: "+String.valueOf(extra1)+"\n\n"+"Venue Type: "+String.valueOf(extra2)+"\n\n"+"Food Items: "+"\n"+String.valueOf(extra3)+"\n\n"+"Activities: "+"\n"+String.valueOf(extra4)+"\n\n"+"No of Guests: "+String.valueOf(extra5)+"\n\n"+"Grand Total: "+String.valueOf(extra6));

    }
}