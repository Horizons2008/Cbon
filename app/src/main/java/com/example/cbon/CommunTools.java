package com.example.cbon;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class CommunTools {


    //*************************************************************

    boolean CheckSession(Context context)
    {
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

boolean test=sharedPreferences.getBoolean("islogin",false);
       // myEdit.putString("name", name.getText().toString());
       // myEdit.putInt("age", Integer.parseInt(age.getText().toString()));

// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();



        return test;
    }

    //*********************************************************
}
