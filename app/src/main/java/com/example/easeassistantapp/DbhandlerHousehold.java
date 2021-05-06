package com.example.easeassistantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbhandlerHousehold extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "mad4";
    private static final String TABLE_NAME = "household";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DbhandlerHousehold(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE + " TEXT,"
                +DESCRIPTION + " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+" TEXT" +
                ");";

        /*
            CREATE TABLE todo (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT,
            started TEXT,finished TEXT); */

        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // Create tables again
        onCreate(db);

    }

    public void addList(HouseHoldModel houseHoldModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, houseHoldModel.getTitle());
        contentValues.put(DESCRIPTION, houseHoldModel.getDescription());
        contentValues.put(STARTED, houseHoldModel.getStarted());
        contentValues.put(FINISHED, houseHoldModel.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
    }

    // calculation
    public int countLists(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    // Get all listings
    public List<HouseHoldModel> getAllLists(){

        List<HouseHoldModel> listings = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new list object
                HouseHoldModel houseHoldModel = new HouseHoldModel();

                houseHoldModel.setId(cursor.getInt(0));
                houseHoldModel.setTitle(cursor.getString(1));
                houseHoldModel.setDescription(cursor.getString(2));
                houseHoldModel.setStarted(cursor.getLong(3));
                houseHoldModel.setFinished(cursor.getLong(4));


                listings.add(houseHoldModel);
            }while (cursor.moveToNext());
        }
        return listings;
    }

    //Delete function
    public void hs_delete_List(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //Get a single list
    public HouseHoldModel getSingleList(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,STARTED, FINISHED},
                ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        HouseHoldModel houseHoldModel;
        if(cursor != null){
            cursor.moveToFirst();
            houseHoldModel = new HouseHoldModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)
            );
            return houseHoldModel;
        }
        return null;
    }

    //Update function
    public int hs_update_List(HouseHoldModel houseHoldModel){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,houseHoldModel.getTitle());
        contentValues.put(DESCRIPTION, houseHoldModel.getDescription());
        contentValues.put(STARTED,houseHoldModel.getStarted());
        contentValues.put(FINISHED,houseHoldModel.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(houseHoldModel.getId())});

        db.close();
        return status;
    }
}

