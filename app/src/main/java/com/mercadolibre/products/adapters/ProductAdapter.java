package com.mercadolibre.products.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mercadolibre.products.R;
import com.mercadolibre.products.models.search.SearchProduct;
import com.squareup.picasso.Picasso;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private final List<SearchProduct> model;
    private final OnItemClickListener itemClickListener;

    public ProductAdapter(List<SearchProduct> model, OnItemClickListener listener) {
        this.model = model;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_products, parent, false);
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
        private final TextView title;
        private final ImageView image;
        private final TextView price;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);

        }

        public void bind(final SearchProduct model, final OnItemClickListener listener) {

            Picasso.get()
                    .load(model.getThumbnail())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.without_image)
                    .into(image);

            title.setText(model.getTitle());

            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
            String result = format.format(Float.parseFloat(model.getPrice()));
            price.setText(result);


            itemView.setOnClickListener(v -> listener.onItemClick(model, getAdapterPosition()));

        }


    }
    public interface OnItemClickListener{
        void onItemClick(SearchProduct model, int position);
    }

}
