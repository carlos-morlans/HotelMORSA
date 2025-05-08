import java.util.Scanner;


import View.ClienteView;
import View.EmpleadosView;
import View.EventoView;
import View.GarajeView;
import View.HabitacionesView;
import View.PagosView;
import View.ReservasView;


public class App {
    public static void main(String[] args) {
        ClienteView cliente = new ClienteView();
        EmpleadosView empleado = new EmpleadosView();
        EventoView evento = new EventoView();
        GarajeView garaje = new
        Scanner sc = new Scanner(System.in);
        int opcion;
        System.out.println("Bienvenido al hotel MORSA creado por Carlos Morlans, Jose Fernando Martinez, Daniel Saz y Marcos Blánquez");
        System.out.println("¿A qué desea acceder?");

        do {
            System.out.println("1. Clientes");
            System.out.println( "2. Empleados");
            System.out.println("3. Eventos");
            System.out.println("4. Garaje");
            System.out.println("5. Habitaciones");
            System.out.println("6. Pagos");
            System.out.println("7. Reservas");
            System.out.println("8. Salir");

            opcion = sc.nextInt();

            switch(opcion) {

                case 1 -> {cliente.menuCliente();}


            }
        
            

        } while (opcion != 8);
        
    }
}
