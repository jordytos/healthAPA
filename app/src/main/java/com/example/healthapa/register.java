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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapa.entities.Utilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText firstNameTxt, lastNameTxt, emailTxt, passwordTxt, ageTxt, phoneTxt,poidsTxt, tailleTxt;
    RadioGroup radioGroup;
    RadioButton radioBtn;
    Button registerButton;
    TextView connexion;
    DatabaseReference reff;
    private FirebaseAuth mAuth;


    //long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        firstNameTxt = findViewById(R.id.firstnameReg);
        lastNameTxt = findViewById(R.id.lastnameReg);
        emailTxt = findViewById(R.id.emailReg);
        passwordTxt = findViewById(R.id.passwordReg);
        ageTxt = findViewById(R.id.ageReg);
        phoneTxt = findViewById(R.id.phoneReg);
        poidsTxt = findViewById(R.id.poidsReg);
        tailleTxt = findViewById(R.id.tailleReg);

        radioGroup = findViewById(R.id.radioBtn);

        registerButton = findViewById(R.id.registerBtn);



        Utilisateur utilisateur = new Utilisateur();
        reff = FirebaseDatabase.getInstance().getReference().child("Utilisateur");
        /*reff.addValueEventListener(new ValueEventListener(){

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

        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        
        registerButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               int age = Integer.parseInt(ageTxt.getText().toString().trim());
               int poids = Integer.parseInt(poidsTxt.getText().toString().trim());
               int taille = Integer.parseInt(tailleTxt.getText().toString().trim());
               String prenom = firstNameTxt.getText().toString().trim();
               String nom = lastNameTxt.getText().toString().trim();
               String email = emailTxt.getText().toString().trim();
               String phone = phoneTxt.getText().toString().trim();
               String mdp = passwordTxt.getText().toString().trim();

               int selectedId = radioGroup.getCheckedRadioButtonId();
               radioBtn = (RadioButton) findViewById(selectedId);
               String role = radioBtn.getText().toString().trim();

               utilisateur.setPrenom_user(prenom);
               utilisateur.setNom_user(nom);
               utilisateur.setEmail(email);
               utilisateur.setPassword(mdp);
               utilisateur.setAge(age);
               utilisateur.setTelephone(phone);
               utilisateur.setRole(role);
               utilisateur.setTaille(taille);
               utilisateur.setPoids(poids);

               createUser();

               reff.push().setValue(utilisateur);

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

    private void createUser(){
        String email = emailTxt.getText().toString();
        String pass = passwordTxt.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Toast.makeText(register.this, "Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(register.this , login.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                passwordTxt.setError("Empty Fields Are not Allowed");
            }
        }else if(email.isEmpty()){
            emailTxt.setError("Empty Fields Are not Allowed");
        }else{
            emailTxt.setError("Pleas Enter Correct Email");
        }
    }

}