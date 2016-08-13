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
 * Created by Lucas on 04/08/2016.
 */
public class ComparadorConsumoEmGrafico extends Fragment {
    BarChart barChart;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        barChart = (BarChart) view.findViewById( R.id.barChart );

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# Dados");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Janeiro");
        labels.add("Fevereiro");
        labels.add("Março");
        labels.add("Abril");
        labels.add("Maio");
        labels.add("Junho");


        BarData data = new BarData(labels, dataset);
        dataset.setColors( ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
        barChart.setTouchEnabled( true );
        barChart.setDragEnabled( false );
        barChart.setScaleEnabled( false );
        barChart.setDescription("Consumo");

        return view;
    }
}
