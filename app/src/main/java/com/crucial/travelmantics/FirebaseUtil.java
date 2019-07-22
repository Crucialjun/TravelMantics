package com.crucial.travelmantics;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil sFirebaseUtil;
    public static ArrayList<TravelDeals> mDeals;


    private FirebaseUtil(){

    }

    public static void openFbReference(String ref){
        if(sFirebaseUtil == null){
            sFirebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDeals = new ArrayList<TravelDeals>();
        }

        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }

}
