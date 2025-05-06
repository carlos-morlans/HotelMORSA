package model;
public class Habitaciones {
  
    int numeroHabitacion;
    String tipoHabitacion;
    int capacidad;
    double precioNoche;
    String estado;

    public Habitaciones(int numeroHabitacion, String tipoHabitacion, int capacidad,  double precioNoche, String estado) {

        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.estado = estado;
    }


    // --- GETTERS Y SETTERS ---
    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }
    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getTipoHabitacion(){
        return tipoHabitacion;
    }
    public void setTipoHabitacion(String tipoHabitacion){
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getCapacidad(){
        return capacidad;
    }
    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }
    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("Habitación[Número: %s, Tipo: %s, Precio: %.2f, Estado: %s]",
        numeroHabitacion, tipoHabitacion, precioNoche, estado);
    }
}
