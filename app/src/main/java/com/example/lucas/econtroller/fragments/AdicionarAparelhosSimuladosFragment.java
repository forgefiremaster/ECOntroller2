package com.example.lucas.econtroller.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lucas.econtroller.R;

/**
 * Created by Lucas on 23/07/2016.
 */
public class AdicionarAparelhosSimuladosFragment extends Fragment {
    Spinner spinnerSemanas, spinnerDias, spinnerHoras;
    ArrayAdapter dias, semanas, horas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adicionar_aparelhos_simulados_fragment, container, false);
        spinnerSemanas = (Spinner) view.findViewById(R.id.semanas);
        spinnerDias = (Spinner) view.findViewById(R.id.dias);
        spinnerHoras = (Spinner) view.findViewById(R.id.horas);

        dias = ArrayAdapter.createFromResource(getContext(),R.array.dias, android.R.layout.simple_dropdown_item_1line);
        spinnerDias.setAdapter(dias);
        horas = ArrayAdapter.createFromResource(getContext(),R.array.horas, android.R.layout.simple_dropdown_item_1line);
        spinnerHoras.setAdapter(horas);
        semanas = ArrayAdapter.createFromResource(getContext(),R.array.semanas, android.R.layout.simple_dropdown_item_1line);
        spinnerSemanas.setAdapter(semanas);

        return view;
    }
}

