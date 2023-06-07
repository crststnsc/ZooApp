package com.example.zooapp.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.zooapp.R;
import com.example.zooapp.model.Animal;
import com.example.zooapp.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datasource {
    public static List<Animal> loadAnimals(Context context){

        DatabaseHelper dbHelper = new DatabaseHelper(context);

        Cursor cursor = dbHelper.getAllAnimals();

        List<Animal> animals = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String continent = cursor.getString(cursor.getColumnIndexOrThrow("continent"));
            animals.add(new Animal(name, continent));
        }
        return animals;
    }
}
