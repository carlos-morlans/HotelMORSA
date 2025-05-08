package View;

import Dao.GarajeDAO;
import Model.Garaje;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GarajeView {

    private Scanner scanner;
    private GarajeDAO garajeDAO;

    public GarajeView() {
        this.scanner = new Scanner(System.in);
        this.garajeDAO = new GarajeDAO();
    }

    public void mostrarMenuGaraje() {
        int opcion = -1;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("** Menú de Garaje **");
            System.out.println("=".repeat(35));
            System.out.println("1. [CREAR] Nueva Plaza");
            System.out.println("2. [MODIFICAR] Plaza");
            System.out.println("3. [BUSCAR] Plaza por Número");
            System.out.println("4. [ELIMINAR] Plaza");
            System.out.println("0. [VOLVER] Al menú principal");
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
            System.out.println("-".repeat(35));

            switch (opcion) {
                case 1 -> crearPlaza();
                case 2 -> modificarPlaza();
                case 3 -> buscarPorPlaza();
                case 4 -> eliminarPlaza();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 0);
    }

    public void crearPlaza() {
        System.out.println("\n--- Crear Nueva Plaza ---");
        try {
            System.out.print("Introduce el Número de Plaza: ");
            int numeroPlaza = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea pendiente
            System.out.print("Introduce el estado de la plaza (ocupado/libre): ");
            String estado = scanner.nextLine();

            Garaje nuevaGaraje = new Garaje(numeroPlaza, estado);
            garajeDAO.insertar(nuevaGaraje);
            System.out.println("Plaza creada correctamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error: El número de plaza debe ser un valor numérico.");
            scanner.next(); // Limpiar el scanner
        }
    }

    public void modificarPlaza() {
        System.out.println("\n--- Modificar Plaza ---");
        try {
            System.out.print("Número de plaza a modificar: ");
            int numeroPlaza = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea pendiente

            int opcionModificar = -1;
            do {
                System.out.println("\n--- ¿Qué desea modificar de la plaza " + numeroPlaza + "? ---");
                System.out.println("1. Estado (ocupado/libre)");
                System.out.println("0. Volver al menú de garaje");
                System.out.print("Seleccione una opción: ");
                try {
                    opcionModificar = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor, introduce un número como opción.");
                    scanner.next(); // Limpiar el scanner
                    opcionModificar = -1;
                    continue;
                }
                scanner.nextLine(); // Consumir la nueva línea pendiente

                switch (opcionModificar) {
                    case 1:
                        System.out.print("Introduce el nuevo estado de la plaza (ocupado/libre): ");
                        String nuevoEstado = scanner.nextLine();
                        garajeDAO.modificarEstado(numeroPlaza, nuevoEstado);
                        System.out.println("Estado de la plaza modificado correctamente.");
                        break;
                    case 0:
                        System.out.println("Volviendo al menú de garaje.");
                        break;
                    default:
                        if (opcionModificar != -1) {
                            System.out.println("Opción no válida. Intente nuevamente.");
                        }
                }
            } while (opcionModificar != 0);

        } catch (InputMismatchException e) {
            System.out.println("Error: El número de plaza debe ser un valor numérico.");
            scanner.next(); // Limpiar el scanner
        }
    }

    public void buscarPorPlaza() {
        System.out.println("\n--- Buscar Plaza por Número ---");
        try {
            System.out.print("Escribe el número de la plaza que quieres buscar: ");
            int numeroPlaza = scanner.nextInt();
            garajeDAO.buscarPlaza(numeroPlaza);
            scanner.nextLine(); // Consumir la nueva línea pendiente
        } catch (InputMismatchException e) {
            System.out.println("Error: El número de plaza debe ser un valor numérico.");
            scanner.next(); // Limpiar el scanner
        }
    }

    public void eliminarPlaza() {
        System.out.println("\n--- Eliminar Plaza ---");
        try {
            System.out.print("Escribe el número de la plaza que quieres eliminar: ");
            int numeroPlaza = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea pendiente

            System.out.print("¿Seguro que quieres eliminar esta plaza (Si/No)? ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("Si")) {
                garajeDAO.eliminarPlazaGaraje(numeroPlaza);
                System.out.println("Plaza eliminada.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El número de plaza debe ser un valor numérico.");
            scanner.next(); // Limpiar el scanner
        }
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}