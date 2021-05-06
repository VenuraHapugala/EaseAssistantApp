package com.example.easeassistantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandlerHealth extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "mad";
    private static final String TABLE_NAME = "health";


    // Column names
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public DbHandlerHealth(@Nullable Context context) {
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
            CREATE TABLE health (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT,
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

    /*
            +-------+-------+-------+-------+
            | Col 1 | col 2 | Col 3 | Col 4 |
            +-------+-------+-------+-------+
            |   1   |   2   |  red  |  dog  |
            +-------+-------+-------+-------+
            |   2   |   4   |  blue |  cat  |
            +-------+-------+-------+-------+
            |   3   |   9   |  red  | bird  |
            +-------+-------+-------+-------+
     */

    // Add a single todo
    public void addToDo(ToDoHealth toDoHealth){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,toDoHealth.getTitle());
        contentValues.put(DESCRIPTION, toDoHealth.getDescription());
        contentValues.put(STARTED,toDoHealth.getStarted());
        contentValues.put(FINISHED,toDoHealth.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
    }

    // Count todo table records
    public int countToDo(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    // Get all todos into a list
    public List<ToDoHealth> getAllToDos(){

        List<ToDoHealth> toDos = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new ToDo object
                ToDoHealth toDoHealth = new ToDoHealth();
                // mmgby6hh
                toDoHealth.setId(cursor.getInt(0));
                toDoHealth.setTitle(cursor.getString(1));
                toDoHealth.setDescription(cursor.getString(2));
                toDoHealth.setStarted(cursor.getLong(3));
                toDoHealth.setFinished(cursor.getLong(4));

                //toDos [obj,objs,asas,asa]
                toDos.add(toDoHealth);
            }while (cursor.moveToNext());
        }
        return toDos;
    }


    // Delete item
    public void deleteToDo(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }




    // Get a single todoHealth
    public ToDoHealth getSingleToDo(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,STARTED, FINISHED},
                ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        ToDoHealth toDoHealth;
        if(cursor != null){
            cursor.moveToFirst();
            toDoHealth= new ToDoHealth(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)
            );
            return toDoHealth;
        }
        return null;
    }

    // Update a single todo
    public int updateSingleToDo(ToDoHealth toDoHealth){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,toDoHealth.getTitle());
        contentValues.put(DESCRIPTION, toDoHealth.getDescription());
        contentValues.put(STARTED,toDoHealth.getStarted());
        contentValues.put(FINISHED,toDoHealth.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(toDoHealth.getId())});

        db.close();
        return status;
    }
}
