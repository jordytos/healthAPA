package com.example.healthapa;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserInfo extends DialogFragment {

    FragmentManager fragmentManager;

    public UserInfo() {

    }

    public static UserInfo newInstance(FragmentManager fragmentManager) {
        UserInfo userInfo = new UserInfo();
        userInfo.fragmentManager = fragmentManager;
        return userInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_user_info, container, false);
        return view;
    }
}