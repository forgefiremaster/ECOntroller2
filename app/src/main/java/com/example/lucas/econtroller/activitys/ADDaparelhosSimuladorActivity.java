package com.example.lucas.econtroller.activitys;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.lucas.econtroller.R;
import com.example.lucas.econtroller.databaseAccess.AccessDataBase;
import com.example.lucas.econtroller.controllers.AparelhosSimuladosDados;
import com.example.lucas.econtroller.databaseAccess.ContextoDeDados;

public class ADDaparelhosSimuladorActivity extends AppCompatActivity {
    private Spinner spinnerSemanas, spinnerDias, spinnerHoras;
    private EditText nome, consumo_watts;
    private ContextoDeDados contextoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addaparelhos_simulador_activity);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerSemanas = (Spinner)findViewById(R.id.semanas);
        spinnerDias = (Spinner) findViewById(R.id.dias);
        spinnerHoras = (Spinner) findViewById(R.id.horas);
        nome = (EditText) findViewById(R.id.nome);
        consumo_watts = (EditText) findViewById(R.id.consumo_watts);
        Button cadastrar = (Button) findViewById(R.id.cadastrar);
        ArrayAdapter dias = ArrayAdapter.createFromResource(this, R.array.dias, android.R.layout.simple_dropdown_item_1line);
        spinnerDias.setAdapter(dias);
        ArrayAdapter horas = ArrayAdapter.createFromResource(this, R.array.horas, android.R.layout.simple_dropdown_item_1line);
        spinnerHoras.setAdapter(horas);
        ArrayAdapter semanas = ArrayAdapter.createFromResource(this, R.array.semanas, android.R.layout.simple_dropdown_item_1line);
        spinnerSemanas.setAdapter(semanas);
        if (cadastrar != null) {
            cadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cadastrarNovoAparelho();
                }
            });
        }
    }

    private void cadastrarNovoAparelho(){
        AparelhosSimuladosDados aparelhosSimuladosDados = new AparelhosSimuladosDados();
        if(nome.getText().toString().equals("")){
            Toast.makeText(this, "Coloque um nome para seu Aparelho.", Toast.LENGTH_LONG).show();
        }else if(consumo_watts.getText().toString().equals("")){
            Toast.makeText(this, "Coloque o consumo de seu aparelho.", Toast.LENGTH_LONG).show();
        }else{
            int semana = spinnerSemanas.getSelectedItemPosition() + 1;
            int dia = spinnerDias.getSelectedItemPosition() + 1;
            int hora = spinnerHoras.getSelectedItemPosition() + 1 ;
            aparelhosSimuladosDados.setNome(nome.getText().toString());
            aparelhosSimuladosDados.setConsumoEmWats(consumo_watts.getText().toString());
            aparelhosSimuladosDados.setSemanasLigados(String.valueOf(semana));
            aparelhosSimuladosDados.setDiasLigados(String.valueOf(dia));
            aparelhosSimuladosDados.setHorasLigados(String.valueOf(hora));

            executarCadastroDeAparelhos(aparelhosSimuladosDados, this);
        }
    }

    private void executarCadastroDeAparelhos(AparelhosSimuladosDados aparelhosSimuladosDados, Context context){
        contextoDeDados = new ContextoDeDados(context);
        SQLiteDatabase db = contextoDeDados.getWritableDatabase();
        AccessDataBase accessDataBase = new AccessDataBase(db);
        accessDataBase.inserir(aparelhosSimuladosDados, context);
    }

}
