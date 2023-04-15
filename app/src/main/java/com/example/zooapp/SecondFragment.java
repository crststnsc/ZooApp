package com.example.zooapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zooapp.adapter.ItemAdapter;
import com.example.zooapp.adapter.ItemClickListener;
import com.example.zooapp.data.Datasource;
import com.example.zooapp.model.Animal;

import java.util.List;

public class SecondFragment extends Fragment {

    String name, continent;
    int color;

    SecondFragment(){
        super(R.layout.second_fragment_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment_layout, container, false);

        // Access the arguments passed from the previous fragment
        Bundle args = getArguments();
        String name = args.getString("name");
        String continent = args.getString("continent");

        int color = args.getInt("color");
        this.color = color;

        // Update the views in the SecondFragment with the data
        TextView nameTextView = view.findViewById(R.id.name_text);
        nameTextView.setText(name);

        TextView continentTextView = view.findViewById(R.id.continent_text);
        continentTextView.setText(continent);

        // Set the background color of the layout to the color from the previous fragment

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(color);
    }
}
