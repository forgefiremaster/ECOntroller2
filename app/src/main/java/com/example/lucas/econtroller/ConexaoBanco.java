package com.example.lucas.econtroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Lucas on 13/08/2016.
 */
public class ConexaoBanco {
    private SQLiteDatabase db;
    public ConexaoBanco(Context context){
        ContextoDeDados contextoDeDados = new ContextoDeDados(context);//Cria objeto para conexão
        contextoDeDados.getWritableDatabase();//Coloca em modo de escrita, e cria conexão
    }

    //INSERT
    public void inserir(AparelhosSimulados aparelhosSimulados){
        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();//Crinado uma content que conterar os valores que serão inseridos
            contentValues.put("nome", aparelhosSimulados.getNome());
            contentValues.put("consumoEmWats", aparelhosSimulados.getConsumoEmWats());
            contentValues.put("semanasLigados", aparelhosSimulados.getSemanasLigados());
            contentValues.put("diasLigados", aparelhosSimulados.getDiasLigados());
            contentValues.put("horasLigados", aparelhosSimulados.getHorasLigados());

            //Inserindo (databasname, coluna de valores nulos, valores
            db.insert("ECOntrollerBaseDeDados", null, contentValues);
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erro", e.toString());
        }
        finally {
            db.endTransaction();
        }
    }

    //UPDATE
    public void atualizar(AparelhosSimulados aparelhosSimulados){
        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();//Crinado uma content que conterar os valores que serão inseridos
            contentValues.put("nome", aparelhosSimulados.getNome());
            contentValues.put("consumoEmWats", aparelhosSimulados.getConsumoEmWats());
            contentValues.put("semanasLigados", aparelhosSimulados.getSemanasLigados());
            contentValues.put("diasLigados", aparelhosSimulados.getDiasLigados());
            contentValues.put("horasLigados", aparelhosSimulados.getHorasLigados());

            //Atualizando (databasname, valores, where, parametros da where)
            db.update("ECOntrollerBaseDeDados", contentValues, "_id = ?", new String[]{""+aparelhosSimulados.getId()});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erro", e.toString());
        }
        finally {
            db.endTransaction();
        }
    }

    //DELETAR
    public void deletar(AparelhosSimulados aparelhosSimulados){
        db.beginTransaction();
        try{

            //Deletando (databasname, where, parametros da where)
            db.delete("ECOntrollerBaseDeDados", "_id = ?", new String[]{""+aparelhosSimulados.getId()});
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erro", e.toString());
        }
        finally {
            db.endTransaction();
        }
    }

    //PROCURAR , não estou utilizando
    public void procurar(){
        db.beginTransaction();
        try{
            //Minhas colunas da tabela que serão procuradas
            String[] colunasProcuradas = new String[]{"_id","nome", "consumo_em_wats", "semanas_ligadas", "dias_ligados", "horas_ligados"};
            //A busca me devolve um cursor
            //Procurando (databasname, Colunas, Clasa where, parametros da where, groupBy, having, orderBy)
            Cursor cursor = db.query("ECOntrollerBaseDeDados", colunasProcuradas, null, null, null, null, "nome ASC" );
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erro", e.toString());
        }
        finally {
            db.endTransaction();
        }
    }



}
