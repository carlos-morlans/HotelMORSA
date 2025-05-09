package View;

import Dao.PagosDAO;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PagosView {

    private Scanner scanner;
    private ReservasView reservasView;
    private PagosDAO pagosDAO;

    public PagosView() {
        this.scanner = new Scanner(System.in);
        this.reservasView = new ReservasView();
        this.pagosDAO = new PagosDAO();
    }

    public void menuPagos() {
        int opcion = -1;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("** Menú de Pagos **");
            System.out.println("=".repeat(35));
            System.out.println("1. Ver historial de pagos");
            System.out.println("2. Ver ingresos");
            System.out.println("3. Ver reembolsos");
            System.out.println("4. Ver pagos empleados");
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
                case 1 -> verHistorialPagos();
                case 2 -> verIngresos();
                case 3 -> verReembolsos();
                case 4 -> verPagosEmpleado();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 0);
    }

    public void verHistorialPagos() {
        System.out.println("\n--- Historial de Pagos ---");
        System.out.print("Ingrese el número de pagos a mostrar: ");
        try {
            int limite = scanner.nextInt();
            scanner.nextLine();
            this.filtroPagos(limite, "Historial");
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número válido.");
            scanner.next();
        }
    }

    public void verIngresos() {
        System.out.println("\n--- Ingresos ---");
        System.out.print("Ingrese el número de ingresos a mostrar: ");
        try {
            int limite = scanner.nextInt();
            scanner.nextLine();
            this.filtroPagos(limite, "Ingresos");
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número válido.");
            scanner.next();
        }
    }

    public void verReembolsos() {
        System.out.println("\n--- Reembolsos ---");
        System.out.print("Ingrese el número de reembolsos a mostrar: ");
        try {
            int limite = scanner.nextInt();
            scanner.nextLine();
            this.filtroPagos(limite, "Reembolsos");
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número válido.");
            scanner.next();
        }
    }

    public void verPagosEmpleado() {
        System.out.println("\n--- Pagos Empleados ---");
        System.out.print("Ingrese el número de gastos a mostrar: ");
        try {
            int limite = scanner.nextInt();
            scanner.nextLine();
            this.filtroPagos(limite, "Pagos Empleados");
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número válido.");
            scanner.next();
        }
    }

    public void filtroPagos(int limite, String tipo) {
        int opcionFiltro = -1;
        do {
            System.out.println("\n--- Filtrar " + tipo + " ---");
            System.out.println("Elija la opción de filtro:");
            System.out.println("1. Por fecha");
            System.out.println("2. Por cuantía");
            System.out.println("3. Por el ID de la Reserva");
            System.out.println("4. Por DNI del Empleado");
            System.out.println("5. Recientes");
            System.out.println("0. Volver al menú anterior");
            System.out.print("Seleccione una opción: ");
            try {
                opcionFiltro = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next();
                opcionFiltro = -1;
                continue;
            }
            scanner.nextLine();
            System.out.println("-".repeat(30));

            switch (opcionFiltro) {
                case 1 -> filtroFecha(limite, tipo);
                case 2 -> filtroCuantia(limite, tipo);
                case 3 -> filtroIDReserva(limite, tipo);
                case 4 -> filtroDNIEmpleado(limite, tipo);
                case 5 -> pagosDAO.filtroRecientes(limite, tipo);
                case 0 -> System.out.println("Volviendo al menú de " + tipo.toLowerCase() + "...");
                default -> {
                    if (opcionFiltro != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcionFiltro != 0);
    }

    public void filtroFecha(int limite, String tipo) {
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        int suma = 0;

        System.out.println("\n--- Filtrar " + tipo + " por Fecha ---");
        System.out.println("Ingrese la fecha de inicio:");
        fechaInicio = reservasView.fechaDia();
        System.out.println("Ingrese la fecha de fin:");
        fechaFin = reservasView.fechaDia();

        System.out.println("¿Quieres calcular la suma de los pagos en este rango?:");
        do {
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Seleccione una opción: ");
            try {
                suma = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next();
                suma = -1;
                continue;
            }
            scanner.nextLine();
        } while (suma != 1 && suma != 2);

        pagosDAO.filtroFecha(limite, fechaInicio, fechaFin, tipo, suma);
    }

    public void filtroCuantia(int limite, String tipo) {
        double cuantiaMinima = 0;
        double cuantiaMaxima = 0;

        System.out.println("\n--- Filtrar " + tipo + " por Cuantía ---");
        try {
            System.out.print("Ingrese la cuantía mínima: ");
            cuantiaMinima = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Ingrese la cuantía máxima: ");
            cuantiaMaxima = scanner.nextDouble();
            scanner.nextLine();
            pagosDAO.filtroCuantia(limite, cuantiaMaxima, cuantiaMinima, tipo);
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce valores numéricos válidos para la cuantía.");
            scanner.next();
        }
    }

    public void filtroIDReserva(int limite, String tipo) {
        System.out.println("\n--- Filtrar " + tipo + " por ID de Reserva ---");
        try {
            System.out.print("Ingrese el ID de la reserva: ");
            int idReserva = scanner.nextInt();
            scanner.nextLine();
            pagosDAO.filtroIDReserva(limite, idReserva, tipo);
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número válido para el ID de la reserva.");
            scanner.next();
        }
    }

    public void filtroDNIEmpleado(int limite, String tipo) {
        System.out.println("\n--- Filtrar " + tipo + " por DNI del Empleado ---");
        System.out.print("Ingrese el DNI del empleado: ");
        String dniEmpleado = scanner.nextLine();
        pagosDAO.filtroDNIEmpleado(limite, dniEmpleado, tipo);
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}