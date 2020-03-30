package com.example.dbelenskiregisterandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "REGISTERED";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "USERNAME";
    public static final String Col_3 = "PASSWORD";
    public static final String Col_4 = "FNAME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //public com.example.dbelenskiregisterandlogin.DatabaseHelper(){

    //}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE REGISTERED (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT, FNAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean checkUserExists(String uname, String pass){
        String[] cols = {Col_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Col_2 + " =? and " + Col_3 + " =?";
        String[] selectionArgs = {uname, pass};
        Cursor cursor = db.query(TABLE_NAME, cols, selection, selectionArgs,null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public long addUser(String uname, String pass, String fname){
        Boolean exists = checkUserExists(uname, pass);
        if (!exists) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues vals = new ContentValues();
            vals.put(Col_2, uname);
            vals.put(Col_3, pass);
            vals.put(Col_4, fname);
            long res = db.insert("REGISTERED", null, vals);
            db.close();
            return res;
        }
        else
            return -1;
    }


}
