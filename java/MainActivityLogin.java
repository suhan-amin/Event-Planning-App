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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityLogin extends AppCompatActivity {
    EditText email,password;
    MaterialButton loginbutton;

    TextView signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        getSupportActionBar().hide();

        email=findViewById(R.id.uname);
        password=findViewById(R.id.pass);
        loginbutton=findViewById(R.id.loginbtn);
        signup=findViewById(R.id.tv1);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityLogin.this,MainActivitySignup.class));
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* startActivity(new Intent(MainActivityLogin.this,MainActivityOpeningPage.class));*/
                loginUser();


            }
        });


    }
    private void loginUser(){
        String memail = email.getText().toString();
        String mpass = password.getText().toString();

        if (!memail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(memail).matches()){
            if (!mpass.isEmpty()){
                mAuth.signInWithEmailAndPassword(memail ,mpass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivityLogin.this, "Login Successful !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivityLogin.this , MainActivityOpeningPage.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivityLogin.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                password.setError("Empty Fields Are not Allowed");
            }
        }else if(memail.isEmpty()){
            email.setError("Empty Fields Are not Allowed");
        }else{
            email.setError("Pleas Enter Correct Email");
        }
    }
}