package com.example.lucas.econtroller;

/**
 * Created by Lucas on 13/08/2016.
 */
public class AparelhosSimuladosDados {
    private static String nome = "", consumoEmWats = "", semanasLigados = "", diasLigados = "", horasLigados = "", id = "";
    public void setId(String id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setConsumoEmWats(String consumoEmWats){
        this.consumoEmWats = consumoEmWats;
    }
    public void setSemanasLigados(String semanasLigados){
        this.semanasLigados = semanasLigados;
    }
    public void setDiasLigados(String diasLigados){
        this.diasLigados = diasLigados;
    }
    public void setHorasLigados(String horasLigados){
        this.horasLigados = horasLigados;
    }
    public String getNome(){
        return nome;
    }
    public String getConsumoEmWats(){return consumoEmWats;}
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
