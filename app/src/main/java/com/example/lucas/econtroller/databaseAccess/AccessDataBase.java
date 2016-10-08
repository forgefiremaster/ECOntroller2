package com.example.lucas.econtroller.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.lucas.econtroller.fragments.ListarAparelhosSimuladosFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 13/08/2016.
 */
public class AccessDataBase {
    private SQLiteDatabase db;
    private ContextoDeDados contextoDeDados;

    public AccessDataBase(SQLiteDatabase db){
        this.db = db;
    }

    //INSERT
    public void inserir(AparelhosSimuladosDados aparelhosSimuladosDados, Context context){
        try{
            ContentValues contentValues = new ContentValues();//Crinado uma content que conterar os valores que serão inseridos
            contentValues.put("nome", aparelhosSimuladosDados.getNome());
            contentValues.put("consumo_em_wats", aparelhosSimuladosDados.getConsumoEmWats());
            contentValues.put("semanas_ligadas", aparelhosSimuladosDados.getSemanasLigados());
            contentValues.put("dias_ligados",aparelhosSimuladosDados.getDiasLigados());
            contentValues.put("horas_ligados", aparelhosSimuladosDados.getHorasLigados());

            //Inserindo (databasname, coluna de valores nulos, valores
            db.insertOrThrow("aparelhos_simulados", null, contentValues);


        }catch (Exception e){
            Log.e("Erro", e.toString());
        }

    }

    //UPDATE
    public void atualizar(AparelhosSimuladosDados aparelhosSimuladosDados){
        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();//Crinado uma content que conterar os valores que serão inseridos
            contentValues.put("nome", aparelhosSimuladosDados.getNome());
            contentValues.put("consumo_em_wats", aparelhosSimuladosDados.getConsumoEmWats());
            contentValues.put("semanas_ligadas", aparelhosSimuladosDados.getSemanasLigados());
            contentValues.put("dias_ligados", aparelhosSimuladosDados.getDiasLigados());
            contentValues.put("horas_ligados", aparelhosSimuladosDados.getHorasLigados());

            //Atualizando (databasname, valores, where, parametros da where)
            db.update("ECOntrollerBaseDeDados", contentValues, "_id = ?", new String[]{""+ aparelhosSimuladosDados.getId()});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erro", e.toString());
        }
        finally {
            db.endTransaction();
        }
    }

    //DELETAR
    public void deletar(AparelhosSimuladosDados aparelhosSimuladosDados){
        db.beginTransaction();
        try{
            //Deletando (databasname, where, parametros da where)
            db.delete("ECOntrollerBaseDeDados", "_id = ?", new String[]{""+ aparelhosSimuladosDados.getId()});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erro", e.toString());
        }
        finally {
            db.endTransaction();
        }
    }

    //PROCURAR
    public ArrayAdapter<String> buscaDados(Context context){
        ArrayAdapter<String> adpBusca = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        String nome;

        //Armazena os registros que foram consultados na classe Cursor; (como se fosse um Recorset)
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                nome = cursor.getString(1);
                adpBusca.add(nome);
            }while (cursor.moveToNext());
        }else{
            Toast.makeText(context, "Não achei nada!!.", Toast.LENGTH_LONG).show();
        }

        return adpBusca;
    }
}
