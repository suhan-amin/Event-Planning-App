package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookEvent extends AppCompatActivity {
    Spinner spinner,spinner1,spinner2;
    String[] eventtype={"Wedding","Engagement","Birthday Party","Baby Shower"};
    String[] venuetype={"Indoor","Outdoor"};
    String[] noofguests={"50-99","100-249","250-499","500+"};
    DatePickerDialog datePickerDialog;
    Button dateButton;
    android.widget.CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9;
    android.widget.CheckBox c10,c11,c12,c13;
    Button bookBtn;
    /*private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Users");*/
    DatabaseReference studentDBref;
    TextView grandt,output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_event);
        getSupportActionBar().hide();

        c1=findViewById(R.id.CB1);
        c2=findViewById(R.id.CB2);
        c3=findViewById(R.id.CB3);
        c4=findViewById(R.id.CB4);
        c5=findViewById(R.id.CB5);
        c6=findViewById(R.id.CB6);
        c7=findViewById(R.id.CB7);
        c8=findViewById(R.id.CB8);
        c9=findViewById(R.id.CB9);

        c10=findViewById(R.id.CB10);
        c11=findViewById(R.id.CB11);
        c12=findViewById(R.id.CB12);
        c13=findViewById(R.id.CB13);

        studentDBref=FirebaseDatabase.getInstance().getReference().child("Students");

        bookBtn=findViewById(R.id.bookbtn);

        initDatePicker();
        dateButton=findViewById(R.id.datebutton);
        dateButton.setText(getTodaysDate());

        spinner=findViewById(R.id.spinner);
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        grandt=findViewById(R.id.textView111);
        output=findViewById(R.id.textView121);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(BookEvent.this, android.R.layout.simple_spinner_item,eventtype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value=adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(BookEvent.this, android.R.layout.simple_spinner_item,venuetype);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value1=adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(BookEvent.this, android.R.layout.simple_spinner_item,noofguests);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value2=adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });
        grandt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total=0;
                int food=0,acti=0;
                StringBuilder s=new StringBuilder();
                if(c1.isChecked()){
                    food+=30;
                }
                if(c2.isChecked()){
                    food+=60;
                }
                if(c3.isChecked()){
                    food+=60;
                }
                if(c4.isChecked()){
                    food+=60;
                }
                if(c5.isChecked()){
                    food+=100;
                }
                if(c6.isChecked()){
                    food+=40;
                }
                if(c7.isChecked()){
                    food+=40;
                }
                if(c8.isChecked()){
                    food+=90;
                }
                if(c9.isChecked()){
                    food+=30;
                }
                if(c10.isChecked()){
                    acti+=3000;
                }
                if(c11.isChecked()){
                    acti+=5000;
                }
                if(c12.isChecked()){
                    acti+=5000;
                }
                if(c13.isChecked()){
                    acti+=4000;
                }
                if(spinner2.getSelectedItem().equals("50-99")) {
                    total=(food*100)+acti;
                    output.setText(" Rs "+String.valueOf(total));
                }
                else if(spinner2.getSelectedItem().equals("100-249")) {
                    total=(food*250)+acti;
                    output.setText(" Rs "+String.valueOf(total));
                }
                else if(spinner2.getSelectedItem().equals("250-499")) {
                    total=(food*500)+acti;
                    output.setText(" Rs "+String.valueOf(total));
                }
                else if(spinner2.getSelectedItem().equals("500+")) {
                    total=(food*700)+acti;
                    output.setText(" Rs "+String.valueOf(total));
                }
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog=new AlertDialog.Builder(BookEvent.this);
                alertDialog.setTitle("Are you sure you want to book?");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent t=new Intent(getApplicationContext(),MainActivityBillPage.class);
                        String cbox=" ",cbox1=" ";
                        t.putExtra("load1",spinner.getSelectedItem().toString());
                        t.putExtra("load2",spinner1.getSelectedItem().toString());
                        if(c1.isChecked())
                        {
                            cbox+=c1.getText().toString()+"\t"+": Rs 30"+"\n";
                        }
                        if(c2.isChecked())
                        {
                            cbox+=c2.getText().toString()+"\t"+": Rs 60"+"\n";
                        }
                        if(c3.isChecked())
                        {
                            cbox+=c3.getText().toString()+"\t"+": Rs 60"+"\n";
                        }
                        if(c4.isChecked())
                        {
                            cbox+=c4.getText().toString()+"\t"+": Rs 60"+"\n";
                        }
                        if(c5.isChecked())
                        {
                            cbox+=c5.getText().toString()+"\t"+": Rs 100"+"\n";
                        }
                        if(c6.isChecked())
                        {
                            cbox+=c6.getText().toString()+"\t"+": Rs 40"+"\n";
                        }
                        if(c7.isChecked())
                        {
                            cbox+=c7.getText().toString()+"\t"+": Rs 40"+"\n";
                        }
                        if(c8.isChecked())
                        {
                            cbox+=c8.getText().toString()+"\t"+": Rs 90"+"\n";
                        }
                        if(c9.isChecked())
                        {
                            cbox+=c9.getText().toString()+"\t"+": Rs 30";

                        }
                        t.putExtra("load3",cbox.toString());

                        if(c10.isChecked())
                        {
                            cbox1+=c10.getText().toString()+"\t"+": Rs 3000"+"\n";
                        }
                        if(c11.isChecked())
                        {
                            cbox1+=c11.getText().toString()+"\t"+": Rs 5000"+"\n";
                        }
                        if(c12.isChecked())
                        {
                            cbox1+=c12.getText().toString()+"\t"+": Rs 5000"+"\n";
                        }
                        if(c13.isChecked())
                        {
                            cbox1+=c13.getText().toString()+"\t"+": Rs 4000";
                        }
                        t.putExtra("load4",cbox1.toString());

                        t.putExtra("load5",spinner2.getSelectedItem().toString());

                        t.putExtra("load6",output.getText().toString());

                        startActivity(t);

                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog1=alertDialog.create();
                alertDialog1.show();
              /*  Toast.makeText(BookEvent.this, s.toString(), Toast.LENGTH_SHORT).show();
                String service=spinner.toString();
                String venue=spinner1.toString();
                String dte=datePickerDialog.toString();
                String nof=spinner2.toString();
                root.child("01").setValue(service);
                root.child("01").setValue(venue);
                root.child("01").setValue(dte);
                root.child("01").setValue(nof);
                insertStudentData();*/

            }
        });

    }
   /* private void insertStudentData()
    {
        String service=spinner.toString();
        String venue=spinner1.toString();
       /* String dte=datePickerDialog.toString();
        String nof=spinner2.getSelectedItem().toString();

        Students students=new Students(service,venue,nof);
        studentDBref.setValue(students);
        Toast.makeText(BookEvent.this, "DATA INSERTED!!!", Toast.LENGTH_SHORT).show();

    }*/

    private String getTodaysDate() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date=makeDateString(day,month,year);
                dateButton.setText(date);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month)+" "+day+" "+year;
    }

    private String getMonthFormat(int month) {
        if(month==1)
            return "JAN";
        if(month==2)
            return "FEB";
        if(month==3)
            return "MAR";
        if(month==4)
            return "APR";
        if(month==5)
            return "MAY";
        if(month==6)
            return "JUN";
        if(month==7)
            return "JUL";
        if(month==8)
            return "AUG";
        if(month==9)
            return "SEP";
        if(month==10)
            return "OCT";
        if(month==11)
            return "NOV";
        if(month==12)
            return "DEC";
        return "JAN";
    }


    public void openDatePicker(View view) {
        datePickerDialog.show();
    }


}