package com.example.easeassistantapp;

 import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

 import java.util.ArrayList;
        import java.util.List;

public class AcademicDBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    List<AcademicNoteDetails> noteDetailsList = new ArrayList<AcademicNoteDetails>();

    public AcademicDBHelper(Context context) {
        super(context, "mad1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Notedetails (name TEXT primary key, note TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
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

    public List<AcademicNoteDetails> getAllData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Notedetails", null);
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex("name");
            String title = cursor.getString(index1);
            int index2 = cursor.getColumnIndex("note");
            String note = cursor.getString(index2);
            AcademicNoteDetails noteDetails = new AcademicNoteDetails(note, title);
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
