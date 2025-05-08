import View.ClienteView;
import View.EmpleadosView;
import View.EventoView;
import View.GarajeView;
import View.HabitacionesView;
import View.PagosView;
import View.ReservasView;
import java.util.InputMismatchException;
import java.util.Scanner;
 
public class App {
    public static void main(String[] args) {
 
        ClienteView cliente = new ClienteView();
        EmpleadosView empleado = new EmpleadosView();
        EventoView eventos = new EventoView();
        GarajeView garaje = new GarajeView();
        HabitacionesView habitacion = new HabitacionesView();
        PagosView pagos = new PagosView();
        ReservasView reservas = new ReservasView();
 
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
 
        System.out.println("=".repeat(25));
        System.out.println("Bienvenido al hotel MORSA");
        System.out.println("=".repeat(25));
        
        do {
            System.out.println("\n" + "=".repeat(25));
            System.out.println("** Menú Principal **");
            System.out.println("=".repeat(25));
            System.out.println("1. Clientes");
            System.out.println("2. Empleados");
            System.out.println("3. Eventos");
            System.out.println("4. Garaje");
            System.out.println("5. Habitaciones");
            System.out.println("6. Pagos");
            System.out.println("7. Reservas");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
 
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next(); // Limpiar el scanner
                opcion = -1;    // Para que el bucle continúe
                continue;
            }
            scanner.nextLine(); // Consumir la nueva línea pendiente
            System.out.println("-".repeat(30));
 
            switch (opcion) {
                case 1 -> cliente.menuCliente();
                case 2 -> empleado.menuEmpleado();
                case 3 -> eventos.menuEventos();
                case 4 -> garaje.mostrarMenuGaraje();
                case 5 -> habitacion.gestionarMenuHabitaciones();
                case 6 -> pagos.menuPagos();
                case 7 -> reservas.menuReservas();
                case 8 -> System.out.println("Saliendo del programa. ¡Gracias por su visita!");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 8);
 
        scanner.close(); // Cerrar el scanner al finalizar el programa
    }
}