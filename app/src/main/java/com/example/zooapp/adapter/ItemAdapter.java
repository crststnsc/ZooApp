package com.example.zooapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zooapp.R;
import com.example.zooapp.model.Animal;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<Animal> dataset;
    public ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public ItemAdapter(Context context, List<Animal> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CardView cardView;
        public TextView textView;

        public TextView continentText;

        public ItemViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            textView = view.findViewById(R.id.info_text);
            cardView = view.findViewById(R.id.card_view);
            continentText = view.findViewById(R.id.continent_text);
        }

        @Override
        public void onClick(View view){
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition());
        }

    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View adapterLayout = new View(parent.getContext());

        switch(viewType){
            case 0:
                adapterLayout = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.eu_layout, parent, false);
                break;
            case 1:
                adapterLayout = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);
                break;
            default:
                break;
        }

        return new ItemViewHolder(adapterLayout);
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataset.get(position).getContinent()) {
            case "europe":
                return 0;
            case "africa":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Animal item = dataset.get(position);
        Log.e("ERROR", String.valueOf(dataset.size()));

        holder.textView.setText(item.getStringResourceId());
        holder.continentText.setText(item.continent);
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

