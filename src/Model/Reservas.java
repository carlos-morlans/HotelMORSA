package model;

import java.sql.Timestamp;
import java.util.Date;

public class Reservas {
    int reservaID;
    String clienteDni;
    int numeroHabitacion;
    Date fechaEntrada;
    Date fechaSalida;
    int numeroAdultos;
    int numeroNinos;
    Timestamp fechaReserva; 
    String estadoReserva;
    double precioTotal;
    Date fechaCancelacion;
    String motivoCancelacion;

    public Reservas(int reservaID, String clienteDni, int numeroHabitacion, Date fechaEntrada, Date fechaSalida, int numeroAdultos, int numeroNinos, Timestamp fechaReserva, String estadoReserva, double precioTotal, Date fechaCancelacion, String motivoCancelacion){
        this.reservaID = reservaID;
        this.clienteDni = clienteDni;
        this.numeroHabitacion = numeroHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numeroAdultos = numeroAdultos;
        this.numeroNinos = numeroNinos;
        this.fechaReserva = fechaReserva;

    }
}
