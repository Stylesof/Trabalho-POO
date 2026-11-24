package me.trabalho.poo.execao;

public class RegistroNaoEncontradoException extends Exception {
    @Override
    public String getMessage() {
        return "Registro nao encontrado!";
    }
}
