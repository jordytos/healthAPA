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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapa.entities.Utilisateur;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {

    EditText firstNameTxt, lastNameTxt, emailTxt, passwordTxt, ageTxt, phoneTxt;
    RadioButton interv, medecin, patient;
    Button registerButton;
    TextView connexion;
    DatabaseReference reff;
    //long maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameTxt = findViewById(R.id.firstnameReg);
        lastNameTxt = findViewById(R.id.lastnameReg);
        emailTxt = findViewById(R.id.emailReg);
        passwordTxt = findViewById(R.id.passwordReg);
        ageTxt = findViewById(R.id.ageReg);
        phoneTxt = findViewById(R.id.phoneReg);

        interv = findViewById(R.id.intervenantBtn);
        medecin = findViewById(R.id.medecinBtn);
        patient = findViewById(R.id.patientBtn);
        
        registerButton = findViewById(R.id.registerBtn);
        Utilisateur utilisateur = new Utilisateur();
        reff = FirebaseDatabase.getInstance().getReference().child("Utilisateur");
       /* reff.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        
        registerButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               int age = Integer.parseInt(ageTxt.getText().toString());
               String prenom = firstNameTxt.getText().toString();
               String nom = lastNameTxt.getText().toString();
               String email = emailTxt.getText().toString();
               String phone = phoneTxt.getText().toString();
               String mdp = passwordTxt.getText().toString();

               utilisateur.setPrenom_user(prenom);
               utilisateur.setNom_user(nom);
               utilisateur.setEmail(email);
               utilisateur.setPassword(mdp);
               utilisateur.setAge(age);
               utilisateur.setTelephone(phone);
               reff.push().setValue(utilisateur);
               //reff.child(String.valueOf(maxid+1)).setValue("Utilisateur");

               Toast.makeText(register.this, "Votre compte a été créé", Toast.LENGTH_SHORT).show();

               Log.d("succes","Prenom "+firstNameTxt);
               Log.d("succes","Nom "+lastNameTxt);
               Log.d("succes","Email "+emailTxt);
               Log.d("succes","mdp "+passwordTxt);
               Log.d("succes","Age "+age);
               Log.d("succes","Phone "+phoneTxt);

           }
        });

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