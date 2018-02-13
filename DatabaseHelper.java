package com.example.j.budjetto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tapahtumat";
    public static final String TABLE_NAME = "transactions_table";
    public static final String ID_COL = "ID";
    public static final String TYPE_COL = "TYPE";
    public static final String AMOUNT_COL = "AMOUNT";
    public static final String TIME_COL = "TIME";
    public double totalAmount = 0;
    public String keratty;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TYPE TEXT,AMOUNT REAL,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String type, double amount, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TYPE",type);
        contentValues.put("AMOUNT",amount);
        contentValues.put("TIME",time);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != -1;

    }

    public double getData() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                AMOUNT_COL
        };

        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,AMOUNT_COL + " ASC");
        while(cursor.moveToNext()){
            double k = cursor.getDouble(cursor.getColumnIndexOrThrow(AMOUNT_COL));
            totalAmount = totalAmount + k;
        }
        cursor.close();
        return totalAmount;

    }

    public ArrayList showAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        List dataValues = new ArrayList<>();

        String[] projection = {
                ID_COL,
                TYPE_COL,
                AMOUNT_COL,
                TIME_COL
        };

        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,ID_COL + " ASC");
        while(cursor.moveToNext()){
            keratty = "ID: "+ cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL))+" ";
            //keratty = keratty + cursor.getString(cursor.getColumnIndexOrThrow(TYPE_COL))+" ";
            keratty = keratty+ "Amount: £" + cursor.getDouble(cursor.getColumnIndexOrThrow(AMOUNT_COL))+ " ";
            keratty = keratty+ "Time: " + cursor.getString(cursor.getColumnIndexOrThrow(TIME_COL));

            dataValues.add(keratty);
        }
        cursor.close();

        return (ArrayList) dataValues;
    }

    public ArrayList getObjectData(){
        SQLiteDatabase db = this.getReadableDatabase();
        List dataObjects = new ArrayList<>();

        String[] projection = {
                ID_COL,
                TYPE_COL,
                AMOUNT_COL,
                TIME_COL
        };

        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,ID_COL + " ASC");
        while(cursor.moveToNext()){
            int iiDee = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL));
            String taippi = cursor.getString(cursor.getColumnIndexOrThrow(TYPE_COL));
            double  amountti= cursor.getDouble(cursor.getColumnIndexOrThrow(AMOUNT_COL));
            String aika = cursor.getString(cursor.getColumnIndexOrThrow(TIME_COL));

            Transaction dataTrans = new Transaction(iiDee,taippi,amountti,aika);
            dataObjects.add(dataTrans);
        }
        cursor.close();

        return (ArrayList) dataObjects;
    }
}