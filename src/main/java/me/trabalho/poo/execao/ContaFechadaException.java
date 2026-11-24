package me.trabalho.poo.execao;

public class ContaFechadaException extends Exception {
    @Override
    public String getMessage() {
        return "A conta está fechada!";
    }
}
