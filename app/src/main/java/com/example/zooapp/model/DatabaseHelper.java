package com.example.zooapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "zoo.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE animals (id INTEGER PRIMARY KEY, name TEXT, continent TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllAnimals() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM animals", null);
        return cursor;
    }

    public long insertAnimal(Animal animal) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM animals WHERE name = ? AND continent = ?";
        String[] selectionArgs = { animal.stringResourceId, animal.continent };
        Cursor cursor = db.rawQuery(query, selectionArgs);

        long newRowId = -1;

        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put("name", animal.stringResourceId);
            values.put("continent", animal.continent);
            newRowId = db.insert("animals", null, values);
        }

        cursor.close();

        return newRowId;
    }

    public void deleteAnimal(Animal animal) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "name = ? AND continent = ?";
        String[] whereArgs = {animal.stringResourceId, animal.continent };

        int rowsDeleted = db.delete("animals", whereClause, whereArgs);
    }
}
