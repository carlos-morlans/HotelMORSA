package model;
public class Clientes{
    String clienteDni;
    String nombre;
    String apellido;
    String email;
    String telefono;
    String direccion;
   
 
    public Clientes(String clienteDni, String nombre, String apellido, String email, String telefono, String direccion) {
        this.clienteDni = clienteDni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
 
    }

 
    // Getters y Setters
    public String getClienteDni() {
        return clienteDni;
    } 
    public void setClienteDni(String clienteDni) {
        this.clienteDni = clienteDni;
    }
 
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getApellidos() {
        return apellido;
    } 
    public void setApellidos(String apellido) {
        this.apellido = apellido;
    }
 
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
 
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
 
    @Override
    public String toString() {
    return String.format(
        "Cliente[DNI: %s, Nombre: %s, Apellido: %s, Email: %s, Teléfono: %s, Dirección: %s]",
        clienteDni, nombre, apellido, email, telefono, direccion
    );
    }
}