package me.trabalho.poo.modelo;

import java.time.LocalDateTime;

public class Consumo implements Faturavel {

    private String descricao;
    private double valor;
    private LocalDateTime data;

    public Consumo(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    @Override
    public double calcularValor() {
        return valor;
    }
}
