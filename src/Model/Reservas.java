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
        return this.clienteDni;
    }

    public void setClienteDni(String clienteDni) {
        this.clienteDni = clienteDni;
    }

    public int getNumeroHabitacion() {
        return this.numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public LocalDate getFechaEntrada() {
        return this.fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return this.fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumeroAdultos() {
        return this.numeroAdultos;
    }

    public void setNumeroAdultos(int numeroAdultos) {
        this.numeroAdultos = numeroAdultos;
    }

    public int getNumeroNinos() {
        return this.numeroNinos;
    }

    public void setNumeroNinos(int numeroNinos) {
        this.numeroNinos = numeroNinos;
    }

    public LocalDateTime getFechaReserva() {
        return this.fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstadoReserva() {
        return this.estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public double getPrecioTotal() {
        return this.precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDate getFechaCancelacion() {
        return this.fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getMotivoCancelacion() {
        return this.motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reserva [");
        sb.append("\n  DNI Cliente: ").append(this.clienteDni);
        sb.append("\n  Habitación: ").append(this.numeroHabitacion);
        sb.append("\n  Fechas: Entrada=").append(this.fechaEntrada)
        .append(" - Salida=").append(this.fechaSalida)
        .append(" (").append(ChronoUnit.DAYS.between(this.fechaEntrada, this.fechaSalida)).append(" noches)");
        sb.append("\n  Huéspedes: ").append(this.numeroAdultos).append(" adultos, ")
        .append(this.numeroNinos).append(" niños");
        sb.append("\n  Estado: ").append(this.estadoReserva != null ? this.estadoReserva : "No especificado");
        sb.append("\n  Fecha reserva: ").append(this.fechaReserva);
        
        if (this.precioTotal > 0) {
            sb.append("\n  Precio total: ").append(String.format("%.2f", this.precioTotal)).append(" €");
        }
        
        if (this.fechaCancelacion != null) {
            sb.append("\n  Cancelación: ").append(this.fechaCancelacion)
            .append(" - Motivo: ").append(this.motivoCancelacion != null ? this.motivoCancelacion : "No especificado");
        }
        
        sb.append("\n]");
        return sb.toString();
    }


}
