package com.example.lucas.econtroller.fragments;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.lucas.econtroller.databaseAccess.AccessDataBase;
import com.example.lucas.econtroller.databaseAccess.AparelhosSimuladosDados;
import com.example.lucas.econtroller.R;
import com.example.lucas.econtroller.databaseAccess.ContextoDeDados;

import java.util.List;

/**
 * Created by Lucas on 23/07/2016.
 */
public class ListarAparelhosSimuladosFragment extends Fragment {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ContextoDeDados contextoDeDados;
    SQLiteDatabase db;
    AccessDataBase accessDataBase;
    PopupMenu popupMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listar_aparelhos_simulados_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.lista);
        listarAparelhos(getContext());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupMenu = new PopupMenu(getContext(), view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        return false;
                    }
                });
                popupMenu.inflate(R.menu.menu_popup_crud);
                popupMenu.show();
            }
        });
        return view;
    }

    public void listarAparelhos(Context context) {
        try{
            contextoDeDados = new ContextoDeDados(context);
            db = contextoDeDados.getWritableDatabase();
            accessDataBase = new AccessDataBase(db);
            arrayAdapter = accessDataBase.buscaDados(context);
            listView.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
        }catch (Exception e){
           Toast.makeText(context, "ERRO AO LISTAR OS APARELHOS.", Toast.LENGTH_LONG).show();
        }
    }
}
