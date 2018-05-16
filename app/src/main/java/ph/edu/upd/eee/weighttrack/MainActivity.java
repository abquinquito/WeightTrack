package ph.edu.upd.eee.weighttrack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.series.DataPoint;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ph.edu.upd.eee.weighttrack.Achievementfragment;
import ph.edu.upd.eee.weighttrack.Devicefragment;
import ph.edu.upd.eee.weighttrack.SettingsActivity;
import ph.edu.upd.eee.weighttrack.Settingsfragment;
import ph.edu.upd.eee.weighttrack.Timelinefragment;

public class MainActivity extends AppCompatActivity {

    BottomBar mBottomBar;
    private FirebaseAuth fireAuth;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fireAuth = FirebaseAuth.getInstance();
        myDb = new DatabaseHelper(this);

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

    public void onMeasureClick(View v){
        //weight test entry is 67
        boolean isInserted = myDb.insertData( Calendar.getInstance().getTime().toString(), "67" );
        Log.d("MainActivity","onMeasureClick:"+isInserted);
        if(isInserted)
                Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

    }

    public void onSettingsClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   SettingsActivity.class);
        startActivity(myIntent);
    }

    public void onSignoutClick(View v) {
        Log.d("MainActivity","onSignoutClick");
        fireAuth.signOut();
        Log.d("MainActivity","onSignoutClick:successful");
        Toast.makeText(MainActivity.this, "Signed out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}
