package com.example.rohan1;


//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import androidx.annotation.Nullable;
//public class DatabaseHelper extends SQLiteOpenHelper {
//    public static final String databaseName = "SignLog.db";
//    public DatabaseHelper(@Nullable Context context) {
//        super(context, "SignLog.db", null, 1);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase MyDatabase) {
//        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
//        MyDB.execSQL("drop Table if exists users");
//    }
//    public Boolean insertData(String email, String password){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("email", email);
//        contentValues.put("password", password);
//        long result = MyDatabase.insert("users", null, contentValues);
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//    public Boolean checkEmail(String email){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
//        if(cursor.getCount() > 0) {
//            return true;
//        }else {
//            return false;
//        }
//    }
//    public Boolean checkEmailPassword(String email, String password){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
//        if (cursor.getCount() > 0) {
//            return true;
//        }else {
//            return false;
//        }
//    }
//}


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SignLog.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
        onCreate(db);
    }

    public boolean insertData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Method to check if the user is already logged in
    public boolean isUserLoggedIn() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
        boolean isLoggedIn = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isLoggedIn;
    }

    public String getLoggedInUserEmail() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM users LIMIT 1", null);
        String loggedInEmail = null;
        if (cursor.moveToFirst()) {
            loggedInEmail = cursor.getString(cursor.getColumnIndex("email"));
        }
        cursor.close();
        db.close();
        return loggedInEmail;
    }

    public void deleteLoginData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete the login data from the appropriate table
        db.delete("login_table", null, null);
        db.close();
    }
}
