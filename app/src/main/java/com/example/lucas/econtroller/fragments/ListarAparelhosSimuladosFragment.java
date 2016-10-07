package com.example.lucas.econtroller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.lucas.econtroller.databaseActivity.AccessDataBase;
import com.example.lucas.econtroller.databaseActivity.AparelhosSimuladosDados;
import com.example.lucas.econtroller.R;

import java.util.List;

/**
 * Created by Lucas on 23/07/2016.
 */
public class ListarAparelhosSimuladosFragment extends Fragment {
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listar_aparelhos_simulados_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.lista);
    
        listarAparelhos();
        
        return view;
    }

    private void listarAparelhos() {
        AccessDataBase accessDataBase = new AccessDataBase(getContext());
        List<AparelhosSimuladosDados> list = accessDataBase.buscar();
        ListAdapter listAdapter = new ArrayAdapter<AparelhosSimuladosDados>(getContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(listAdapter);
    }
}
