package com.example.proapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceStorage {

    Context context;
    SharedPreferences myFile;
    public static final String PREF_NAME = "com.example.proapp.SHARED_PREF";
    public static final String USER_NAME = "com.example.proapp.USER_NAME";
    public static final String USER_IMAGE = "com.example.proapp.USER_IMAGE";
    public static final String USER_PASSWORD = "com.example.proapp.USER_USER_PASSWORD";
    public static final String USER_EMAIL = "com.example.proapp.USER_EMAIL";
    public static final String USER_NUMBER = "com.example.proapp.USER_NUMBER";
    public static final String IS_LOGGED_IN = "com.example.proapp.IS_LOGGED_IN";

    public PreferenceStorage(Context context) {
        this.context = context;
        this.myFile = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getUserName(){
        return myFile.getString(USER_NAME, "");
    }


    public void saveUserData(String name, String email, String number, String password) {
        SharedPreferences.Editor myEditor = myFile.edit();
        myEditor.putString(USER_NAME, name);
        myEditor.putString(USER_EMAIL, email);
        myEditor.putString(USER_NUMBER, number);
        myEditor.putString(USER_PASSWORD, password);
        myEditor.apply();

    }

    public boolean isLoggedIn(){
        return myFile.getBoolean(IS_LOGGED_IN, false);
    }

    public void setLoggedInStatus(boolean status){

        SharedPreferences.Editor myEditor = myFile.edit();
        myEditor.putBoolean(IS_LOGGED_IN, status);
        myEditor.apply();
    }

    public boolean authenticate(String username, String password) {
        String currentName = getUserName();
        String currentPassword = myFile.getString(USER_PASSWORD, "");

        if(currentName.contentEquals(username) && currentPassword.contentEquals(password)){
            return true;
        }
        else{
            return false;
        }
    }
}
