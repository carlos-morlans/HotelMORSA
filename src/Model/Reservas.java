package Model;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Reservas {
    
    String clienteDni;
    int numeroHabitacion;
    LocalDate fechaEntrada;
    LocalDate fechaSalida;
    int numeroAdultos;
    int numeroNinos;
    LocalDateTime fechaReserva; 
    String estadoReserva;
    double precioTotal;
    LocalDate fechaCancelacion;
    String motivoCancelacion;

    public Reservas(String clienteDni, int numeroHabitacion, LocalDate fechaEntrada, LocalDate fechaSalida, int numeroAdultos, int numeroNinos){
        
        this.clienteDni = clienteDni;
        this.numeroHabitacion = numeroHabitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numeroAdultos = numeroAdultos;
        this.numeroNinos = numeroNinos;
        //this.estadoReserva = "Confirmada";
        this.motivoCancelacion= null;
        this.fechaCancelacion= null;
        this.fechaReserva= LocalDateTime.now();

    }

    public String getClienteDni() {
        return clienteDni;
    }

    public void setClienteDni(String clienteDni) {
        this.clienteDni = clienteDni;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumeroAdultos() {
        return numeroAdultos;
    }

    public void setNumeroAdultos(int numeroAdultos) {
        this.numeroAdultos = numeroAdultos;
    }

    public int getNumeroNinos() {
        return numeroNinos;
    }

    public void setNumeroNinos(int numeroNinos) {
        this.numeroNinos = numeroNinos;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva [");
        sb.append("\n  DNI Cliente: ").append(clienteDni);
        sb.append("\n  Habitación: ").append(numeroHabitacion);
        sb.append("\n  Fechas: Entrada=").append(fechaEntrada)
        .append(" - Salida=").append(fechaSalida)
        .append(" (").append(ChronoUnit.DAYS.between(fechaEntrada, fechaSalida)).append(" noches)");
        sb.append("\n  Huéspedes: ").append(numeroAdultos).append(" adultos, ")
        .append(numeroNinos).append(" niños");
        sb.append("\n  Estado: ").append(estadoReserva != null ? estadoReserva : "No especificado");
        sb.append("\n  Fecha reserva: ").append(fechaReserva);
        
        if (precioTotal > 0) {
            sb.append("\n  Precio total: ").append(String.format("%.2f", precioTotal)).append(" €");
        }
        
        if (fechaCancelacion != null) {
            sb.append("\n  Cancelación: ").append(fechaCancelacion)
            .append(" - Motivo: ").append(motivoCancelacion != null ? motivoCancelacion : "No especificado");
        }
        
        sb.append("\n]");
        return sb.toString();
    }


}
