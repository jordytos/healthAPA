package com.example.healthapa;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Context;

import java.util.Locale;

public class DemoActivity  extends FragmentPagerAdapter {

    private final Context mContext;
    FragmentManager fragmentManager;

    public DemoActivity(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position) {
            case 0:
                return HomeActivity.newInstance(fragmentManager);
            case 1:
                return UserInfo.newInstance(fragmentManager);

        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 1 total pages.
        return 2;
    }

}