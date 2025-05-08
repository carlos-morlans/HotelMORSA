package View;

import java.time.LocalDate;
import java.util.Scanner;

import View.ReservasView;

import Dao.PagosDAO;

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
            case 4 -> verGastos();
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }

        
    }

    public void verHistorialPagos() {
        int limite;
        int suma;
        String tipo="Historial";
        System.out.println("Historial de pagos:");
        System.out.println("Ingrese el número de pagos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();
        System.out.println("Quieres calcular la suma de los pagos?:");
        do{
        System.out.println("1. Si");
        System.out.println("2. No");

        suma = sc.nextInt();
        sc.nextLine();
        }while (suma != 1 && suma != 2);

        this.filtroPagos(limite, tipo, suma);  
    }

    public void verIngresos() {
        int limite;
        int suma;
        String tipo="Ingresos";
        System.out.println("Ingresos:");
        System.out.println("Ingrese el número de ingresos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();
        System.out.println("Quieres calcular la suma de los pagos?:");
        do{
        System.out.println("1. Si");
        System.out.println("2. No");

        suma = sc.nextInt();
        sc.nextLine();
        }while (suma != 1 && suma != 2);
        this.filtroPagos(limite, tipo, suma);  
    }

    public void verReembolsos() {
        int limite;
        int suma;
        String tipo="Reembolsos";
        System.out.println("Reembolsos:");
        System.out.println("Ingrese el número de reembolsos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();
        System.out.println("Quieres calcular la suma de los pagos?:");
        do{
        System.out.println("1. Si");
        System.out.println("2. No");

        suma = sc.nextInt();
        sc.nextLine();
        }while (suma != 1 && suma != 2);
        this.filtroPagos(limite, tipo, suma);  
    }

    public void verGastos() {
        int limite;
        int suma;
        String tipo="Gastos";
        System.out.println("Gastos:");
        System.out.println("Ingrese el número de gastos a mostrar:");
        limite = sc.nextInt();
        sc.nextLine();
        System.out.println("Quieres calcular la suma de los pagos?:");
        do{
        System.out.println("1. Si");
        System.out.println("2. No");

        suma = sc.nextInt();
        sc.nextLine();
        }while (suma != 1 && suma != 2);
        this.filtroPagos(limite, tipo, suma);  
    }

    public void filtroPagos(int limite, String tipo, int suma) {
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
            case 1 -> this.filtroFecha(limite, tipo, suma);
            case 2 -> this.filtroCuantia(limite, tipo, suma);
            case 3 -> this.filtroIDReserva(limite, tipo, suma);
            case 4 -> this.filtroDNIEmpleado(limite, tipo, suma);
            case 5 -> pagosDAO.filtroRecientes(limite, tipo, suma);
           
        }

    }

    public void filtroFecha(int limite, String tipo, int suma) {
        LocalDate fechaInicio;
        LocalDate fechaFin;


        System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
        fechaInicio = reservasView.fechaDia();
        System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
        fechaFin = reservasView.fechaDia();

        pagosDAO.filtroFecha(limite, fechaInicio, fechaFin, tipo, suma);
    }

    public void filtroCuantia(int limite, String tipo, int suma) {
        double cuantiaMinima;
        double cuantiaMaxima;

        System.out.println("Ingrese la cuantía mínima:");
        cuantiaMinima = sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingrese la cuantía máxima:");
        cuantiaMaxima = sc.nextDouble();
        sc.nextLine();

        pagosDAO.filtroCuantia(limite, cuantiaMinima, cuantiaMaxima, tipo, suma);
    }

    public void filtroIDReserva(int limite, String tipo, int suma) {
        int idReserva;

        System.out.println("Ingrese el ID de la reserva:");
        idReserva = sc.nextInt();
        sc.nextLine();

        pagosDAO.filtroIDReserva(limite, idReserva, tipo, suma);
    }

    public void filtroDNIEmpleado(int limite, String tipo, int suma) {
        String dniEmpleado;

        System.out.println("Ingrese el DNI del empleado:");
        dniEmpleado = sc.nextLine();

        pagosDAO.filtroDNIEmpleado(limite, dniEmpleado, tipo, suma);
    }


}