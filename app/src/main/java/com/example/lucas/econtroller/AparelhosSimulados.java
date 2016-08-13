package com.example.lucas.econtroller;

/**
 * Created by Lucas on 13/08/2016.
 */
public class AparelhosSimulados {
    private static String nome = "", consumoEmWats = "", semanasLigados = "", diasLigados = "", horasLigados = "", id = "";
    private void setId(String id){
        this.id = id;
    }
    private void setNome(String nome){
        this.nome = nome;
    }
    private void setConsumoEmWats(String consumoEmWats){
        this.consumoEmWats = consumoEmWats;
    }
    private void setSemanasLigados(String semanasLigados){
        this.semanasLigados = semanasLigados;
    }
    private void setDiasLigados(String diasLigados){
        this.diasLigados = diasLigados;
    }
    private void setHorasLigados(String horasLigados){
        this.horasLigados = horasLigados;
    }



    public String getNome(){
        return nome;
    }
    public String getConsumoEmWats(){
        return consumoEmWats;
    }

    public String getSemanasLigados(){
        return semanasLigados;
    }

    public String getDiasLigados(){
        return diasLigados;
    }
    public String getHorasLigados(){
        return horasLigados;
    }

    public String getId(){
        return id;
    }

}
