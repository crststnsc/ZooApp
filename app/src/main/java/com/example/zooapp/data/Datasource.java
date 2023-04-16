package com.example.zooapp.data;

import android.content.Context;
import android.util.Log;

import com.example.zooapp.R;
import com.example.zooapp.model.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datasource {
    public static List<Animal> loadAnimals(Context context){

        String[] names = context.getResources().getStringArray(R.array.names);
        String[] continents = context.getResources().getStringArray(R.array.continents);

        List<Animal> animals = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            animals.add(new Animal(names[i], continents[i]));
        }
        return animals;
    }
}
