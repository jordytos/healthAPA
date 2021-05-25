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
import android.widget.TextView;

public class register extends AppCompatActivity {

    TextView connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //clickable text to regitser
        connexion = findViewById(R.id.Connexion);
        String text = "Déjà inscrit ? Connectez-vous";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickSpan = new ClickableSpan() {

            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.CYAN);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickSpan, 14, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        connexion.setText(ss);
        connexion.setMovementMethod(LinkMovementMethod.getInstance());
    }
}