package com.mercadolibre.products.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mercadolibre.products.R;
import com.mercadolibre.products.util.MyLog;

import com.mercadolibre.products.viewmodels.ViewModelMain;

public class MainActivity extends AppCompatActivity implements SearchDialog.NoticeSearchDialog {
    private final String LOG = "MainActivity";
    private ViewModelMain viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar();
        viewModel = new ViewModelProvider(this).get(ViewModelMain.class);

        viewModel.getSearch().observe(this, resource -> {
            switch (resource.status){
                case LOADING:
                    findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
                    findViewById(R.id.containerErros).setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    findViewById(R.id.progress_bar).setVisibility(View.GONE);
                    findViewById(R.id.containerErros).setVisibility(View.GONE);
                    if(resource.data==null || resource.data.getProductos()==null)return;
                    MyLog.i(LOG,"Products: "+resource.data.getProductos().size());
                    break;
                case ERROR:
                    findViewById(R.id.containerErros).setVisibility(View.VISIBLE);
                    ((TextView)findViewById(R.id.textError)).setText(resource.message);
                    findViewById(R.id.progress_bar).setVisibility(View.GONE);
                    break;
            }
        });

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
            if(!((EditText)findViewById(R.id.search_edittext)).getText().toString().isEmpty()){
                searchDialog.setProductDescription(((EditText)findViewById(R.id.search_edittext)).getText().toString());
            }
            searchDialog.show(getSupportFragmentManager(), "SearchDialog");
        });
    }

    @Override
    public void onDialogSearch(String productDescription) {
        viewModel.startSearch(productDescription,0,20);
        ((EditText)findViewById(R.id.search_edittext)).setText(productDescription);
    }
}