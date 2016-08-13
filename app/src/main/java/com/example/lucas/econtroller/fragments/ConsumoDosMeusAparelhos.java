package com.example.lucas.econtroller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucas.econtroller.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Lucas on 04/08/2016.
 */
public class ConsumoDosMeusAparelhos extends Fragment {
    PieChart pieChart;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.aparelhos_consumo_grafico_fragment, container, false);
        pieChart = (PieChart) view.findViewById( R.id.pieChart );

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));


        PieDataSet dataset = new PieDataSet(entries, "#Dados");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Geladeira");
        labels.add("TV");
        labels.add("Ar-Condicionado");
        labels.add("Video Game");
        labels.add("Lâmpadas da Sala");
        labels.add("Lâmpadas do Quarto");

        PieData data = new PieData(labels, dataset); // initialize Piedata
        dataset.setColors( ColorTemplate.COLORFUL_COLORS);
        pieChart.animateY(2750);
        pieChart.animateX(3000);
        pieChart.setTouchEnabled( true );
        pieChart.setData(data);
        pieChart.setDescription("Aparelhos");

        return view;
    }
}
