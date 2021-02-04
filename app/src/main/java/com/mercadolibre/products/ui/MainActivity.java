package com.mercadolibre.products.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mercadolibre.products.R;
import com.mercadolibre.products.adapters.ProductAdapter;

import com.mercadolibre.products.viewmodels.ViewModelMain;

public class MainActivity extends AppCompatActivity implements SearchDialog.NoticeSearchDialog {
    private final String TAG = "MainActivity";
    private NestedScrollView scrollView;
    private ViewModelMain viewModel;
    private RecyclerView myRecyclerView;

    private RecyclerView.Adapter myAdapter;

    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar();
        init();

        viewModel.getSearch().observe(this, resource -> {
            switch (resource.status) {
                case LOADING:
                    findViewById(R.id.containerErros).setVisibility(View.GONE);
                    findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
                    break;
                case SUCCESS:
                    findViewById(R.id.progress_bar).setVisibility(View.GONE);
                    findViewById(R.id.containerErros).setVisibility(View.GONE);
                    break;
                case ERROR:
                    findViewById(R.id.containerErros).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.textError)).setText(resource.message);
                    findViewById(R.id.progress_bar).setVisibility(View.GONE);
                    break;
            }
        });

        myRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        viewModel.getProducts().observe(this, searchProducts -> {
            findViewById(R.id.progressBarLoading).setVisibility(View.GONE);
            myAdapter = new ProductAdapter(searchProducts, (model, position) -> {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                intent.putExtra("id",model.getId());
                startActivity(intent);
            });
            myRecyclerView.setItemAnimator(new DefaultItemAnimator());
            myRecyclerView.setLayoutManager(myLayoutManager);
            myRecyclerView.setAdapter(myAdapter);

        });

        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == -(v.getMeasuredHeight() - v.getChildAt(0).getMeasuredHeight())) {
                findViewById(R.id.progressBarLoading).setVisibility(View.VISIBLE);
                viewModel.loadProducts(((EditText) findViewById(R.id.search_edittext)).getText().toString());
            }
        });


    }

    private void init() {
        this.myRecyclerView = findViewById(R.id.productsRecyclerView);
        this.myLayoutManager = new LinearLayoutManager(this);
        this.scrollView = findViewById(R.id.nestedScrollView);
        this.viewModel = new ViewModelProvider(this).get(ViewModelMain.class);
    }


    public void toolbar() {
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.search_edittext).setOnClickListener(v -> {
            SearchDialog searchDialog = new SearchDialog();
            if (!((EditText) findViewById(R.id.search_edittext)).getText().toString().isEmpty()) {
                searchDialog.setProductDescription(((EditText) findViewById(R.id.search_edittext)).getText().toString());
            }
            searchDialog.show(getSupportFragmentManager(), "SearchDialog");
        });
    }

    @Override
    public void onDialogSearch(String productDescription) {
        ((EditText) findViewById(R.id.search_edittext)).setText(productDescription);
        viewModel.startSearch(productDescription);
        if (myAdapter != null) {
            myRecyclerView.removeAllViews();
        }

    }

}