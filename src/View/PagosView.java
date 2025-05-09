package View;

import Dao.PagosDAO;
import java.time.LocalDate;
import java.util.Scanner;

public class PagosView {

    Scanner sc = new Scanner(System.in);

    ReservasView reservasView = new ReservasView();

    PagosDAO pagosDAO = new PagosDAO();

    public void menuPagos() {

        int opcion;
        System.out.println("Bienvenido al menú de Pagos");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Ver historial de pagos");
        System.out.println("2. Ver ingresos");
        System.out.println("3. Ver reembolsos");
        System.out.println("4. Ver gastos");

        opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1 -> verHistorialPagos();
            case 2 -> verIngresos();
            case 3 -> verReembolsos();
            case 4 -> verPagosEmpleado();
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }


    }

    public void verHistorialPagos() {
        int limite;

        String tipo="Historial";
        System.out.println("Historial de pagos:");
        System.out.println("Ingrese el número de pagos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();


        this.filtroPagos(limite, tipo);
    }

    public void verIngresos() {
        int limite;

        String tipo="Ingresos";
        System.out.println("Ingresos:");
        System.out.println("Ingrese el número de ingresos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();

        this.filtroPagos(limite, tipo);
    }

    public void verReembolsos() {
        int limite;

        String tipo="Reembolsos";
        System.out.println("Reembolsos:");
        System.out.println("Ingrese el número de reembolsos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();

        this.filtroPagos(limite, tipo);
    }

    public void verPagosEmpleado() {
        int limite;
        
        String tipo="Pagos Empleado";
        System.out.println("Gastos:");
        System.out.println("Ingrese el número de gastos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();
        
        this.filtroPagos(limite, tipo);  
    }

    public void filtroPagos(int limite, String tipo) {
        int opcion;
        System.out.println("Elija la opción de filtro:");
        System.out.println("1. Por fecha");
        System.out.println("2. Por cuantía");	
        System.out.println("3. Por el ID de la Reserva");
        System.out.println("4. Por DNI del Empleado");
        System.out.println("5. Recientes");

        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1 -> this.filtroFecha(limite, tipo);
            case 2 -> this.filtroCuantia(limite, tipo);
            case 3 -> this.filtroIDReserva(limite, tipo);
            case 4 -> this.filtroDNIEmpleado(limite, tipo);
            case 5 -> pagosDAO.filtroRecientes(limite, tipo);
           
        }

    }

    public void filtroFecha(int limite, String tipo) {
        LocalDate fechaInicio;
        LocalDate fechaFin;
        int suma;

        System.out.println("Ingrese la fecha de inicio:");
        fechaInicio = reservasView.fechaDia();
        System.out.println("Ingrese la fecha de fin:");
        fechaFin = reservasView.fechaDia();

        System.out.println("Quieres calcular la suma de los pagos?:");
        do{
        System.out.println("1. Si");
        System.out.println("2. No");

        suma = sc.nextInt();
        sc.nextLine();
        }while (suma != 1 && suma != 2);

        pagosDAO.filtroFecha(limite, fechaInicio, fechaFin, tipo, suma);
    }

    public void filtroCuantia(int limite, String tipo) {
        double cuantiaMinima;
        double cuantiaMaxima;

        System.out.println("Ingrese la cuantía mínima:");
        cuantiaMinima = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingrese la cuantía máxima:");
        cuantiaMaxima = sc.nextDouble();
        sc.nextLine();

        pagosDAO.filtroCuantia(limite, cuantiaMaxima, cuantiaMinima, tipo);
    }

    public void filtroIDReserva(int limite, String tipo) {
        int idReserva;

        System.out.println("Ingrese el ID de la reserva:");
        idReserva = sc.nextInt();
        sc.nextLine();

        pagosDAO.filtroIDReserva(limite, idReserva, tipo);
    }

    public void filtroDNIEmpleado(int limite, String tipo) {
        String dniEmpleado;

        System.out.println("Ingrese el DNI del empleado:");
        dniEmpleado = sc.nextLine();

        pagosDAO.filtroDNIEmpleado(limite, dniEmpleado, tipo);
    }


}