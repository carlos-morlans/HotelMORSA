package Model;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    
    private int numero;
    private String tipo;  // Individual, Doble, Suite, Familiar 
    
    private double precioPorNoche;
    private boolean ocupada;
    // Atributos de cliente asociado
    private Cliente clienteAsignado;
    private String fechaCheckIn;
    private String fechaCheckOut;

    public Habitacion(int numero, String tipo, double precioPorNoche, String fechaCheckIn, String fechaCheckOut) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.ocupada = false;
        this.clienteAsignado = null;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
    }

    //Asigna un cliente a la habitación
    public boolean asignarCliente(Cliente cliente, String fechaIn, String fechaOut) {
        if (!ocupada) {
            this.clienteAsignado = cliente;
            this.fechaCheckIn = fechaIn;
            this.fechaCheckOut = fechaOut;
            this.ocupada = true;
            return true;
        }
        return false;
    }


    //Libera la habitación (check-out)
    public void liberarHabitacion() {
        this.clienteAsignado = null;
        this.ocupada = false;
        this.fechaCheckIn = null;
        this.fechaCheckOut = null;
    }

    // --- GETTERS Y SETTERS ---
    
    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public int getPlanta() {
        return planta;
    }

    public Cliente getClienteAsignado() {
        return clienteAsignado;
    }

    @Override
    public String toString() {
        return String.format(
            "[%d] %s (Planta %d) - %s",
            numero, tipo, planta,
            ocupada ? "Ocupada por " + clienteAsignado.getNombre() : "Disponible"
        );
    }
}
