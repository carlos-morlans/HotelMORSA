package View;

import Dao.ReservasDAO;
import Model.Reservas;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ReservasView {

    ReservasDAO reservasDAO = new ReservasDAO();

    Scanner sc = new Scanner(System.in);

    //Reservas reserva;

    HabitacionesView habitacion = new HabitacionesView();

    public void menuReservas() {

        int opcion;


        System.out.println("1. Crear Reserva");
        System.out.println("2. Actualizar Reserva");
        System.out.println("3. Cancelar Reserva");
        System.out.println("4. Consultar Reservas");
        System.out.println("5. Volver al menú principal");

        opcion = sc.nextInt();
        sc.nextLine(); 

        switch (opcion) {
            case 1-> { this.crearReserva(); }
            case 2-> { this.actualizarReserva(); }  
            case 3-> { this.cancelarReserva(); }
            case 4-> { this.consultarReservas(); }
            case 5-> { System.out.println("Volviendo al menú principal..."); }
            default -> { System.out.println("Opción no válida. Intente nuevamente."); }
        }
       

    }

    public void crearReserva() {
        System.out.println("Ingrese el DNI del cliente:");
        String dni = sc.nextLine();

        System.out.println("Elija el tipo de habitación que desea:");
        System.out.println("1. Individuale, doble, familiare  suit");
        habitacion.listarHabitaciones();



        System.out.println("Ingrese el número de habitación:");
        int numeroHabitacion = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese la fecha de entrada:");
        LocalDate fechaEntrada = this.fechaDia();
        System.out.println(fechaEntrada);

        System.out.println("Ingrese la fecha de salida:");
        LocalDate fechaSalida = this.fechaDia();
        System.out.println(fechaSalida);

        System.out.println("Ingrese el número de adultos:");
        int numeroAdultos = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el número de ninos:");
        int numeroNinos = sc.nextInt();
        sc.nextLine();

     

        Reservas reserva = new Reservas(dni, numeroHabitacion, fechaEntrada, fechaSalida, numeroAdultos, numeroNinos);
        
        reservasDAO.crearReserva(reserva);
    }

    public LocalDateTime fechaMinuto(){
        int year, month, day, hour, minute;
        System.out.println("Ingrese el ano:");
        year = sc.nextInt();
        System.out.println("Ingrese el mes:");
        month = sc.nextInt();
        System.out.println("Ingrese el día:");
        day = sc.nextInt();
        System.out.println("Ingrese la hora:");
        hour = sc.nextInt();
        System.out.println("Ingrese los minutos:");
        minute = sc.nextInt();
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public LocalDate fechaDia(){
        int year, month, day;
        System.out.println("Ingrese el ano:");
        year = sc.nextInt();
        System.out.println("Ingrese el mes:");
        month = sc.nextInt();
        System.out.println("Ingrese el día:");
        day = sc.nextInt();
        return LocalDate.of(year, month, day);
    }

    public void actualizarReserva() {
        System.out.println("Ingrese el ID de la reserva a actualizar:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nuevo DNI del cliente:");
        String dni = sc.nextLine();

        System.out.println("Ingrese el nuevo número de habitación:");
        int numeroHabitacion = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese la nueva fecha de entrada:");
        LocalDate fechaEntrada = this.fechaDia();

        System.out.println("Ingrese la nueva fecha de salida:");
        LocalDate fechaSalida = this.fechaDia();

        System.out.println("Ingrese el nuevo número de adultos:");
        int numeroAdultos = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nuevo número de ninos:");
        int numeroNinos = sc.nextInt();
        sc.nextLine();

        
        sc.nextLine();

        Reservas reserva = new Reservas(dni, numeroHabitacion, fechaEntrada, fechaSalida, numeroAdultos, numeroNinos);
        
        
        reservasDAO.actualizarReserva(reserva, id);
    }
    public void cancelarReserva() {
        System.out.println("Ingrese el ID de la reserva a cancelar:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el motivo de la cancelación:");
        String motivo = sc.nextLine();

        reservasDAO.cancelarReserva(id, motivo);
    }

    public void consultarReservas() {
        System.out.println("Ingrese el ID de la reserva a consultar:");
        int id = sc.nextInt();
        sc.nextLine();

        reservasDAO.consultarReserva(id);
        
    }

}