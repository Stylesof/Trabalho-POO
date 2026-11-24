package me.trabalho.poo.execao;

public class DadosInvalidosException extends Exception {
    @Override
    public String getMessage() {
        return "Dados invalidos!";
    }
}
