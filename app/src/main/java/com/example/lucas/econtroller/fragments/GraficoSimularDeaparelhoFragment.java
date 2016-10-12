package com.example.lucas.econtroller.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lucas.econtroller.R;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.grafico_simulado_layout_fragment, container, false);
        barChart = (BarChart) view.findViewById(R.id.graficoEmBarra);

        ArrayList<BarEntry> entrada = new ArrayList<>();
        entrada.add(new BarEntry(4f,0));
        entrada.add(new BarEntry(8f,1));
        BarDataSet barDataSet = new BarDataSet(entrada,"# Dados");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Primeiro");
        labels.add("Segundo");

        BarData barData = new BarData(labels, barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
        barChart.setTouchEnabled( true );
        barChart.setDragEnabled( true );
        barChart.setScaleEnabled( true );
        barChart.setDescription("Consumo");

        return view;
    }
}

