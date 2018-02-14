package ph.edu.upd.eee.weighttrack;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editDatetime, editWeight;
    Button btnWeigh, btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editDatetime = findViewById(R.id.editText_datetime);
        editWeight   = findViewById(R.id.editText_weight);
        btnWeigh     = findViewById(R.id.btn_weigh);
        btnGetData   = findViewById(R.id.btn_getdata);
        addData();
        getData();
    }

    public void addData() {
        btnWeigh.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    boolean isInserted = myDb.insertData( editDatetime.getText().toString(), editWeight.getText().toString() );
                if(isInserted)
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                }
            }
        );
    }

    public void getData() {
        btnGetData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuilder buffer = new StringBuilder();
                        while(res.moveToNext()){
                            buffer.append(res.getString(0)).append("\n").append(res.getString(1)).append("\n\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
