package me.trabalho.poo.modelo.acomodacao;

public class Suite extends Acomodacao {

    private boolean possuiHidromassagem;

    public Suite(int codigo, String identificacao, int capacidade, double valorDiaria) {
        super(codigo, identificacao, capacidade, valorDiaria);
    }

    @Override
    public double calcularValor() {
        return super.calcularValor();
    }
}
