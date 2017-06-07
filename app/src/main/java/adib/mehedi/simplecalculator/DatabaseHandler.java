package adib.mehedi.simplecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "calculatorhistory1.db";
    public static final String TABLE_CALC = "calchistory";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_HISTORY = "history";

    private Context myContext;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CALC + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_HISTORY + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP_TABLE_IF_EXISTS " + TABLE_CALC);
        onCreate(db);
    }

    public void addHistory(CalcHistory history){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HISTORY, history.get_history());

        long newRowId = db.insert(TABLE_CALC, null, values);
    }

    public void deleteHistory(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("TRUNCATE TABLE " + TABLE_CALC);
    }

    public String displayHistory(){
        SQLiteDatabase db = this.getReadableDatabase();

        String finalResult = "";

        String[] projection = { this.COLUMN_ID, this.COLUMN_HISTORY };

        Cursor cursor = db.query(this.TABLE_CALC, projection, null, null, null, null, null);

        while(cursor.moveToNext()) {
            String id = cursor.getString(0);
            String history = cursor.getString(1);
            finalResult += id + "  |  " + history + "\n";
        }

        cursor.close();
        return finalResult;
    }

}