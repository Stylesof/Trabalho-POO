package me.trabalho.poo.modelo.acomodacao;

public class Chale extends Acomodacao {

    private boolean possuiCozinha;

    public Chale(int codigo, String identificacao, int capacidade, double valorDiaria) {
        super(codigo, identificacao, capacidade, valorDiaria);
    }

    @Override
    public double calcularValor() {
        return super.calcularValor();
    }
}
