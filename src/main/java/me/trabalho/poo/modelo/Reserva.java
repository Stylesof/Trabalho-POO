package me.trabalho.poo.modelo;

import me.trabalho.poo.execao.ReservaConflitanteException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Reserva extends Conta {

    public static Map<Integer, Reserva> Reservas = new HashMap<>();

    private int codigo;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String status;

    private Reserva() {
        this.checkIn = LocalDateTime.now();
        this.status = "Ativo!";
    }

    public Reserva(int codigo) {
        try {
            if (Reservas.containsKey(codigo)) {
                throw new ReservaConflitanteException();
            } else {
                Reservas.put(codigo, new Reserva());
                this.codigo = codigo;
            }
        } catch (ReservaConflitanteException rc) {
            System.out.println(rc.getMessage());
        }
    }

    public void fecharReserva() {
        this.status = "Nao ativo!";
        this.checkOut = LocalDateTime.now();
        Reservas.remove(codigo);

        this.fecharConta();
    }

}
