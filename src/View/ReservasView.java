package View;

import Dao.ReservasDAO;
import Model.Reservas;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReservasView {

    private ReservasDAO reservasDAO;
    private Scanner scanner;
    private HabitacionesView habitacion;

    public ReservasView() {
        this.reservasDAO = new ReservasDAO();
        this.scanner = new Scanner(System.in);
        this.habitacion = new HabitacionesView();
    }

    public void menuReservas() {
        int opcion = -1;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("** Menú de Reservas **");
            System.out.println("=".repeat(35));
            System.out.println("1. Crear Reserva");
            System.out.println("2. Actualizar Reserva");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Consultar Reserva");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next();
                opcion = -1;
                continue;
            }
            scanner.nextLine();
            System.out.println("-".repeat(35));

            switch (opcion) {
                case 1 -> crearReserva();
                case 2 -> actualizarReserva();
                case 3 -> cancelarReserva();
                case 4 -> consultarReserva();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 0);
    }

    public void crearReserva() {
        System.out.println("\n--- Crear Nueva Reserva ---");
        System.out.print("Ingrese el DNI del cliente: ");
        String dni = scanner.nextLine();

        System.out.println("\n--- Elija el tipo de habitación que desea: ---");
        habitacion.listarHabitaciones();

        int numeroHabitacion = -1;
        try {
            System.out.print("Ingrese el número de habitación: ");
            numeroHabitacion = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número de habitación válido.");
            scanner.next();
            return;
        }

        LocalDate fechaEntrada = null;
        System.out.println("Ingrese la fecha de entrada:");
        fechaEntrada = this.fechaDia();
        if (fechaEntrada != null) {
            System.out.println("Fecha de entrada: " + fechaEntrada);
        } else {
            return;
        }

        LocalDate fechaSalida = null;
        System.out.println("Ingrese la fecha de salida:");
        fechaSalida = this.fechaDia();
        if (fechaSalida != null) {
            System.out.println("Fecha de salida: " + fechaSalida);
        } else {
            return;
        }

        int numeroAdultos = -1;
        try {
            System.out.print("Ingrese el número de adultos: ");
            numeroAdultos = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número de adultos válido.");
            scanner.next();
            return;
        }

        int numeroNinos = -1;
        try {
            System.out.print("Ingrese el número de niños: ");
            numeroNinos = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número de niños válido.");
            scanner.next();
            return;
        }

        Reservas reserva = new Reservas(dni, numeroHabitacion, fechaEntrada, fechaSalida, numeroAdultos, numeroNinos);
        reservasDAO.crearReserva(reserva);
        System.out.println("Reserva creada exitosamente.");
    }

    public LocalDateTime fechaMinuto() {
        int year = 0, month = 0, day = 0, hour = 0, minute = 0;
        try {
            System.out.print("Ingrese el año: ");
            year = scanner.nextInt();
            System.out.print("Ingrese el mes: ");
            month = scanner.nextInt();
            System.out.print("Ingrese el día: ");
            day = scanner.nextInt();
            System.out.print("Ingrese la hora: ");
            hour = scanner.nextInt();
            System.out.print("Ingrese los minutos: ");
            minute = scanner.nextInt();
            scanner.nextLine();
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce valores numéricos válidos para la fecha y hora.");
            scanner.next();
            return null;
        }
    }

    public LocalDate fechaDia() {
        int year = 0, month = 0, day = 0;
        try {
            System.out.print("Ingrese el año: ");
            year = scanner.nextInt();
            System.out.print("Ingrese el mes (1-12): ");
            month = scanner.nextInt();
            System.out.print("Ingrese el día (1-31): ");
            day = scanner.nextInt();
            scanner.nextLine();
            return LocalDate.of(year, month, day);
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce valores numéricos válidos para la fecha.");
            scanner.next();
            return null;
        } catch (java.time.DateTimeException e) {
            System.out.println("Error: La fecha introducida no es válida. Por favor, verifica el día y el mes.");
            return null;
        }
    }

    public void actualizarReserva() {
        System.out.println("\n--- Actualizar Reserva ---");
        int id = -1;
        try {
            System.out.print("Ingrese el ID de la reserva a actualizar: ");
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un ID de reserva válido.");
            scanner.next();
            return;
        }

        System.out.print("Ingrese el nuevo DNI del cliente: ");
        String dni = scanner.nextLine();

        int numeroHabitacion = -1;
        try {
            System.out.print("Ingrese el nuevo número de habitación: ");
            numeroHabitacion = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número de habitación válido.");
            scanner.next();
            return;
        }

        LocalDate fechaEntrada = null;
        System.out.println("Ingrese la nueva fecha de entrada:");
        fechaEntrada = this.fechaDia();
        if (fechaEntrada == null) return;

        LocalDate fechaSalida = null;
        System.out.println("Ingrese la nueva fecha de salida:");
        fechaSalida = this.fechaDia();
        if (fechaSalida == null) return;

        int numeroAdultos = -1;
        try {
            System.out.print("Ingrese el nuevo número de adultos: ");
            numeroAdultos = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número de adultos válido.");
            scanner.next();
            return;
        }

        int numeroNinos = -1;
        try {
            System.out.print("Ingrese el nuevo número de niños: ");
            numeroNinos = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número de niños válido.");
            scanner.next();
            return;
        }

        Reservas reserva = new Reservas(dni, numeroHabitacion, fechaEntrada, fechaSalida, numeroAdultos, numeroNinos);
        reservasDAO.actualizarReserva(reserva, id);
        System.out.println("Reserva con ID " + id + " actualizada exitosamente.");
    }

    public void cancelarReserva() {
        System.out.println("\n--- Cancelar Reserva ---");
        int id = -1;
        try {
            System.out.print("Ingrese el ID de la reserva a cancelar: ");
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un ID de reserva válido.");
            scanner.next();
            return;
        }

        System.out.print("Ingrese el motivo de la cancelación: ");
        String motivo = scanner.nextLine();

        reservasDAO.cancelarReserva(id, motivo);
        System.out.println("Reserva con ID " + id + " cancelada.");
    }

    public void consultarReserva() {
        System.out.println("\n--- Consultar Reserva ---");
        int id = -1;
        try {
            System.out.print("Ingrese el ID de la reserva a consultar: ");
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un ID de reserva válido.");
            scanner.next();
            return;
        }

        reservasDAO.consultarReserva(id);
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}