package com.crucial.travelmantics;

import android.app.Activity;
import androidx.annotation.NonNull;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil sFirebaseUtil;
    private static FirebaseAuth mFirebaseAuth;
    private static FirebaseAuth.AuthStateListener mAuthListener;
    public static ArrayList<TravelDeal> mDeals;
    private static Activity caller;
    private static final int RC_SIGN_IN = 123;


    private FirebaseUtil(){

    }

    public static void openFbReference(String ref, final Activity callerActivity){
        if(sFirebaseUtil == null){
            sFirebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseAuth = mFirebaseAuth.getInstance();
            caller = callerActivity;
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    // Choose authentication providers
                    if(firebaseAuth.getCurrentUser() == null){
                        signIn();
                    }else{
                        Toast.makeText(callerActivity, "Welcome Back", Toast.LENGTH_LONG).show();

                    }

                }
            };
        }

        mDeals = new ArrayList<TravelDeal>();

        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }

    public static void attachListener(){
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    public static void detachListener(){
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }

    public static void signIn(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());


        // Create and launch sign-in intent
        caller.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

}
