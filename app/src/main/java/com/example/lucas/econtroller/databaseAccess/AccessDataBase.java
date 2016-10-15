package com.example.lucas.econtroller.databaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.lucas.econtroller.controllers.AparelhosSimuladosDados;

import java.util.ArrayList;

/**
 * Created by Lucas on 13/08/2016.
 */
public class AccessDataBase {
    private SQLiteDatabase db;
    private ContextoDeDados contextoDeDados;
    private ArrayList<Integer> id_aparelhos;
    private ArrayAdapter< String> adpBusca;
    private ArrayList<String> nomes_aparelhos;
    private ArrayList<String> semanas_aparelhos;
    private ArrayList<String> dias_aparelhos;
    private ArrayList<String> horas_aparelhos;
    private ArrayList<String> watts_aparelhos;

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
            db.update("aparelhos_simulados", contentValues, "_id = ?", new String[]{""+ aparelhosSimuladosDados.getId()});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erro", e.toString());
        }
        finally {
            db.endTransaction();
        }
    }

    //DELETAR
    public boolean deletar(AparelhosSimuladosDados aparelhosSimuladosDados){
        boolean retorno = false;
        try{
            //Deletando (databasname, where, parametros da where)
            Log.d("ADebugTag", "Value: " + aparelhosSimuladosDados.getId());
            db.delete("aparelhos_simulados", "_id = ?", new String[]{""+ aparelhosSimuladosDados.getId()});
            retorno = true;
        }catch (Exception e){
            Log.e("Erro", e.toString());
            retorno = false;
        }
        return retorno;
    }

    //PROCURAR
    public ArrayAdapter buscaDados(Context context){
        adpBusca = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        String nome;
        String consumoEmWats;

        //Armazena os registros que foram consultados na classe Cursor; (como se fosse um Recorset)
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                nome = cursor.getString(1);
                consumoEmWats = cursor.getString(2);
                adpBusca.add(nome + " - " + consumoEmWats);
            }while (cursor.moveToNext());
        }else{
            Toast.makeText(context, "Não achei nada!!.", Toast.LENGTH_LONG).show();
        }
        cursor.close();
        return adpBusca;
    }

    public ArrayList<Integer> getIdsDosAparelhos(Context context){
        id_aparelhos = new ArrayList<>();
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                id_aparelhos.add(Integer.parseInt(cursor.getString(0)));
                Log.d("ADebugTag", "Value: " + cursor.getString(0));
            }while(cursor.moveToNext());
        }else{
            Toast.makeText(context, "Não achei nada!!.", Toast.LENGTH_LONG).show();
        }
        cursor.close();
        return id_aparelhos;
    }

    public int retornaQuantidadeDeAparelhosCadastrados(Context context){
        int quantidade = 0;
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if(cursor.getCount() > 0){
            quantidade = cursor.getCount();
        }
        cursor.close();
        return  quantidade;
    }

    public ArrayList<String> getNomes_aparelhos(Context context){
        nomes_aparelhos = new ArrayList<>();
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                nomes_aparelhos.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return nomes_aparelhos;
    }

    public ArrayList<String> getWatts_aparelhos(Context context){
        watts_aparelhos = new ArrayList<>();
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                watts_aparelhos.add(cursor.getString(2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return watts_aparelhos;
    }

    public ArrayList<String> getSemanas_aparelhos(Context context){
        semanas_aparelhos = new ArrayList<>();
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                semanas_aparelhos.add(cursor.getString(3));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return semanas_aparelhos;
    }

    public ArrayList<String> getDias_aparelhos(Context context){
        dias_aparelhos = new ArrayList<>();
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                dias_aparelhos.add(cursor.getString(4));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dias_aparelhos;
    }

    public ArrayList<String> getHoras_aparelhos(Context context){
        horas_aparelhos = new ArrayList<>();
        Cursor cursor = db.query("aparelhos_simulados", null, null ,null, null, null, null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Log.d("ADebugTag", "Value: " + cursor.getString(5));
                horas_aparelhos.add(cursor.getString(5));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return horas_aparelhos;
    }






}
