package com.example.lucas.econtroller.fragments;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.lucas.econtroller.activitys.ADDaparelhosSimuladorActivity;
import com.example.lucas.econtroller.activitys.SimuladorActivity;
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
    ImageButton floatingButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listar_aparelhos_simulados_fragment, container, false);
        floatingButton = (ImageButton) view.findViewById(R.id.floatingButton);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            initLFloatingButtons();
        }

        listView = (ListView) view.findViewById(R.id.lista);
        listarAparelhos(getContext());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initLFloatingButtons() {
        final int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
        final ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, size, size);
            }
        };

        floatingButton.setOutlineProvider(viewOutlineProvider);
        floatingButton.setClipToOutline(true);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ADDaparelhosSimuladorActivity.class);
                startActivity(intent);
            }
        });
    }

}
