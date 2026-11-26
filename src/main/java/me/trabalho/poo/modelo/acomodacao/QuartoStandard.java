package me.trabalho.poo.modelo.acomodacao;

public class QuartoStandard extends Acomodacao {

    private boolean possuiArCondicionado;

    public QuartoStandard(int codigo, String identificacao, int capacidade, double valorDiaria) {
        super(codigo, identificacao, capacidade, valorDiaria);
    }

    @Override
    public double calcularValor() {
        return super.calcularValor();
    }
}
