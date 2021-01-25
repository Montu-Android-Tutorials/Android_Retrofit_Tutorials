package com.app.android_retrofit_tutorials.app_base;

import androidx.fragment.app.Fragment;

public class Base_Fragment extends Fragment {


    public void setFragment(int container, Fragment mFragment, boolean addToBackStack){
        if (addToBackStack){
            getFragmentManager().beginTransaction()
                    .replace(container, mFragment, mFragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
        }
        else {
            getFragmentManager().beginTransaction()
                    .replace(container, mFragment, mFragment.getClass().getSimpleName())
                    .commit();
        }
    }


}
