package com.app.android_retrofit_tutorials.app_constants;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Manish Ahire
 */

public class Preference_Manager {

    // Shared preferences file name
    private static final String PREF_NAME = "User_Preference";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_USER_LOGGED = "IS_USER_LOGGED";
    private static final String USER_ID = "userId";

    // shared pref mode
    private int PRIVATE_MODE = 0;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    public Preference_Manager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


    public void setUserLogged(boolean isUserLogged) {
        editor.putBoolean(IS_USER_LOGGED, isUserLogged);
        editor.commit();
    }

    public boolean getIsUserLogged() {
        return pref.getBoolean("IS_USER_LOGGED", false);
    }

    public boolean getUserInfo() {
        return pref.getBoolean("IS_USER_LOGGED", false);
    }

    public void setUserInfo(boolean isUserLogged) {
        editor.putBoolean(IS_USER_LOGGED, isUserLogged);
        editor.commit();
    }

    public void setSelectJobLcation(boolean isUserLogged) {
        editor.putBoolean(IS_USER_LOGGED, isUserLogged);
        editor.commit();
    }

}
