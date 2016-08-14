package com.example.lucas.econtroller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucas.econtroller.R;

/**
 * Created by Lucas on 23/07/2016.
 */
public class ListarAparelhosSimuladosFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listar_aparelhos_simulados_fragment, container, false);
        return view;
    }
}
