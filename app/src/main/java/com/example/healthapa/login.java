package com.example.healthapa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private Button loginBtn;
    private TextView password;
    private TextView username;
    private TextView register;
    private FirebaseAuth mAuth;

    private String user,u,p;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(loginBtnListener);

        //clickable text to regitser
        register = findViewById(R.id.Register);
        String text = "Register, HERE";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickSpan = new ClickableSpan(){

            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.CYAN);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickSpan,9,14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(ss);
        register.setMovementMethod(LinkMovementMethod.getInstance());


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

         u ="";
         p = "";

    }



    private View.OnClickListener loginBtnListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            loginUser();

        }
    };

    private void loginUser(){
        String email = username.getText().toString();
        String pass = password.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email , pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(login.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this , MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                password.setError("Empty Fields Are not Allowed");
            }
        }else if(email.isEmpty()){
            username.setError("Empty Fields Are not Allowed");
        }else{
            username.setError("Pleas Enter Correct Email");
        }
    }
}