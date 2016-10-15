package com.example.lucas.econtroller.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lucas.econtroller.R;
import com.example.lucas.econtroller.controllers.AparelhosEletricos;
import com.example.lucas.econtroller.databaseAccess.AccessDataBase;
import com.example.lucas.econtroller.databaseAccess.ContextoDeDados;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Lucas on 23/07/2016.
 */
public class GraficoSimularDeaparelhoFragment extends Fragment {
    BarChart barChart;
    private int quantidadeDeAparelhosCadastrados;
    SQLiteDatabase db;
    AccessDataBase accessDataBase;
    AparelhosEletricos aparelhosEletricos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.grafico_simulado_layout_fragment, container, false);
        barChart = (BarChart) view.findViewById(R.id.graficoEmBarra);
        if(verificarSeExisteAparelhosCadastrados()){
            criaGraficoEmBarras();
        }else{

        }
        return view;
    }

    public boolean verificarSeExisteAparelhosCadastrados(){
        ContextoDeDados contextoDeDados = new ContextoDeDados(getContext());
        db = contextoDeDados.getWritableDatabase();
        accessDataBase = new AccessDataBase(db);
        quantidadeDeAparelhosCadastrados = accessDataBase.retornaQuantidadeDeAparelhosCadastrados(getContext());
        if(quantidadeDeAparelhosCadastrados > 0){
            db.close();
            return true;
        }else{
            db.close();
            return false;
        }
    }

    public void criaGraficoEmBarras(){
        aparelhosEletricos = new AparelhosEletricos();
        ContextoDeDados contextoDeDados = new ContextoDeDados(getContext());
        db = contextoDeDados.getWritableDatabase();
        accessDataBase = new AccessDataBase(db);
        quantidadeDeAparelhosCadastrados = accessDataBase.retornaQuantidadeDeAparelhosCadastrados(getContext());
        ArrayList<String> nomes_aparelhos = accessDataBase.getNomes_aparelhos(getContext());
        ArrayList<String> semanas_aparelhos = accessDataBase.getSemanas_aparelhos(getContext());
        ArrayList<String> dias_aparelhos = accessDataBase.getDias_aparelhos(getContext());
        ArrayList<String> horas_aparelhos = accessDataBase.getHoras_aparelhos(getContext());
        ArrayList<String> watts_aparelhos = accessDataBase.getWatts_aparelhos(getContext());

        ArrayList<BarEntry> entrada = new ArrayList<>();
        BarDataSet barDataSet = new BarDataSet(entrada,"# Dados");
        ArrayList<String> labels = new ArrayList<>();
        Float valor;
        for(int i = 0; i < quantidadeDeAparelhosCadastrados; i++){
            valor = (float) aparelhosEletricos.calculGastoComAparelhoEmKiloWattsHoraNoMes(
                    Integer.parseInt(semanas_aparelhos.get(i)),
                    Integer.parseInt(dias_aparelhos.get(i)),
                    Integer.parseInt(horas_aparelhos.get(i)),
                    Double.parseDouble(watts_aparelhos.get(i)),
                    0.59
            );
            entrada.add(new BarEntry(valor, i, 1));
            labels.add(nomes_aparelhos.get(i) );
        }
        //entrada.add(new BarEntry(4f,0));

        BarData barData = new BarData(labels, barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
        barChart.setTouchEnabled( true );
        barChart.setDragEnabled( true );
        barChart.setScaleEnabled( true );
        barChart.setDescription("Consumo");
    }


}

