package com.example.rehabready;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TimerDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TimerDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names
    public static final String TABLE_NAME = "timers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_USER_INPUT = "user_input";



    // SQL query to create the table
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_START_TIME + " INTEGER," +
                    COLUMN_USER_INPUT + " TEXT)";


    public TimerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Timer> getAllTimers() {
        List<Timer> timerList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
                long startTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_START_TIME));
                String userInput = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_INPUT));

                // Create a Timercc object and add it to the list
                Timer timer = new Timer(id, startTime, userInput);
                timerList.add(timer);

            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return timerList;
    }

    public void updateTimer(Timer timer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Update the start time to the current time
        long currentTime = System.currentTimeMillis();
        values.put(COLUMN_START_TIME, currentTime);
        values.put(COLUMN_USER_INPUT, timer.getUserInput());

        // Update the row in the database where the ID matches the timer's ID
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(timer.getId())});

        db.close();

        // Reset the start time of the timer to the current time
        timer.setStartTime(currentTime);
    }



    public void deleteTimer(long timerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(timerId)});
        db.close();
    }






}
