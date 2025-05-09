package View;

import Dao.HabitacionDAO;
import Model.Habitaciones;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HabitacionesView {

    private Scanner scanner;
    private HabitacionDAO habitacionDAO;

    public HabitacionesView() {
        this.scanner = new Scanner(System.in);
        this.habitacionDAO = new HabitacionDAO(); 
    }

    public void gestionarMenuHabitaciones() {
        int opcion = -1;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("** Menú de Habitaciones **");
            System.out.println("=".repeat(35));
            System.out.println("1. [AGREGAR] Nueva Habitación");
            System.out.println("2. [BUSCAR] Habitación por Número");
            System.out.println("3. [LISTAR] Todas las Habitaciones");
            System.out.println("4. [ACTUALIZAR] Habitación");
            System.out.println("5. [ELIMINAR] Habitación");
            System.out.println("0. [VOLVER] Al menú principal");
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
                case 1 -> agregarNuevaHabitacion();
                case 2 -> buscarHabitacion();
                case 3 -> listarHabitaciones();
                case 4 -> actualizarDatosHabitacion();
                case 5 -> eliminarHabitacionPorNumero();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 0);
    }

    private void agregarNuevaHabitacion() {
        System.out.println("\n--- Nueva Habitación ---");
        System.out.print("Número de Habitación: ");
        int numeroHabitacion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo de Habitación: ");
        String tipoHabitacion = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = scanner.nextInt();
        System.out.print("Precio por Noche: ");
        double precioNoche = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Estado (Disponible/Ocupada/Mantenimiento): ");
        String estado = scanner.nextLine();
        Habitaciones nuevaHabitacion = new Habitaciones(numeroHabitacion, tipoHabitacion, capacidad, precioNoche, estado);
        habitacionDAO.insertar(nuevaHabitacion);
        System.out.println("Habitación agregada exitosamente.");
    }

    private void buscarHabitacion() {
        System.out.println("\n--- Buscar Habitación por Número ---");
        System.out.print("Ingrese el número de habitación a buscar: ");
        int numeroHabitacion = scanner.nextInt();
        Habitaciones habitacion = habitacionDAO.buscarPorNumero(numeroHabitacion);
        if (habitacion != null) {
            System.out.println("\n--- Datos de la Habitación ---");
            System.out.println(habitacion);
        } else {
            System.out.println("No se encontró ninguna habitación con ese número.");
        }
    }

    public void listarHabitaciones() {
        ArrayList<Habitaciones> habitaciones = habitacionDAO.obtenerTodos();
        if (habitaciones != null && !habitaciones.isEmpty()) {
            System.out.println("\n--- Lista de Habitaciones ---");
            for (Habitaciones habitacion : habitaciones) {
                System.out.println(habitacion);
            }
        } else {
            System.out.println("No hay habitaciones registradas.");
        }
    }

    private void actualizarDatosHabitacion() {
        System.out.println("\n--- Actualizar Habitación ---");
        System.out.print("Ingrese el número de habitación a actualizar: ");
        int numeroHabitacion = scanner.nextInt();
        scanner.nextLine();

        int opcionActualizar = -1;
        do {
            System.out.println("\n--- ¿Qué desea actualizar de la habitación " + numeroHabitacion + "? ---");
            System.out.println("1. Tipo de Habitación");
            System.out.println("2. Capacidad");
            System.out.println("3. Precio por Noche");
            System.out.println("4. Estado");
            System.out.println("0. Volver al menú de habitaciones");
            System.out.print("Seleccione una opción: ");
            try {
                opcionActualizar = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next();
                opcionActualizar = -1;
                continue;
            }
            scanner.nextLine();

            switch (opcionActualizar) {
                case 1:
                    System.out.print("Ingrese el nuevo Tipo de Habitación: ");
                    String nuevoTipo = scanner.nextLine();
                    habitacionDAO.actualizar("TipoHabitacion", nuevoTipo, numeroHabitacion);
                    System.out.println("Tipo de habitación actualizado.");
                    break;
                case 2:
                    System.out.print("Ingrese la nueva Capacidad: ");
                    String nuevaCapacidad = scanner.nextLine();
                    habitacionDAO.actualizar("Capacidad", nuevaCapacidad, numeroHabitacion);
                    System.out.println("Capacidad de la habitación actualizada.");
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo Precio por Noche: ");
                    String nuevoPrecio = scanner.nextLine();
                    habitacionDAO.actualizar("PrecioNoche", nuevoPrecio, numeroHabitacion);
                    System.out.println("Precio por noche actualizado.");
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo Estado: ");
                    String nuevoEstado = scanner.nextLine();
                    habitacionDAO.actualizar("Estado", nuevoEstado, numeroHabitacion);
                    System.out.println("Estado de la habitación actualizado.");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal de habitaciones.");
                    break;
                default:
                    if (opcionActualizar != -1) {
                        System.out.println("Opción inválida. Intente de nuevo.");
                    }
            }
        } while (opcionActualizar != 0);
    }

    private void eliminarHabitacionPorNumero() {
        System.out.println("\n--- Eliminar Habitación ---");
        System.out.print("Ingrese el número de habitación a eliminar: ");
        int numeroHabitacion = scanner.nextInt();
        habitacionDAO.eliminar(String.valueOf(numeroHabitacion));
        System.out.println("Habitación " + numeroHabitacion + " eliminada.");
        scanner.nextLine(); 
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}