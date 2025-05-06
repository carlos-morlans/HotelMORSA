package Model;
public class Cliente {
        private String nombre;
        private boolean conDesayuno;
        private int numeroHabitacion;
        private String numeroMatricula;

        public Cliente(String nombre, boolean conDesayuno, int numeroHabitacion, String numeroMatricula) {
            this.nombre = nombre;
            this.conDesayuno = conDesayuno;
            this.numeroHabitacion = numeroHabitacion;
            this.numeroMatricula = numeroMatricula;
        }
        //Metodo para registrar un vehiculo en el garaje 
        public void registrarVehiculo(Garaje garaje, String matricula) {
            
            // Validar que tenga habitación asignada
            if (this.numeroHabitacion <= 0) {
                throw new IllegalStateException("El cliente no tiene habitación asignada");
            }
            
            if (garaje.registrarVehiculo(matricula, this.numeroHabitacion)) {
                this.numeroMatricula = matricula;
                return true;
            }
            return false;
        }

        //Metodo para liberar plaza de Garaje
        public void liberarVehiculo(Garaje garaje) {
            if (numeroMatricula != null) {
                garaje.retirarVehiculo(this.numeroHabitacion);
                this.numeroMatricula = null;
            }
        }

        public String getNombre(){
            return this.nombre;
        }
    
        @Override
        public String toString() {
            return nombre + " - Desayuno: " + (conDesayuno ? "Sí" : "No");
        }
    
}
