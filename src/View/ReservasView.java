package View;

import java.util.Scanner;
import Model.Reservas;
import Dao.ReservasDAO;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ReservasView {

    ReservasDAO reservasDAO = new ReservasDAO();

    Scanner sc = new Scanner(System.in);

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
        
        System.out.println("Ingrese el número de habitación:");
        int numeroHabitacion = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese la fecha de entrada (YYYY-MM-DD):");
        LocalDate fechaEntrada = this.fechaDia();

        System.out.println("Ingrese la fecha de salida (YYYY-MM-DD):");
        LocalDate fechaSalida = this.fechaDia();

        System.out.println("Ingrese el número de adultos:");
        int numeroAdultos = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el número de niños:");
        int numeroNinos = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese la fecha de reserva (YYYY-MM-DD HH:MM):");
        LocalDateTime fechaReserva = this.fechaMinuto();

        System.out.println("Ingrese el estado de la reserva:");
        String estadoReserva = sc.nextLine();

        System.out.println("Ingrese el precio total:");
        double precioTotal = sc.nextDouble();
        sc.nextLine();

        Reservas reserva = new Reservas(0, dni, numeroHabitacion, fechaEntrada, fechaSalida, numeroAdultos, numeroNinos, fechaReserva, estadoReserva, precioTotal, null, null);
        
        reservasDAO.crearReserva(reserva);
    }

    public LocalDateTime fechaMinuto(){
        int year, month, day, hour, minute;
        System.out.println("Ingrese el año:");
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
        System.out.println("Ingrese el año:");
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

        System.out.println("Ingrese la nueva fecha de entrada (YYYY-MM-DD):");
        LocalDate fechaEntrada = this.fechaDia();

        System.out.println("Ingrese la nueva fecha de salida (YYYY-MM-DD):");
        LocalDate fechaSalida = this.fechaDia();

        System.out.println("Ingrese el nuevo número de adultos:");
        int numeroAdultos = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nuevo número de niños:");
        int numeroNinos = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese la nueva fecha de reserva (YYYY-MM-DD HH:MM):");
        LocalDateTime fechaReserva = this.fechaMinuto();

        System.out.println("Ingrese el nuevo estado de la reserva:");
        String estadoReserva = sc.nextLine();

        System.out.println("Ingrese el nuevo precio total:");
        double precioTotal = sc.nextDouble();
        sc.nextLine();

        Reservas reserva = new Reservas(id, dni, numeroHabitacion, fechaEntrada, fechaSalida, numeroAdultos, numeroNinos, fechaReserva, estadoReserva, precioTotal, null, null);
        
        reservasDAO.actualizarReserva(reserva);
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

        Reservas reserva = reservasDAO.consultarReserva(id);
        if (reserva != null) {
            System.out.println("Reserva encontrada: " + reserva);
        } else {
            System.out.println("No se encontró la reserva con ID: " + id);
        }
    }

}