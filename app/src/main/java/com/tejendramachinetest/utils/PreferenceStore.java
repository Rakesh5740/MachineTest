package com.tejendramachinetest.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceStore {

    private static PreferenceStore store;
    private SharedPreferences sharedPreferences;



    private PreferenceStore(Context context) {
        String filename = "MACHINE_TEST";
        sharedPreferences = context.getApplicationContext().getSharedPreferences(filename,0);
    }

    public static PreferenceStore getInstance(Context context) {
        if (store == null) {
            store = new PreferenceStore(context);
        }
        return store;
    }



    public void logOut(String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();

    }

    public void logIn(){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PreferenceConstants.IS_LOGGEDIN, true);
        editor.apply();
    }

    public void saveString(String key,String value){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public String getStringValue(String key){

      return sharedPreferences.getString(key,null);

    }

    public boolean isLoggedIn(){

        return  sharedPreferences.getBoolean(PreferenceConstants.IS_LOGGEDIN,false);
    }
}
