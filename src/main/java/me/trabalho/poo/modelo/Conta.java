package me.trabalho.poo.modelo;

import me.trabalho.poo.execao.ContaFechadaException;

public class Conta extends Consumo {

        private int codigo;
        private double valorTotal;
        private boolean fechada = false;

        public void consumir(Consumo consumo) {
            try {
                if (!fechada) {
                    valorTotal += consumo.calcularValor();
                } else {
                    throw new ContaFechadaException();
                }
            } catch (ContaFechadaException cf) {
                System.out.println(cf.getMessage());
            }
        }

        public double calcularTotal() {
            return valorTotal;
        }

        public void fecharConta() {
            try {
                if (!fechada) {
                    fechada = true;
                } else {
                    throw new ContaFechadaException();
                }
            } catch (ContaFechadaException cf) {
                System.out.println(cf.getMessage());
            }
        }

}
