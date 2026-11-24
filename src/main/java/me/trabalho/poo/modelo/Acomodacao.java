package me.trabalho.poo.modelo;

public abstract class Acomodacao {

    private int codigo;
    private String identificacao;
    private double valorDiaria;

    public double calcularValor() {
        return valorDiaria;
    }
}
