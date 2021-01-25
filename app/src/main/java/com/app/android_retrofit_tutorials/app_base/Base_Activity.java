package com.app.android_retrofit_tutorials.app_base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Base_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //Todo Set Fragment
    public void setFragment(int conatiner, Fragment mFragment, boolean addToBackStack) {
        if (addToBackStack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(conatiner, mFragment, mFragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(conatiner, mFragment, mFragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
