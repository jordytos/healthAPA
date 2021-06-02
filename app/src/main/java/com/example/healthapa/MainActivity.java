package com.example.healthapa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Utilisateur;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DemoActivity demoActivity;
    private ViewPager viewPager;
    FragmentManager fragmentManager;

    private FirebaseAuth mAuth;

    private apaDatabase db;
    private UtilisateurDao utilisateurDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserEmail = currentUser.getEmail();

        if (getIntent().getBooleanExtra("LOGOUT", false))
        {
            finish();
        }

        //Toast.makeText(MainActivity.this, "Connection to data base succeed", Toast.LENGTH_SHORT).show();

        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListerner);


        new Thread(() -> {

            db = apaDatabase.getDatabase(this);
            utilisateurDao = db.utilisateurDao();
            List<Utilisateur> user_list = utilisateurDao.findByEmail(currentUserEmail);


            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    Utilisateur new_user = new Utilisateur();

                    for (Utilisateur user: user_list
                    ) {
                        if(user.getRole().equals("Médecin")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeActivityMedecin()).commit();
                        }
                        else if(user.getRole().equals("Patient")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeActivityPatient()).commit();
                        }
                        else if(user.getRole().equals("Intervenant")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeActivityIntervenant()).commit();
                        }
                        else{
                            Log.d("Error", "Role non reconnu");
                        }

                    }
                }
            });

        }).start();


        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeActivityPatient()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    String currentUserEmail = currentUser.getEmail();

                    new Thread(() -> {

                        db = apaDatabase.getDatabase(getApplicationContext());
                        utilisateurDao = db.utilisateurDao();
                        List<Utilisateur> user_list = utilisateurDao.findByEmail(currentUserEmail);

                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                Fragment selectedFragment = null;

                                switch (item.getItemId()){
                                    case R.id.menuHome:
                                        for (Utilisateur user: user_list)
                                        {
                                            if(user.getRole().equals("Médecin")){
                                                selectedFragment = new HomeActivityMedecin();
                                                Log.d("Jordy", "MEDECIN OK");
                                            }
                                            if(user.getRole().equals("Patient")){
                                                selectedFragment = new HomeActivityPatient();
                                            }
                                            if(user.getRole().equals("Intervenant")){
                                                Log.d("Jordy ", "INTERVENANT OK");
                                                selectedFragment = new HomeActivityIntervenant();
                                            }
                                        }

                                        break;
                                    case R.id.menuCompte:
                                        selectedFragment = new UserInfo();
                                        break;

                                    case R.id.menuLogout:
                                        selectedFragment = new logout();
                                        break;
                                }
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                            }
                        });

                    }).start();

                    return true;
                }
            };

}