package ph.edu.upd.eee.weighttrack;

import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    DatabaseHelper myDb;
    private FirebaseDatabase fireDb;
    private EditText inputDatetime, inputWeight;
    private Button btnWeigh, btnGetData;
    private Switch switchAutoSync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        fireDb   = FirebaseDatabase.getInstance();
        inputDatetime = findViewById(R.id.input_datetime);
        inputWeight   = findViewById(R.id.input_weight);

        btnWeigh      = findViewById(R.id.btn_weigh);
        btnGetData    = findViewById(R.id.btn_getdata);
        switchAutoSync= findViewById(R.id.switch_autosync);

        btnWeigh.setOnClickListener(this);
        btnGetData.setOnClickListener(this);
        switchAutoSync.setOnCheckedChangeListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void addData(String weight, String datetime ) {
        boolean isInserted = myDb.insertData( weight, datetime );
        if(isInserted)
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
    }

    public void getData() {
        Cursor res = myDb.getAllData();
        if(res.getCount()==0){
            showMessage("Error","Nothing found");
            return;
        }
        StringBuilder buffer = new StringBuilder();
        while(res.moveToNext()){
            buffer.append(res.getString(0)).append("\n").append(res.getString(1)).append("\n\n");
        }
        showMessage("Data", buffer.toString() );
    }

    public void autoSync( boolean isChecked ) {
        if (isChecked) {
            Toast.makeText(getApplicationContext(), "Syncing", Toast.LENGTH_LONG).show();
            DatabaseReference datetimeRef, weightRef;
            Cursor res = myDb.getAllData();
            if(res.getCount()==0){
                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
                return;
            }
            StringBuilder buffer = new StringBuilder();
            while(res.moveToNext()){
                datetimeRef = fireDb.getReference("datetime");
                datetimeRef.setValue( res.getString(0) );

                weightRef = fireDb.getReference("weight");
                weightRef.setValue( res.getString(1) );
            }
            Toast.makeText(getApplicationContext(), "Finished Syncing", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Syncing Off", Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_weigh) {
            addData( inputWeight.getText().toString(), inputDatetime.getText().toString());
        } else if (i == R.id.btn_getdata) {
            getData();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int i = buttonView.getId();
        if(i == R.id.switch_autosync) {
            autoSync(isChecked);
        }
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
