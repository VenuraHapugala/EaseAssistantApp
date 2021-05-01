package com.example.easeassistantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

}
