package com.example.cbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    CommunTools communTools=new CommunTools();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (communTools.CheckSession(this)==true) {

            Intent myIntent = new Intent(MainActivity.this, Portail.class);
            // myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        }

        else {
            Intent myIntent = new Intent(MainActivity.this, Registration.class);
            // myIntent.putExtra("key", value); //Optional parameters
          //  MainActivity.this.startActivity(myIntent);



        }



    }
}