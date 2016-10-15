package com.example.lucas.econtroller.controllers;

/**
 * Created by Lucas on 15/10/2016.
 */

public class AparelhosEletricos {

    public double calculGastoComAparelhoEmKiloWattsHoraNoMes(int semanas, int dias, int horas, double watts, double kwhora){
        double valor = 0.0;
        double baseDeCalculo = watts / 1000;
        baseDeCalculo = horas * baseDeCalculo;
        baseDeCalculo = dias * baseDeCalculo;
        baseDeCalculo = semanas * baseDeCalculo;
        valor = baseDeCalculo * kwhora;
        return valor;
    }


}
