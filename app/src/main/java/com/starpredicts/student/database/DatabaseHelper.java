package com.starpredicts.student.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "crudOne.db";
    private static final String TABLE_NAME = "table_one";

    private static final String ID = "id";
    private static final String NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ONE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + NAME + " TEXT )";
        db.execSQL(CREATE_TABLE_ONE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addText(String text) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, text);
        database.insert(TABLE_NAME, null, values);
        return true;
    }

    public boolean updateText(String Id, String text) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, text);
        database.update(TABLE_NAME, values, ID + "= ?", new String[]{Id});
        return true;
    }

    public void deleteText(String Id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,ID +"= ?",new String[]{Id});
    }

    public ArrayList getAllText() {

        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<String> readValue = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            readValue.add(cursor.getString(cursor.getColumnIndex(NAME)));
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return readValue;
    }
}
