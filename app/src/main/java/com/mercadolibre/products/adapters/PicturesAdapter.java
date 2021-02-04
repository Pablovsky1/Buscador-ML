package com.mercadolibre.products.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercadolibre.products.R;
import com.mercadolibre.products.models.details.ItemPictures;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PicturesAdapter extends  RecyclerView.Adapter<PicturesAdapter.ViewHolder>{

    private final ArrayList<ItemPictures> model;
    private final OnItemClickListener itemClickListener;

    public PicturesAdapter(ArrayList<ItemPictures> model, OnItemClickListener listener) {
        this.model = model;
        this.itemClickListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_pictures, parent, false);
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
        private final ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.picture);

        }

        public void bind(final ItemPictures model, final OnItemClickListener listener) {

            Picasso.get().load(model.getUrl()).into(picture);


            itemView.setOnClickListener(v -> listener.onItemClick(model, getAdapterPosition()));

        }


    }
    public interface OnItemClickListener{
        void onItemClick(ItemPictures model, int position);
    }

}