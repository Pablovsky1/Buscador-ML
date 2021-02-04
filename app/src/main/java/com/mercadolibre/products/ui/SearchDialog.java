package com.mercadolibre.products.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mercadolibre.products.R;
import com.mercadolibre.products.models.suggest.SuggestQueries;
import com.mercadolibre.products.viewmodels.ViewModelSearch;

import java.util.ArrayList;

public class SearchDialog extends DialogFragment {

    private String productDescription;
    private NoticeSearchDialog listener;
    private View myView;
    private ViewModelSearch viewModel;
    private ListView listView;
    private SearchView searchView;

    public interface NoticeSearchDialog{
        void onDialogSearch(String productDescription);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (NoticeSearchDialog) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString()
                    + " must implement NoticeFacturanteListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.dialog_search,container,true);
        return this.myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel= new ViewModelProvider(this).get(ViewModelSearch.class);
        this.listView = view.findViewById(R.id.listview);
        this.searchView = view.findViewById(R.id.search);
        viewModel.getSuggest().observe(getViewLifecycleOwner(), suggestQueries -> {
            ArrayList<String> suggest = new ArrayList<>();
            for (int i = 0 ; i < suggestQueries.size() ; i++){
                suggest.add(suggestQueries.get(i).getQ());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, suggest);

            listView.setOnItemClickListener((parent, view1, position, id) -> {
                listener.onDialogSearch(suggest.get(position));
                dismiss();
            });

            listView.setAdapter(adapter);


        });

        if (this.productDescription != null && !this.productDescription.isEmpty()){
            searchView.setQuery(this.productDescription,false);
        }

        ((android.widget.SearchView)view.findViewById(R.id.search)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listener.onDialogSearch(query);
                dismiss();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchSuggest(newText);
                return false;
            }
        });

    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public void onDestroy() {
        myView = null;
        super.onDestroy();
    }

}
