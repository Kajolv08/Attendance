package com.example.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper/* extends SQLiteOpenHelper*/ {
    /*public static final String Database_Name="Attendance";
    public static final String TableName="Add_Student";
    //public static final String Col_1="Profile";
    public static final String Col_2="Roll_no";
    public static final String Col_3="Name";
    public static final String Col_4="STD";
    public static final String Col_5="Address";
    public static final String Col_6="Phone";
    public static final String Col_7="Email";*/

    /*SQLiteOpenHelper sql;


    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable=" CREATE TABLE " +TableName+ "(" +Col_2+ "INTEGER PRIMARY KEY AUTOINCREMENT," +Col_3+ "TEXT," +Col_4+ "INT," +Col_5+ "TEXT," +Col_6+ "INT," +Col_7+ "TEXT)" ;
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }
    public boolean addOne(StudentModel studentModel) {
        SQLiteDatabase sqLiteDatabase    = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_2, studentModel.getRollno());
        cv.put(Col_3, studentModel.getName());
        cv.put(Col_4, studentModel.getStd());
        cv.put(Col_5, studentModel.getAddress());
        cv.put(Col_6, studentModel.getPhone());
        cv.put(Col_7, studentModel.getEmail());
        long insert = sqLiteDatabase.insert(TableName, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }


    }*/


}
