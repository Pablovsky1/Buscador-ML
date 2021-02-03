package com.mercadolibre.products.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.mercadolibre.products.R;

public class SearchDialog extends DialogFragment {

    private String productDescription;
    private NoticeSearchDialog listener;
    private View myView;

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

        if (this.productDescription != null && !this.productDescription.isEmpty()){
            ((android.widget.SearchView)view.findViewById(R.id.search)).setQuery(this.productDescription,false);
        }

        ((android.widget.SearchView)view.findViewById(R.id.search)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Envia
                listener.onDialogSearch(query);
                dismiss();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Escribe

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
