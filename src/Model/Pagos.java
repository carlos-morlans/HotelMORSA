package Model;

import java.security.Timestamp;
import java.util.Date;

public class Pagos {
    int pagoID;
    int reservaID;
    Timestamp fechaPago;
    double monto;
    String metodoPago;
    Date fechaReembolso;
    String motivoReembolso;

    public Pagos(int pagoID, int reservaID, Timestamp fechaPago, double monto, String metodoPago, Date fechaReembolso, String motivoReembolso) {
        this.pagoID = pagoID;
        this.reservaID = reservaID;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaReembolso = fechaReembolso;
        this.motivoReembolso = motivoReembolso;
    }


    //Getters y Setters

    public int getPagoID() {
        return pagoID;
    }
    public void setPagoID(int pagoID) {
        this.pagoID = pagoID;
    }

    public int getReservaID() {
        return reservaID;
    }
    public void setReservaID(int reservaID) {
        this.reservaID = reservaID;
    }

    public Timestamp getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFechaReembolso(){
        return fechaReembolso;
    }
    public void setFechaReembolso(Date fechaReembolso){
        this.fechaReembolso = fechaReembolso;
    }

    public String getMotivoReembolso(){
        return motivoReembolso;
    }
    public void setMotivoReembolso(String motivoReembolso) {
        this.motivoReembolso = motivoReembolso;
    }


    @Override
    public String toString() {
        return String.format(
            "Pago {ID: %d | Reserva: %d | Fecha: %s | Monto: %.2f€ | Método: %s | Reembolso: %s}",
            pagoID, reservaID,fechaPago,monto,metodoPago,fechaReembolso
        );
    }
    
}
