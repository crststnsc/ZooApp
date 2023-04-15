package com.example.zooapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zooapp.adapter.ItemAdapter;
import com.example.zooapp.adapter.ItemClickListener;
import com.example.zooapp.data.Datasource;
import com.example.zooapp.model.Animal;

import java.util.List;

public class MainFragment extends Fragment implements ItemClickListener {

    public MainFragment() {
        super(R.layout.main_fragment_layout);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment_layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Animal> dataset = Datasource.loadAnimals(this.getActivity());

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        ItemAdapter adapter = new ItemAdapter(this.getActivity(), dataset);
        adapter.setClickListener((ItemClickListener) this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onClick(View view, int position){
        String name = ((TextView) view.findViewById(R.id.info_text)).getText().toString();
        String continent = ((TextView) view.findViewById(R.id.continent_text)).getText().toString();

        int color;

        switch(continent){
            case "europe":
                color = getActivity().getResources().getColor(R.color.Green);
                break;
            case "africa":
                color = getActivity().getResources().getColor(R.color.Yellow);
                break;
            case "asia":
                color = getActivity().getResources().getColor(R.color.Red);
                break;
            case "americas":
                color = getActivity().getResources().getColor(R.color.Blue);
                break;
            case "australia":
                color = getActivity().getResources().getColor(R.color.Orange);
                break;
            default:
                color = Color.WHITE;
                break;
        }

        SecondFragment secondFragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("continent", continent);
        args.putInt("color", color);
        secondFragment.setArguments(args);


        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, secondFragment);
        transaction.addToBackStack("second fragment");
        transaction.commit();
    }
}
