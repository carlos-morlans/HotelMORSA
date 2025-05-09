package Model;

import java.sql.Time;
import java.util.Date;

public class RegistroEmpleado {
    private int registroID;
    private String empleadoDni;
    private String tipo; 
    private Time hora;
    private Date fechaRegistro;

    public RegistroEmpleado() {
    }

    public RegistroEmpleado(int registroID, String empleadoDni, String tipo, Time hora, Date fechaRegistro) {
        this.registroID = registroID;
        this.empleadoDni = empleadoDni;
        this.tipo = tipo;
        this.hora = hora;
        this.fechaRegistro = fechaRegistro;
    }

    public int getRegistroID() {
        return registroID;
    }

    public void setRegistroID(int registroID) {
        this.registroID = registroID;
    }

    public String getEmpleadoDni() {
        return empleadoDni;
    }

    public void setEmpleadoDni(String empleadoDni) {
        this.empleadoDni = empleadoDni;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "RegistroEmpleado{" + "registroID=" + registroID + ", empleadoDni='" + empleadoDni + '\'' + ", tipo='" + tipo + '\'' +  ", hora=" + hora + ", fechaRegistro=" + fechaRegistro +'}';
    }
}