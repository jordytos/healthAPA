package com.example.healthapa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListAddButton extends Fragment {

    Button addActiviteBtn, addStrucreBtn;
    FragmentManager fm;

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

        return view;
    }
}