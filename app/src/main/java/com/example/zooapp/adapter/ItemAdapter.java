package com.example.zooapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private OnDeleteClickListener onDeleteClickListener;
    public List<Animal> getDataset() {
        return dataset;
    }

    public void setDataset(List<Animal> dataset) {
        this.dataset = dataset;
    }

    private List<Animal> dataset;
    public ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public ItemAdapter(Context context, List<Animal> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CardView cardView;
        public TextView textView;
        ImageButton deleteButton;
        public TextView continentText;

        public ItemViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            textView = view.findViewById(R.id.info_text);
            cardView = view.findViewById(R.id.card_view);
            continentText = view.findViewById(R.id.continent_text);

            deleteButton = view.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(v -> {
                if(onDeleteClickListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        onDeleteClickListener.onDeleteClick(position);
                    }
                }
            });
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
        View adapterLayout = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_layout, parent, false);
        return new ItemViewHolder(adapterLayout);
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataset.get(position).continent) {
            case "europe":
                return 0;
            case "africa":
                return 1;
            case "americas":
                return 2;
            case "asia":
                return 3;
            case "australia":
                return 4;
            default:
                return -1;
        }

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Animal item = dataset.get(position);
        if (holder.textView != null) {
            holder.textView.setText(item.stringResourceId);
        }
        if (holder.continentText != null) {
            holder.continentText.setText(item.continent);
        }
    }


    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

