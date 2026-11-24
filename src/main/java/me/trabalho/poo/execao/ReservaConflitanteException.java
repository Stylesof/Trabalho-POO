package me.trabalho.poo.execao;

public class ReservaConflitanteException extends Exception {
    @Override
    public String getMessage() {
        return "Conflito de reservas!";
    }
}
