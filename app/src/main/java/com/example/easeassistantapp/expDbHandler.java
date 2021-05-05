package com.example.easeassistantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class expDbHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "mad";
    private static final String TABLE_NAME = "expenses";

    // Column names
    private static final String ID = "id";
    private static final String DETAILS = "details";
    private static final String AMOUNT = "amount";
    private static final String TYPE = "type";
    private static final String DATE = "date";

    public expDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +DETAILS + " TEXT,"
                +AMOUNT + " DOUBLE,"
                +TYPE+ " TEXT,"
                +DATE+" LONG" +
                ");";

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
    //add new record
    public void addExpenses(Expenses expenses){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DETAILS,expenses.getDetails());
        contentValues.put(AMOUNT, expenses.getAmount());
        contentValues.put(TYPE, expenses.getType());
        contentValues.put(DATE,expenses.getDate());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
    }
    //get all records into a list
    public List<Expenses> getAllExpenses(){

        List<Expenses> exp = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new Expenses object
                Expenses expenses = new Expenses();
                // set values
                expenses.setId(cursor.getInt(0));
                expenses.setDetails(cursor.getString(1));
                expenses.setAmount("Rs "+cursor.getString(2));
                expenses.setType(cursor.getString(3));
                expenses.setDate(cursor.getLong(4));

                //adding values to array
                exp.add(expenses);
            }while (cursor.moveToNext());
        }
        return exp;
    }
    //delete
    public void deleteExp(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID + " =?",new String[]{String.valueOf(id)});
        db.close();
    }
    //taking one by one
    public Expenses getSingleEXP(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,DETAILS,AMOUNT,TYPE,DATE},ID + "= ?",new String[]{String.valueOf(id)},null,null,null);

        Expenses expenses;
        if(cursor != null) {
            cursor.moveToFirst();
            expenses = new Expenses(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getLong(4)
            );
            return expenses;
        }
        return null;
    }
    //update
    public int updateExp(Expenses expenses){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DETAILS,expenses.getDetails());
        contentValues.put(AMOUNT, expenses.getAmount());
        contentValues.put(TYPE, expenses.getType());
        contentValues.put(DATE,expenses.getDate());

        int status = db.update(TABLE_NAME,contentValues,ID + "= ?",new String[]{String.valueOf(expenses.getId())});

        db.close();
        return status;
    }
    /*public float balanceExp() {
        float total = 0;
        SQLiteDatabase db = getReadableDatabase();
        String tot = "SELECT TYPE FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(tot,null);
        cursor.moveToFirst();
        String ty = TYPE;
        float am = Float.parseFloat(AMOUNT);

        if (ty == "Credit") {
            total = total + am;
        }
        if (ty == "Debit") {
            total = total - am;
        }
        return total;
    }*/
    /*
    public float balanceExp() {
        float total = 0;
        String ty = type.getText().toString();
        float am = Float.parseFloat(amount.getText().toString());

        if (ty == "Credit") {
            total = total + am;
        }
        if (ty == "Debit") {
            total = total - am;
        }
        return total;
    }*/


    }

