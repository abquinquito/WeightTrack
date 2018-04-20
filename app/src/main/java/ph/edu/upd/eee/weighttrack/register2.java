package com.example.raymundrafael.weighttrak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class register2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
    }

    public void onRegisterClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   register3.class);
        startActivity(myIntent);
    }
}

