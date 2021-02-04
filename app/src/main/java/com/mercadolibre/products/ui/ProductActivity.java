package com.mercadolibre.products.ui;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.TextView;

import com.mercadolibre.products.R;
import com.mercadolibre.products.adapters.AttributesAdapter;
import com.mercadolibre.products.adapters.PicturesAdapter;
import com.mercadolibre.products.models.details.Item;
import com.mercadolibre.products.models.details.ItemAttributes;
import com.mercadolibre.products.models.details.ItemPictures;
import com.mercadolibre.products.util.Resource;
import com.mercadolibre.products.viewmodels.ViewModelProduct;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private final String TAG = "ProductActivity";
    private  ViewModelProduct viewModel;
    private RecyclerView myRecyclerViewAttributes,myRecyclerViewPictures;

    private RecyclerView.Adapter myAdapterAttributes,myAdapterPictures;

    private RecyclerView.LayoutManager myLayoutManagerAttributes,myLayoutManagerPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        toolbar();

        this.viewModel = new ViewModelProvider(this).get(ViewModelProduct.class);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
           String id = extras.getString("id");
            this.viewModel.loadProduct(id);
        }

        viewModel.getProduct().observe(this, new Observer<Resource<Item>>() {
            @Override
            public void onChanged(Resource<Item> resource) {
                switch (resource.status){
                    case LOADING:
                        break;
                    case SUCCESS:

                        ((TextView)findViewById(R.id.condition)).setText(resource.data.getCondition());
                        ((TextView)findViewById(R.id.soldQuantity)).setText(resource.data.getSoldQuantity());
                        ((TextView)findViewById(R.id.title)).setText(resource.data.getTitle());
                        ((TextView)findViewById(R.id.price)).setText(resource.data.getPrice());
                        ((TextView)findViewById(R.id.quantity)).setText(getString(R.string.product_available_quantity).concat(": ").concat(String.valueOf(resource.data.getAvailableQuantity())));

                        break;
                    case ERROR:
                        break;
                }
            }
        });
        this.myRecyclerViewAttributes = findViewById(R.id.recyclerAttributes);
        this.myLayoutManagerAttributes = new LinearLayoutManager(this);
        myRecyclerViewAttributes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        viewModel.getAttributes().observe(this, new Observer<ArrayList<ItemAttributes>>() {
            @Override
            public void onChanged(ArrayList<ItemAttributes> itemAttributes) {
                myAdapterAttributes = new AttributesAdapter(itemAttributes, (model, position) -> {

                });
                myRecyclerViewAttributes.setItemAnimator(new DefaultItemAnimator());
                myRecyclerViewAttributes.setLayoutManager(myLayoutManagerAttributes);
                myRecyclerViewAttributes.setAdapter(myAdapterAttributes);
            }
        });

        this.myRecyclerViewPictures = findViewById(R.id.recyclerPicture);
        this.myLayoutManagerPictures = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        viewModel.getPictures().observe(this, new Observer<ArrayList<ItemPictures>>() {
            @Override
            public void onChanged(ArrayList<ItemPictures> itemPictures) {
                myAdapterPictures = new PicturesAdapter(itemPictures, (model, position) -> {

                });
                myRecyclerViewPictures.setItemAnimator(new DefaultItemAnimator());
                myRecyclerViewPictures.setLayoutManager(myLayoutManagerPictures);
                myRecyclerViewPictures.setAdapter(myAdapterPictures);
            }
        });

    }



    public void toolbar() {
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}