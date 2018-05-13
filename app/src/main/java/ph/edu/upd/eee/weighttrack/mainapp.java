package com.example.raymundrafael.weighttrak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;





public class mainapp extends AppCompatActivity {

    BottomBar mBottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainapp);

        mBottomBar = BottomBar.attach(this,savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(int menuItemId) {
                if(menuItemId == R.id.bottombaritemone){
                    Timelinefragment f = new Timelinefragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.bottombaritemtwo){
                    Devicefragment f = new Devicefragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.bottombaritemthree){
                    Achievementfragment f = new Achievementfragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.bottombaritemfour){
                    Settingsfragment f = new Settingsfragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }

            }

            @Override
            public void onMenuTabReSelected(int menuItemId) {

            }

        });


    }

    public void onSettingsClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   SettingsActivity.class);
        startActivity(myIntent);
    }
}
