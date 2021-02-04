package com.mercadolibre.products.ui;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mercadolibre.products.R;
import com.mercadolibre.products.adapters.AttributesAdapter;
import com.mercadolibre.products.adapters.PicturesAdapter;
import com.mercadolibre.products.util.MyLog;
import com.mercadolibre.products.viewmodels.ViewModelProduct;


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
        init();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
           String id = extras.getString("id");
            this.viewModel.loadProduct(id);
        }else{
            finish();
        }

        viewModel.getProduct().observe(this, resource -> {
            switch (resource.status){
                case LOADING:
                    MyLog.i(TAG,"loading product");
                    findViewById(R.id.productProgressBar).setVisibility(View.VISIBLE);
                    findViewById(R.id.containerErrors).setVisibility(View.GONE);
                    findViewById(R.id.retry).setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    MyLog.i(TAG,"producto: "+resource.data.toString());
                    findViewById(R.id.productProgressBar).setVisibility(View.GONE);
                    findViewById(R.id.containerErrors).setVisibility(View.GONE);
                    findViewById(R.id.retry).setVisibility(View.GONE);
                    ((TextView)findViewById(R.id.condition)).setText(resource.data.getCondition());
                    ((TextView)findViewById(R.id.soldQuantity)).setText(resource.data.getSoldQuantity());
                    ((TextView)findViewById(R.id.title)).setText(resource.data.getTitle());
                    ((TextView)findViewById(R.id.price)).setText(resource.data.getPrice());
                    ((TextView)findViewById(R.id.quantity)).setText(String.valueOf(resource.data.getAvailableQuantity()));

                    break;
                case ERROR:
                    MyLog.e(TAG,"product error "+resource.message);
                    findViewById(R.id.containerErrors).setVisibility(View.VISIBLE);
                    findViewById(R.id.productProgressBar).setVisibility(View.GONE);
                    ((TextView)findViewById(R.id.textError)).setText(resource.message);
                    findViewById(R.id.retry).setVisibility(View.VISIBLE);
                    break;
            }
        });


        viewModel.getAttributes().observe(this, itemAttributes -> {
            MyLog.i(TAG,"attributes: "+itemAttributes.size());
            myAdapterAttributes = new AttributesAdapter(itemAttributes, (model, position) -> {

            });
            myRecyclerViewAttributes.setItemAnimator(new DefaultItemAnimator());
            myRecyclerViewAttributes.setLayoutManager(myLayoutManagerAttributes);
            myRecyclerViewAttributes.setAdapter(myAdapterAttributes);
        });


        viewModel.getPictures().observe(this, itemPictures -> {
            MyLog.i(TAG,"pictures: "+itemPictures.size());
            myAdapterPictures = new PicturesAdapter(itemPictures, (model, position) -> {

            });
            myRecyclerViewPictures.setItemAnimator(new DefaultItemAnimator());
            myRecyclerViewPictures.setLayoutManager(myLayoutManagerPictures);
            myRecyclerViewPictures.setAdapter(myAdapterPictures);
        });

        findViewById(R.id.retry).setOnClickListener(v -> {
            if(extras != null) {
                String id = extras.getString("id");
                viewModel.loadProduct(id);
            }
        });

    }

    private void init(){
        this.viewModel = new ViewModelProvider(this).get(ViewModelProduct.class);
        this.myRecyclerViewPictures = findViewById(R.id.recyclerPicture);
        this.myLayoutManagerPictures = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        this.myRecyclerViewAttributes = findViewById(R.id.recyclerAttributes);
        this.myRecyclerViewAttributes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        this.myLayoutManagerAttributes = new LinearLayoutManager(this);
    }

    private void toolbar() {
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