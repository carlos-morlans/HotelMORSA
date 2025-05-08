package View;
import java.util.*;

import model.Garaje;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestionHotel {
    // Listas para gestionar todas las entidades
    private List<Habitacion> habitaciones;
    private List<Evento> eventos;
    private Garaje garaje;
    private List<Cliente> clientes;
    private List<Trabajador> trabajadores;
    private Scanner scanner;



    public void menus() {
        GarajeView.mostrarMenuGaraje();
    }


    public GestionHotel() {
        habitaciones = new ArrayList<>();
        eventos = new ArrayList<>();
        garaje = new Garaje(50); // Capacidad para 50 vehículos
        clientes = new ArrayList<>();
        trabajadores = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Menú principal interactivo
    private void mostrarMenuPrincipal() {
        System.out.println("\nSISTEMA DE GESTIÓN HOTELERA");
        System.out.println("1. Gestión de Habitaciones");
        System.out.println("2. Gestión de Eventos");
        System.out.println("3. Gestión de Clientes");
        System.out.println("4. Gestión de Trabajadores");
        System.out.println("5. Gestión de Garaje");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void ejecutarSistema() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: gestionarHabitaciones(); break;
                case 2: gestionarEventos(); break;
                case 3: gestionarClientes(); break;
                case 4: gestionarTrabajadores(); break;
                case 5: gestionarGaraje(); break;
                case 0: System.out.println("Saliendo del sistema de gestion"); break;
                default: System.out.println("Opción no válida");
            }
        } while (opcion != 0);
        scanner.close();
    }

    // Metodo para gesitonar las habitaciones
    private void gestionarHabitaciones() {
        int opcion;
        do {
            System.out.println("\nGESTIÓN DE HABITACIONES");
            System.out.println("1. Añadir habitación");
            System.out.println("2. Reservar habitación");
            System.out.println("3. Liberar habitación");
            System.out.println("4. Mostrar todas las habitaciones");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            
            switch (opcion) {       
                case 1:
                añadirHabitacion();
                break;

                case 2:
                reservarHabitacion();
                break;
                
                case 3:
                liberarHabitacion();
                break;
                
                case 4:
                mostrarHabitaciones();
                break;

                case 0:
                System.out.println("Volviendo al menú principal...");
                break;
                    default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 0);
    }

    //Metodo para añadir una habitacion
    private void añadirHabitacion() {
        System.out.println("Numero de habitacion: ");
        int numero = Integer.parseInt(scanner.nextLine());
        System.out.println("Tipo de habitacion (Indivivual/Doble/Familiar/Suite): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Precio por noche: ");
        double precio = Double.parseDouble(scanner.nextLine());

        habitaciones.add(new Habitacion(numero, tipo, precio));
        System.out.println("Habitación añadida con éxito");

    }
    
    // Método para reservar habitación
    private void reservarHabitacion() {
        System.out.print("Número de habitación: ");
        int numero = Integer.parseInt(scanner.nextLine());
        Habitacion hab = buscarHabitacion(numero);
        if (hab == null || hab.isOcupada()) {
            System.out.println("Habitación no disponible");
            return;
        }
    
        System.out.print("Nombre cliente: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Incluye desayuno (Si/No): ");
        boolean conDesayuno = scanner.nextLine().equalsIgnoreCase("Si");
        
        System.out.print("Fecha check-in (YYYY-MM-DD): ");
        String fechaIn = scanner.nextLine();
        
        System.out.print("Fecha check-out (YYYY-MM-DD): ");
        String fechaOut = scanner.nextLine();
        
        Cliente cliente = new Cliente(nombre, conDesayuno, numero, null);
        hab.asignarCliente(cliente, fechaIn, fechaOut);
        clientes.add(cliente);
        System.out.println("Reserva exitosa");
    }
    //Metodo para buscar una habitacion
    private Habitacion buscarHabitacion(int numeroHabBuscado) {
        for (Habitacion hab : this.habitaciones) {
            if (hab.getNumero() == numeroHabBuscado) {
                return hab;
            }
        }
        return null;

    //Metodo para liberar una habitacion
    private void liberarHabitacion() {
        System.out.print("Número de habitación: ");
        int numero = Integer.parseInt(scanner.nextLine());
        
        Habitacion hab = buscarHabitacion(numero);
        if (hab != null && hab.isOcupada()) {
            hab.liberarHabitacion();
            System.out.println("Habitación liberada");
        } else {
            System.out.println("No se pudo liberar");
        }
    }

    // Método para mostrar habitaciones
    public void mostrarHabitaciones() {
        System.out.println("\nLISTADO DE HABITACIONES:");
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
            return;
        }
        System.out.printf("%-10s %-15s %-10s %-10s%n", "Número", "Tipo", "Precio", "Estado");
        for (Habitacion h : habitaciones) {
            System.out.printf("%-10d %-15s %-10.2f %-10s%n", 
                h.getNumero(), h.getTipo(), h.getPrecioPorNoche(), 
                h.isOcupada() ? "Ocupada" : "Disponible");
        }
    }
    

    public static void main(String[] args) {
        GestionHotel sistema = new GestionHotel();
        sistema.ejecutarSistema();
    }
}

