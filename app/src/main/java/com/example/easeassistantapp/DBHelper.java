package com.example.easeassistantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

<<<<<<< Updated upstream
import androidx.annotation.Nullable;

=======
>>>>>>> Stashed changes
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    List<NoteDetails> noteDetailsList = new ArrayList<>();

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Notedetails (name TEXT primary key, note TEXT )");
    }

    @Override
<<<<<<< Updated upstream
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
=======
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
>>>>>>> Stashed changes
        DB.execSQL("drop Table if exists Notedetails");
    }

    public Boolean savedata(String name, String note) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("note", note);
        long result = DB.insert("Notedetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

<<<<<<< Updated upstream



=======
>>>>>>> Stashed changes
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Notedetails", null);
        return cursor;
    }

    public Cursor getdata(String title) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Notedetails where name = ?", new String[]{title});
        return cursor;
    }

    public List<NoteDetails> getAllData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Notedetails", null);
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex("name");
            String title = cursor.getString(index1);
            int index2 = cursor.getColumnIndex("note");
            String note = cursor.getString(index2);
            NoteDetails noteDetails = new NoteDetails(note, title);
            noteDetailsList.add(noteDetails);
        }
        return noteDetailsList;
    }

    public Boolean updateuserdate(String name, String note) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("note", note);
        Cursor cursor = DB.rawQuery("Select * from Notedetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Notedetails", contentValues, "name = ?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

<<<<<<< Updated upstream
        public Boolean deletedata(String name)
        {
            SQLiteDatabase DB = this.getWritableDatabase();

            Cursor cursor = DB.rawQuery("Select * from Notedetails where name = ?", new String[]{name});
            if  (cursor.getCount()>0)
            {
                long result = DB.delete("Notedetails",  "name = ?", new String[]{name});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
            }else
            {
                return false;
            }
        }


    }
=======
    public Boolean deletedata(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Notedetails where name = ?", new String[]{name});
        if  (cursor.getCount()>0)
        {
            long result = DB.delete("Notedetails",  "name = ?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

}
>>>>>>> Stashed changes
