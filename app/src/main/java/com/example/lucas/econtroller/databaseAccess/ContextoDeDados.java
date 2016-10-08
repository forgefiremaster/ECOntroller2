package com.example.lucas.econtroller.databaseAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lucas on 13/08/2016.
 */
public class ContextoDeDados extends SQLiteOpenHelper{

    //Nome da Base de Dados
    private  static final String DatabaseName = "ECOntrollerBaseDeDados";
    //Versão da base dados
    private static final int versioBD = 1;
    private static final String LOG_TAG = "ECOntrollerBaseDeDados";
    /** Mantém rastreamento do contexto que nós podemos carregar SQL */
    private final Context contexto;


    public ContextoDeDados(Context context) {
        super(context, DatabaseName, null, versioBD);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS aparelhos_simulados(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "consumo_em_wats TEXT NOT NULL," +
                "semanas_ligadas INTEGER NOT NULL," +
                "dias_ligados INTEGER NOT NULL," +
                "horas_ligados INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE aparelhos_simulados;");
        onCreate(db);
    }
}
