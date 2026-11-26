package me.trabalho.poo.modelo.acomodacao;

public abstract class Acomodacao {

    private final int codigo;
    private final String identificacao;
    private double valorDiaria;
    private final int capacidade;

    public Acomodacao(int codigo, String identificacao, int capacidade, double valorDiaria) {
        this.codigo = codigo;
        this.identificacao = identificacao;
        this.capacidade = capacidade;
        this.valorDiaria = valorDiaria;
    }

    public double calcularValor() {
        return valorDiaria;
    }
}
