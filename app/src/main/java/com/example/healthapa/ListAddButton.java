package com.example.healthapa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.healthapa.dao.UtilisateurDao;
import com.example.healthapa.dao.apaDatabase;
import com.example.healthapa.entities.Utilisateur;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class ListAddButton extends Fragment {

    Button addActiviteBtn, addStrucreBtn, addSeanceBtn;
    FragmentManager fm;

    private FirebaseAuth mAuth;
    private apaDatabase db;
    private UtilisateurDao utilisateurDao;

    public ListAddButton() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DemoFragment.
     */
    public static ListAddButton newInstance(FragmentManager fm) {
        ListAddButton ladb = new ListAddButton();
        ladb.fm = fm;
        return ladb;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_button_list, container, false);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserEmail = currentUser.getEmail();

        addActiviteBtn = view.findViewById(R.id.addActiviteBtn);
        addActiviteBtn.setOnClickListener(v -> {
            CreateActiviteFragment createActiviteFragment = CreateActiviteFragment.newInstance(fm);
            createActiviteFragment.show(getActivity().getSupportFragmentManager(), "createActiviteFragment");
        });

        addStrucreBtn = view.findViewById(R.id.addStructureBtn);
        addStrucreBtn.setOnClickListener(v -> {
            CreateStructureFragment createStructureFragment = CreateStructureFragment.newInstance(fm);
            createStructureFragment.show(getActivity().getSupportFragmentManager(), "createStructureFragment");
        });

        addSeanceBtn = view.findViewById(R.id.addSeanceBtn);

        new Thread(() -> {

            db = apaDatabase.getDatabase(getActivity().getApplicationContext());
            utilisateurDao = db.utilisateurDao();
            List<Utilisateur> user_list = utilisateurDao.findByEmail(currentUserEmail);


            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    Utilisateur new_user = new Utilisateur();

                    for (Utilisateur user: user_list
                    ) {
                        if(user.getRole().equals("Intervenant")){
                            addSeanceBtn.setOnClickListener(v -> {
                                CreateSeanceFragment createSeanceFragment = CreateSeanceFragment.newInstance(fm);
                                createSeanceFragment.show(getActivity().getSupportFragmentManager(), "createSeanceFragment");
                            });
                        }

                        else{
                            Toast.makeText(getActivity().getApplicationContext(), "Vous n'êtes pas autorisé à ajoute une séance !", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });

        }).start();


        return view;
    }
}