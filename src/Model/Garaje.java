package Model;

public class Garaje{
    int numeroPlaza;
    String estado;

    public Garaje(int numeroPlaza, String estado) {
        this.numeroPlaza = numeroPlaza;
        this.estado = estado;
    }


    //Getters y Setters

    public int getNumeroPlaza() {
        return numeroPlaza;
    }
    public void setNumeroPlaza(int numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return String.format("Garaje[Plaza: %d, Estado: %s]", numeroPlaza, estado);
    }

}
