package View;

import Dao.EventosDAO;
import Model.Eventos;
import java.sql.Date;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventoView {

    private Scanner scanner;
    private EventosDAO eventosDAO;

    public EventoView() {
        this.scanner = new Scanner(System.in);
        this.eventosDAO = new EventosDAO();
    }

    public void menuEventos() {
        int opcion = -1;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("** Menú de Eventos **");
            System.out.println("=".repeat(35));
            System.out.println("1. [BUSCAR] Evento por ID");
            System.out.println("2. [MODIFICAR] Evento");
            System.out.println("3. [AÑADIR] Nuevo Evento");
            System.out.println("4. [MOSTRAR] Todos los Eventos");
            System.out.println("5. [ELIMINAR] Evento");
            System.out.println("0. [VOLVER] Al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next(); // Limpiar el buffer del scanner
                opcion = -1; // Reiniciar la opción para que el bucle continúe
                continue;
            }
            scanner.nextLine(); // Consumir la nueva línea después de leer el entero
            System.out.println("-".repeat(35));

            switch (opcion) {
                case 1 -> buscarID();
                case 2 -> gestionarEventos();
                case 3 -> crearEventos();
                case 4 -> eventosMostrar();
                case 5 -> eliminarEventos();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 0);
    }

    public void buscarID() {
        System.out.println("\n--- Buscar Evento por ID ---");
        System.out.print("ID del evento a buscar: ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            Eventos evento = eventosDAO.buscarPorID(id);

            if (evento != null) {
                System.out.println("\n--- Datos del Evento ---");
                System.out.println("ID: " + evento.getEventoID());
                System.out.println("Nombre: " + evento.getNombreEvento());
                System.out.println("Fecha: " + evento.getFechaEvento());
                System.out.println("Hora: " + evento.getHoraInicio());
                System.out.println("Precio: " + evento.getPrecio());
                System.out.println("Capacidad: " + evento.getCapacidad());
            } else {
                System.out.println("No se ha encontrado ningún evento con ese ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número para el ID.");
            scanner.next();
        }
    }

    public void gestionarEventos() {
        System.out.println("\n--- Modificar Evento ---");
        System.out.print("Introduzca el ID del evento que desea gestionar: ");
        try {
            int idEvento = scanner.nextInt();
            scanner.nextLine();
            Eventos evento = eventosDAO.buscarPorID(idEvento);

            if (evento == null) {
                System.out.println("No se encontró ningún evento con ese ID.");
                return;
            }

            int eleccion = -1;
            do {
                System.out.println("\n--- ¿Qué desea modificar del evento con ID " + evento.getEventoID() + "? ---");
                System.out.println("1. Nombre");
                System.out.println("2. Fecha");
                System.out.println("3. Hora");
                System.out.println("4. Precio");
                System.out.println("5. Capacidad");
                System.out.println("6. Salir");
                System.out.print("Introduzca una opción: ");
                try {
                    eleccion = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor, introduce un número como opción.");
                    scanner.next();
                    eleccion = -1;
                    continue;
                }
                scanner.nextLine(); // Consume newline

                String atributo = "";
                String valor = "";

                switch (eleccion) {
                    case 1:
                        atributo = "NombreEvento";
                        System.out.print("Introduzca el nombre nuevo: ");
                        valor = scanner.nextLine();
                        break;
                    case 2:
                        atributo = "FechaEvento";
                        System.out.print("Introduzca la fecha nueva (YYYY-MM-DD): ");
                        valor = scanner.nextLine();
                        break;
                    case 3:
                        atributo = "HoraInicio";
                        System.out.print("Introduzca la hora nueva (HH:MM:SS): ");
                        valor = scanner.nextLine();
                        break;
                    case 4:
                        atributo = "Precio";
                        System.out.print("Introduzca el precio nuevo: ");
                        valor = scanner.nextLine();
                        break;
                    case 5:
                        atributo = "Capacidad";
                        System.out.print("Introduzca la capacidad nueva: ");
                        valor = scanner.nextLine();
                        break;
                    case 6:
                        System.out.println("Volviendo al menú de eventos.");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }

                if (!atributo.isEmpty()) {
                    try {
                        eventosDAO.actualizar(atributo, valor, evento.getEventoID());
                        System.out.println("Evento actualizado.");
                    } catch (Exception e) {
                        System.out.println("Error al actualizar el evento: " + e.getMessage());
                    }
                }
            } while (eleccion != 6);

        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número para el ID del evento.");
            scanner.next();
        }
    }

    public void crearEventos() {
        System.out.println("\n--- Crear un Nuevo Evento ---");
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Fecha (YYYY-MM-DD): ");
            Date fecha = Date.valueOf(scanner.nextLine());
            System.out.print("Hora (HH:MM:SS): ");
            Time hora = Time.valueOf(scanner.nextLine());
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            System.out.print("Capacidad: ");
            int capacidad = scanner.nextInt();
            scanner.nextLine();

            Eventos evento = new Eventos(id, nombre, fecha, hora, precio, capacidad);
            eventosDAO.insertar(evento);
            System.out.println("Nuevo evento creado exitosamente.");

        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce el tipo de dato correcto.");
            scanner.next();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Formato de fecha u hora incorrecto. Use YYYY-MM-DD para fecha y HH:MM:SS para hora.");
        } catch (Exception e) {
            System.out.println("Error al crear el evento: " + e.getMessage());
        }
    }

    public void eliminarEventos() {
        System.out.println("\n--- Eliminar Evento ---");
        System.out.print("Introduzca el ID del evento que desea eliminar: ");
        try {
            int idEliminar = scanner.nextInt();
            scanner.nextLine();

            if (eventosDAO.buscarPorID(idEliminar) != null) {
                System.out.print("¿Seguro que desea eliminar el evento con ID " + idEliminar + "? (Si/No): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("Si")) {
                    eventosDAO.eliminar(idEliminar);
                    System.out.println("Evento eliminado exitosamente.");
                } else {
                    System.out.println("Operación de eliminación cancelada.");
                }
            } else {
                System.out.println("No se encontró ningún evento con ese ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número para el ID.");
            scanner.next();
        } catch (Exception e) {
            System.out.println("Error al eliminar el evento: " + e.getMessage());
        }
    }

    public void eventosMostrar() {
        System.out.println("\n--- Listado de Eventos ---");
        try {
            java.util.ArrayList<Eventos> eventos = eventosDAO.obtenerTodos();
            if (eventos != null && !eventos.isEmpty()) {
                for (Eventos evento : eventos) {
                    System.out.println(evento);
                }
                System.out.println("--- Fin del listado de eventos ---");
            } else {
                System.out.println("No hay eventos registrados.");
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los eventos: " + e.getMessage());
        }
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}