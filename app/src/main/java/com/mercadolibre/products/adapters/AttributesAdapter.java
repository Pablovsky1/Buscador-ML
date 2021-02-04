package com.mercadolibre.products.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercadolibre.products.R;
import com.mercadolibre.products.models.details.ItemAttributes;
import java.util.ArrayList;

public class AttributesAdapter extends  RecyclerView.Adapter<AttributesAdapter.ViewHolder>{

    private final ArrayList<ItemAttributes> model;
    private final OnItemClickListener itemClickListener;

    public AttributesAdapter(ArrayList<ItemAttributes> model, OnItemClickListener listener) {
        this.model = model;
        this.itemClickListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_attributes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(model.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView valueName;


        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            valueName = itemView.findViewById(R.id.valueName);

        }

        public void bind(final ItemAttributes model, final OnItemClickListener listener) {

            name.setText(model.getName());
            valueName.setText(model.getValue_name());


            itemView.setOnClickListener(v -> listener.onItemClick(model, getAdapterPosition()));

        }


    }
    public interface OnItemClickListener{
        void onItemClick(ItemAttributes model, int position);
    }

}
