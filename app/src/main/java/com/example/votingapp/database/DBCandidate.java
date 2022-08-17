package com.example.votingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import java.util.HashMap;

//import androidx.annotation.Nullable;

public class DBCandidate extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "candidateInfo.db";

    public DBCandidate(Context context) {super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table candidates(name TEXT, description TEXT, id TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists candidates");
    }

    //insert data
    public Boolean addCandidate(String name, String description, String id) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put("name", name);
        cValues.put("description", description);
        cValues.put("id", id);

        long result = MyDB.insert("candidates", null, cValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //update
    public Boolean updateCandidate(String name, String description, String id) {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put("name", name);
        cValues.put("description", description);
        cValues.put("id", id);

        Cursor cursor = MyDB.rawQuery("select * from candidates where id = ?", new String[] {id});

        if (cursor.getCount()>0) {
            long result = MyDB.update("candidates", cValues, "id = ?", new String[] {id});
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
    public Boolean deleteCandidate(String id) {

        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from candidates where id = ?", new String[] {id});
        if (cursor.getCount()>0) {
            long result = MyDB.delete("candidates", "id = ?", new String[] {id});
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
    public Cursor getCandidates() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from candidates", null);
        return cursor;
    }

    //view one
    public Cursor getOneCandidate(String id) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from candidates where id = ?", new String[] {id});
        return cursor;
    }



    //validations
    public Boolean checkCandidateID(String id) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from candidates where id = ?", new String[] {id});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //get one - stack overflow
    public HashMap<String, String> getCandidateInfo(String id) {
        HashMap<String, String> candidateList = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM candidates where id ='"+id+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                candidateList.put("id", cursor.getString(2));
            }while (cursor.moveToNext());
        }
        return candidateList;
    }

}
