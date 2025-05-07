package Model;

import java.sql.Time;
import java.sql.Date;

public class Eventos {
   int eventoID;
   String nombreEvento;
   Date fechaEvento;
   Time horaInicio;
   double precio;
   int capacidad;

   public Eventos(int eventoID, String nombreEvento, Date fechaEvento, Time horaInicio, double precio, int capacidad) {
      this.eventoID = eventoID;
      this.nombreEvento = nombreEvento;
      this.fechaEvento = fechaEvento;
      this.horaInicio = horaInicio;
      this.precio = precio;
      this.capacidad = capacidad;

   }


   //Getter y Setters

   public int getEventoID() {
      return eventoID;
   }
   public void setEventoID(int eventoID) {
      this.eventoID = eventoID;
   }

   public String getNombreEvento(){
      return nombreEvento;
   }
   public void setNombreEvento(String nombreEvento){
      this.nombreEvento = nombreEvento;
   }

   public Date getFechaEvento(){
      return fechaEvento;
   }
   public void setFechaEvento(Date fechaEvento){
      this.fechaEvento = fechaEvento;
   }

   public Time getHoraInicio(){
      return horaInicio;
   }
   public void setHoraInicio(Time horaInicio){
      this.horaInicio = horaInicio;
   }

   public double getPrecio(){
      return precio;
   }
   public void setPrecio(double precio){
      this.precio = precio;
   }

   public int getCapacidad(){
      return capacidad;
   }
   public void setCapacidad(int capacidad){
      this.capacidad = capacidad;   
   }


   @Override
   public String toString() {
      return String.format(
          "Evento[ID: %d, Nombre: %s, Fecha: %s, Hora: %s, Precio: %.2f€, Capacidad: %d]",
          eventoID, nombreEvento, fechaEvento, horaInicio, precio, capacidad
      );
  }

}
