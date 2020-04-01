package com.example.dbelenskiregisterandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//class to manage the database and its communications with the apps activities
public class DatabaseHelper extends SQLiteOpenHelper {

    //creating final string values for the databases one table name and column names
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "REGISTERED";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "EMAIL";
    public static final String Col_3 = "PASSWORD";
    public static final String Col_4 = "FNAME";

    //creates database and assigns the name of the database
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //public com.example.dbelenskiregisterandlogin.DatabaseHelper(){

    //}

    //creates the databases table and associated columns with all their keywords
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE REGISTERED (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT, FNAME TEXT)");
    }

    //if an upgrade were to come to the database, it would drop the current table and make a blank one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //checks database for a given email/password pair
    //@param: String email, string value of the inputted email
    //@param: String password, string value of inputted password
    //@return: true/false value whether the user exists in the database or not
    public Boolean checkUserExists(String email, String pass){
        //columns we are interested in querying
        String[] cols = {Col_1};
        //getting current state of the database
        SQLiteDatabase db = getReadableDatabase();
        //sql selection code for query
        String selection = Col_2 + " =? and " + Col_3 + " =?";
        //parameters as query arguments
        String[] selectionArgs = {email, pass};
        //performing query
        Cursor cursor = db.query(TABLE_NAME, cols, selection, selectionArgs,null, null, null);
        //getting number of results
        int count = cursor.getCount();
        //close query
        cursor.close();
        //close database
        db.close();

        //returing if any entries exist that match the email/password pair
        if (count > 0)
            return true;
        else
            return false;
    }

    //adding a user to the database
    //this method assumes validation has been performed already
    //@param: email, string value of the inputted email
    //@param: pass, string value of inputted password
    //@param: fname, string value of the users first name
    //@return: long with value of server response or failure
    public long addUser(String email, String pass, String fname){
        //checking if user already exists before insertion
        Boolean exists = checkUserExists(email, pass);
        //if email/password pair doesn't exist
        if (!exists) {
            //get writable state of the database
            SQLiteDatabase db = this.getWritableDatabase();
            //creates a set of empty string values to hold column, data pairs
            ContentValues vals = new ContentValues(3);
            // putting column/data pairs into vals
            vals.put(Col_2, email);
            vals.put(Col_3, pass);
            vals.put(Col_4, fname);
            //inserting vals into table and getting response
            long res = db.insert("REGISTERED", null, vals);
            //closing database
            db.close();
            //returning server response
            return res;
        }
        // returning -1 to indicate user already exists
        else
            return -1;
    }


}
