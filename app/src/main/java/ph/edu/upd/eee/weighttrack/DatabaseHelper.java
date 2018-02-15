package ph.edu.upd.eee.weighttrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "weight_track.db";
    private static final String TABLE_NAME = "weight_entries";
    private static final String COL_1 = "TIMESTAMP";
    private static final String COL_2 = "WEIGHT_ENTRY";

    DatabaseHelper(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+COL_1+" DATETIME PRIMARY KEY NOT NULL, "+COL_2+" INTEGER NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    boolean insertData(String timestamp, String weight_entry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues input = new ContentValues();
        input.put(COL_1, timestamp);
        input.put(COL_2, weight_entry);
        if( timestamp.isEmpty() || weight_entry.isEmpty() )
            return false;
        else {
            long isInserted = db.insert(TABLE_NAME, null, input);
            return isInserted != -1;
        }
    }

    Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

}
