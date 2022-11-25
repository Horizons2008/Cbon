package com.example.cbon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class Registration extends AppCompatActivity {
    Switch Switch_Type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initIHM();
        Switch_Type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true) {Switch_Type.setText("Proffessionnelle  ");Switch_Type.setTextColor(getResources().getColor(R.color.PrimaryColor));}
               else if (b==false) {Switch_Type.setText("Particulier  ");Switch_Type.setTextColor(Color.rgb(70,70,70));}

            }
        });




    }// on create
    void initIHM(){
        Switch_Type=findViewById(R.id.Switch_type);





    }
}