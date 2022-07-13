package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivitySignup extends AppCompatActivity {
    TextView reg;
    EditText pass,email;
    Button register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_signup);
        getSupportActionBar().hide();
        reg=findViewById(R.id.textView3);
        email=findViewById(R.id.mail);
        pass=findViewById(R.id.pword);
        register=findViewById(R.id.signupbtn);
        mAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*startActivity(new Intent(MainActivitySignup.this,MainActivityLogin.class));*/
                createUser();
            }
        });
    }
    private void createUser(){
        String mmail = email.getText().toString();
        String mpass = pass.getText().toString();

        if (!mmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mmail).matches()){
            if (!mpass.isEmpty()){
                mAuth.createUserWithEmailAndPassword(mmail, mpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(MainActivitySignup.this, "Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivitySignup.this , MainActivityLogin.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivitySignup.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                pass.setError("Empty Fields Are not Allowed");
            }
        }else if(mmail.isEmpty()){
            email.setError("Empty Fields Are not Allowed");
        }else{
            email.setError("Pleas Enter Correct Email");
        }
    }
}