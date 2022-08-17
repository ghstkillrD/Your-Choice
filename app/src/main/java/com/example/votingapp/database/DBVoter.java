package com.example.votingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import java.util.HashMap;

//import androidx.annotation.Nullable;

public class DBVoter extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "voterInfo.db";

    public DBVoter(Context context) {super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase MyDB) {


        MyDB.execSQL("create Table voters(name TEXT, username TEXT primary key, phone TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists voters");
    }


    //insert data
    public Boolean addVoters(String name, String username, String phone) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues vValues = new ContentValues();

        vValues.put("name", name);
        vValues.put("username", username);
        vValues.put("phone", phone);

        long result = MyDB.insert("voters", null, vValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //update
    public Boolean updateVoter(String name, String username, String phone) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        ContentValues vValues = new ContentValues();

        vValues.put("name", name);
        vValues.put("username", username);
        vValues.put("phone", phone);


        Cursor cursor = MyDB.rawQuery("select * from voters where username = ?", new String[] {username});

        if (cursor.getCount()>0) {
            long result = MyDB.update("voters", vValues, "username = ?", new String[] {username});
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
    public Boolean deleteVoters(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from voters where username = ?", new String[] {username});
        if (cursor.getCount()>0) {
            long result = MyDB.delete("voters", "username = ?", new String[] {username});
            if (result == -1) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    //view data
    public Cursor getVoter() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from voters", null);
        return cursor;
    }

    //view one
    public Cursor getOneVoter(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from voters where username = ?", new String[] {username});
        return cursor;
    }



    //validations
    public Boolean checkVoterUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from voters where username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //get one - stack overflow
    public HashMap<String, String> getVoterInfo(String username) {
        HashMap<String, String> voterList = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM voters where username ='" + username + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                voterList.put("username", cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return voterList;
    }
}
