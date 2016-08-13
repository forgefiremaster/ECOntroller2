package com.example.lucas.econtroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 13/08/2016.
 */
public class AccessDataBase {
    private SQLiteDatabase db;
    public AccessDataBase(Context context){
        ContextoDeDados contextoDeDados = new ContextoDeDados(context);//Cria objeto para conexão
        contextoDeDados.getWritableDatabase();//Coloca em modo de escrita, e cria conexão
    }

    //INSERT
    public void inserir(AparelhosSimuladosDados aparelhosSimuladosDados){
        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();//Crinado uma content que conterar os valores que serão inseridos
            contentValues.put("nome", aparelhosSimuladosDados.getNome());
            contentValues.put("consumoEmWats", aparelhosSimuladosDados.getConsumoEmWats());
            contentValues.put("semanasLigados", aparelhosSimuladosDados.getSemanasLigados());
            contentValues.put("diasLigados", aparelhosSimuladosDados.getDiasLigados());
            contentValues.put("horasLigados", aparelhosSimuladosDados.getHorasLigados());

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
    public void atualizar(AparelhosSimuladosDados aparelhosSimuladosDados){
        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();//Crinado uma content que conterar os valores que serão inseridos
            contentValues.put("nome", aparelhosSimuladosDados.getNome());
            contentValues.put("consumoEmWats", aparelhosSimuladosDados.getConsumoEmWats());
            contentValues.put("semanasLigados", aparelhosSimuladosDados.getSemanasLigados());
            contentValues.put("diasLigados", aparelhosSimuladosDados.getDiasLigados());
            contentValues.put("horasLigados", aparelhosSimuladosDados.getHorasLigados());

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
    public List<AparelhosSimuladosDados> buscar(){
        List <AparelhosSimuladosDados> list = new ArrayList<AparelhosSimuladosDados>();
        String[] colunasProcuradas = new String[]{"_id","nome", "consumo_em_wats", "semanas_ligadas", "dias_ligados", "horas_ligados"};
        db.beginTransaction();

        //A busca me devolve um cursor
        //Procurando (databasname, Colunas, Clasa where, parametros da where, groupBy, having, orderBy)
        Cursor cursor = db.query("ECOntrollerBaseDeDados", colunasProcuradas, null, null, null, null, "nome ASC" );//cursor é tipo um RecordSet

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                AparelhosSimuladosDados aparelhosSimuladosDados = new AparelhosSimuladosDados();
                aparelhosSimuladosDados.setId(cursor.getString(0));
                aparelhosSimuladosDados.setNome(cursor.getString(1));
                aparelhosSimuladosDados.setConsumoEmWats(cursor.getString(2));
                aparelhosSimuladosDados.setSemanasLigados(cursor.getString(3));
                aparelhosSimuladosDados.setDiasLigados(cursor.getString(4));
                aparelhosSimuladosDados.setHorasLigados(cursor.getString(5));
                list.add(aparelhosSimuladosDados);
            }while (cursor.moveToNext());
        }
        return list;
    }



}
