package com.example.votingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.HashMap;

//import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userInfo.db";

    public DBHelper(Context context) {super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(name TEXT, username TEXT primary key, email TEXT, phoneNumber TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    //insert
    public Boolean insertData(String name, String username, String email, String phoneNumber, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("username", username);
        values.put("email", email);
        values.put("phoneNumber", phoneNumber);
        values.put("password", password);

        long result = MyDB.insert("users", null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    //view all
    public Cursor getData() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users", null);
        return cursor;
    }

    //update
    public Boolean updateData(String name, String username, String email, String phoneNumber, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("email", email);
        values.put("phoneNumber", phoneNumber);
        values.put("password", password);

        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});

        if (cursor.getCount()>0) {
            long result = MyDB.update("users", values, "username = ?", new String[] {username});
            if (result == -1) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    //delete
    public Boolean deleteData(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0) {
            long result = MyDB.delete("users", "username = ?", new String[] {username});
            if (result == -1) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    //view one
    public Cursor getOneData(TextView username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {String.valueOf(username)});
        return cursor;
    }

    //validate functions
    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //validate functions
    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //get one - stack overflow
    public HashMap<String, String> getUserInfo(String username) {
        HashMap<String, String> userList = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM users where username ='"+username+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //HashMap<String, String> map = new HashMap<String, String>();
                userList.put("username", cursor.getString(1));
                //userList.add(map);
            }while (cursor.moveToNext());
        }
        return userList;
    }

}
