package Model;
public class Empleados {
    
    String empleadoDni;
    String nombre;
    String apellido;
    String puesto; // Limpieza, Camarero, Recepcion, Botones
    String email;
    String telefono; 
    String jornada;
    int horasExtra;

    public Empleados(String empleadoDni, String nombre, String apellido, String puesto, String email, String telefono, String jornada, int horasExtra) {
        this.empleadoDni = empleadoDni;
        this.nombre = nombre; 
        this.apellido = apellido;
        this.puesto = puesto;
        this.email = email; 
        this.telefono = telefono;
        this.jornada = jornada;
        this.horasExtra = horasExtra;
    }

    
    // Getters y Setters
    public String getEmpleadoDni(){
        return empleadoDni;
    }
    public void setEmpleadoDni(String empleadoDni){
        this.empleadoDni = empleadoDni;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApellido(){
        return apellido;
    }
    public void setApellidos(String apellido){
        this.apellido = apellido;
    }

    public String getPuesto(){
        return puesto;
    }
    public void setPuesto(String puesto){
        this.puesto = puesto; 
    }
    
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getJornada(){
        return jornada;
    }

    public int getHorasExtra(){
        return horasExtra;
    }
    public void setHorasExtra(int horasExtra){
        this.horasExtra = horasExtra;
    }

    

    @Override 
    public String toString() {
        return String.format(
            "Empleado[DNI: %s, Nombre: %s, Apellido: %s, Puesto: %s, Email: %s, Teléfono: %s, Jornada: %s, Horas Extra: %d]",
            empleadoDni, nombre, apellido, puesto, email, telefono, jornada, horasExtra
        );
    }


    
}