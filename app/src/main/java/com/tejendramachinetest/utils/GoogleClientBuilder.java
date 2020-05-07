package com.tejendramachinetest.utils;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/*Singleton Class to avoid multiple object creations*/
public class GoogleClientBuilder {

    private static GoogleClientBuilder googleClientBuilder;

    public static GoogleClientBuilder getInstance(){
        if(googleClientBuilder == null)
            googleClientBuilder = new GoogleClientBuilder();

        return googleClientBuilder;
    }

    public GoogleSignInClient googleSignInClient (Context context){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        return GoogleSignIn.getClient(context, gso);

    }
}
