package org.firstinspires.ftc.robotcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ftcpi on 10/31/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "pid.db";
    public static final String TABLE_NAME = "Test 1";
    public static int TEST_NUMB = 1;
    public static final String ID = "ID";
    public static final String DATETIME = "DATETIME";
    public static final String ELAPSEDTIME = "ELAPSEDTIME";
    public static final String DEGREES = "DEGREES";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+TEST_NUMB+"("+ID+"INTEGER PRIMARY KEY AUTOINCREMENT, "+DATETIME+" DATETIME,"+ELAPSEDTIME+" TEXT,"+DEGREES+" decimal(10,2))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TEST_NUMB++;
        onCreate(db);
    }
    public boolean insertData(double degrees, String elapsedTime) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put(DEGREES, degrees);
        contentValues.put(ELAPSEDTIME, elapsedTime);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
