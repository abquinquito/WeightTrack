package com.example.raymundrafael.weighttrak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onRegisterClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   register1.class);
        startActivity(myIntent);
    }
}
