package com.example.lucas.econtroller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lucas.econtroller.AccessDataBase;
import com.example.lucas.econtroller.AparelhosSimuladosDados;
import com.example.lucas.econtroller.R;

/**
 * Created by Lucas on 23/07/2016.
 */
public class AdicionarAparelhosSimuladosFragment extends Fragment {
    Spinner spinnerSemanas, spinnerDias, spinnerHoras;
    EditText nome, consumo_watts;
    ArrayAdapter dias, semanas, horas;
    Button cadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adicionar_aparelhos_simulados_fragment, container, false);
        spinnerSemanas = (Spinner) view.findViewById(R.id.semanas);
        spinnerDias = (Spinner) view.findViewById(R.id.dias);
        spinnerHoras = (Spinner) view.findViewById(R.id.horas);
        nome = (EditText) view.findViewById(R.id.nome);
        consumo_watts = (EditText) view.findViewById(R.id.consumo_watts);
        cadastrar = (Button) view.findViewById(R.id.cadastrar);
        dias = ArrayAdapter.createFromResource(getContext(),R.array.dias, android.R.layout.simple_dropdown_item_1line);
        spinnerDias.setAdapter(dias);
        horas = ArrayAdapter.createFromResource(getContext(),R.array.horas, android.R.layout.simple_dropdown_item_1line);
        spinnerHoras.setAdapter(horas);
        semanas = ArrayAdapter.createFromResource(getContext(),R.array.semanas, android.R.layout.simple_dropdown_item_1line);
        spinnerSemanas.setAdapter(semanas);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarNovoAparelho();
            }
        });


        return view;
    }

    private void cadastrarNovoAparelho(){
        AparelhosSimuladosDados aparelhosSimuladosDados = new AparelhosSimuladosDados();

        if(nome.getText().toString().equals("")){
            Toast.makeText(getContext(), "Coloque um nome para seu Aparelho.", Toast.LENGTH_LONG).show();
        }else if(consumo_watts.getText().toString().equals("")){
            Toast.makeText(getContext(), "Coloque o consumo de seu aparelho.", Toast.LENGTH_LONG).show();
        }else{
            aparelhosSimuladosDados.setNome(nome.getText().toString());
            aparelhosSimuladosDados.setConsumoEmWats(consumo_watts.getText().toString());
            aparelhosSimuladosDados.setSemanasLigados(semanas.toString());
            aparelhosSimuladosDados.setSemanasLigados(dias.toString());
            aparelhosSimuladosDados.setSemanasLigados(horas.toString());
            executarCadastroDeAparelhos(aparelhosSimuladosDados);
        }
    }

    private void executarCadastroDeAparelhos(AparelhosSimuladosDados aparelhosSimuladosDados){
        AccessDataBase accessDataBase = new AccessDataBase(getContext());
        accessDataBase.inserir(aparelhosSimuladosDados);
    }
}

