package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zooapp.adapter.ItemAdapter;
import com.example.zooapp.adapter.ItemClickListener;
import com.example.zooapp.data.Datasource;
import com.example.zooapp.model.Animal;
import com.example.zooapp.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment implements View.OnClickListener {

    public MainFragment() {
        super(R.layout.main_fragment_layout);
    }

    RecyclerView recyclerView;
    ItemAdapter adapter;
    List<Animal> dataset;
    List<String> continents;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addButton = (Button) requireView().findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        dataset = Datasource.loadAnimals(this.getActivity());
        continents = new ArrayList<String>(){{
            add("africa");
            add("asia");
            add("europe");
            add("americas");
            add("australia");
        }};

        databaseHelper = new DatabaseHelper(getActivity());
        db = databaseHelper.getWritableDatabase();

        builder = new AlertDialog.Builder(getActivity());

        recyclerView = getView().findViewById(R.id.recycler_view);
        adapter = new ItemAdapter(this.getActivity(), dataset);
        adapter.setOnDeleteClickListener(position -> {
            databaseHelper.deleteAnimal(dataset.get(position));
            dataset.remove(position);
            adapter.notifyItemRemoved(position);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }


    AlertDialog.Builder builder;
    @Override
    public void onClick(View v) {
        String name = ((TextView) getView().findViewById(R.id.nameEdit)).getText().toString();
        String continent = ((TextView) getView().findViewById(R.id.conEdit)).getText().toString();
        Animal newAnimal = new Animal(name, continent);

        if (name.isEmpty() || continent.isEmpty()) {
            builder.setMessage("Please fill in all fields")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        if (!continents.contains(continent.toLowerCase())) {
            builder.setMessage("Please enter a valid continent")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        long newRow = databaseHelper.insertAnimal(newAnimal);
        if(newRow == -1){
            builder.setMessage("Animal already exists")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, id) -> {
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        dataset.add(newAnimal);
        adapter.notifyItemInserted(dataset.size() - 1);
    }
}
