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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private Button loginBtn;
    private TextView password;
    private TextView username;
    private TextView register;

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

         u ="user";
         p = "user";

    }



    private View.OnClickListener loginBtnListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            user = username.getText().toString();
            pass = password.getText().toString();

            Intent intent = new Intent(login.this, MainActivity.class);

            if(u.equals(user) && p.equals(pass)){
                startActivity(intent);
            }
            else{
                Toast.makeText(login.this, "Error Login or Password", Toast.LENGTH_LONG).show();
            }

        }
    };
}