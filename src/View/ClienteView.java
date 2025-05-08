package View;

import Dao.ClienteDAO;
import Model.Clientes;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteView {

    private Scanner scanner;
    private Clientes cliente;
    private ClienteDAO clienteDAO;
    private ArrayList<Clientes> listaClientes;

    public ClienteView() {
        this.scanner = new Scanner(System.in);
        this.clienteDAO = new ClienteDAO();
        this.listaClientes = new ArrayList<>();
    }

    public void menuCliente() {
        int opcion = -1;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("** Menú de Clientes **");
            System.out.println("=".repeat(35));
            System.out.println("1. [AÑADIR] Nuevo Cliente");
            System.out.println("2. [MODIFICAR] Cliente");
            System.out.println("3. [ELIMINAR] Cliente");
            System.out.println("4. [VER] Clientes");
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
                case 1 -> añadirCliente();
                case 2 -> modificarCliente();
                case 3 -> eliminarCliente();
                case 4 -> verClientes();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 0);
    }

    public void añadirCliente() {
        System.out.println("\n--- Añadir Nuevo Cliente ---");
        try {
            System.out.print("DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Dirección: ");
            String direccion = scanner.nextLine();
            cliente = new Clientes(dni, nombre, apellido, email, telefono, direccion);

            clienteDAO.insertar(cliente);
            System.out.println("Cliente añadido correctamente.");
        } catch (Exception e) {
            System.out.println("Error al añadir el cliente: " + e.getMessage());
        }
    }

    public void modificarCliente() {
        System.out.println("\n--- Modificar Cliente ---");
        System.out.print("DNI del cliente a modificar: ");
        String dni = scanner.nextLine();

        if (clienteDAO.buscarPorDni(dni) == null) {
            System.out.println("No se encontró ningún cliente con ese DNI.");
            return;
        }

        int opcion2 = -1;
        do {
            System.out.println("\n--- ¿Qué desea modificar del cliente con DNI " + dni + "? ---");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Email");
            System.out.println("4. Teléfono");
            System.out.println("5. Dirección");
            System.out.println("0. Volver al menú de clientes");
            System.out.print("Opción: ");
            try {
                opcion2 = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next(); // Limpiar el buffer
                opcion2 = -1;
                continue;
            }
            scanner.nextLine(); // Consumir la nueva línea

            String atributo = null;
            System.out.print("Nuevo valor: ");
            String valor = scanner.nextLine();

            switch (opcion2) {
                case 1 -> atributo = "Nombre";
                case 2 -> atributo = "Apellido";
                case 3 -> atributo = "Email";
                case 4 -> atributo = "Telefono";
                case 5 -> atributo = "Direccion";
                case 0 -> System.out.println("Volviendo al menú de clientes.");
                default -> {
                    if (opcion2 != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }

            if (atributo != null) {
                clienteDAO.actualizar(atributo, valor, dni);
                System.out.println("Cliente modificado correctamente.");
            }

        } while (opcion2 != 0);

        System.out.println("Saliendo del menú de modificar cliente.");
    }

    public void eliminarCliente() {
        System.out.println("\n--- Eliminar Cliente ---");
        System.out.print("DNI del cliente a eliminar: ");
        String dni = scanner.nextLine();

        if (clienteDAO.buscarPorDni(dni) == null) {
            System.out.println("No se encontró ningún cliente con ese DNI.");
            return;
        }

        System.out.print("¿Seguro que desea eliminar el cliente con DNI " + dni + "? (Si/No): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("Si")) {
            clienteDAO.eliminar(dni);
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
        System.out.println("Saliendo del menú de eliminar cliente.");
    }

    public void verClientes() {
        int opcion = -1;
        do {
            System.out.println("\n--- Ver Clientes ---");
            System.out.println("1. Ver todos los clientes");
            System.out.println("2. Ver cliente por DNI");
            System.out.println("0. Volver al menú de clientes");
            System.out.print("Opción: ");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next(); // Limpiar el buffer
                opcion = -1;
                continue;
            }
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1 -> {
                    listaClientes = clienteDAO.obtenerTodos();
                    if (listaClientes != null && !listaClientes.isEmpty()) {
                        System.out.println("\n--- Listado de Clientes ---");
                        for (Clientes cliente : listaClientes) {
                            System.out.println(cliente);
                        }
                    } else {
                        System.out.println("No hay clientes registrados.");
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Ver Cliente por DNI ---");
                    System.out.print("Introduce el DNI del cliente: ");
                    String dni = scanner.nextLine();
                    cliente = clienteDAO.buscarPorDni(dni);
                    if (cliente != null) {
                        System.out.println(cliente);
                    } else {
                        System.out.println("No se encontró ningún cliente con ese DNI.");
                    }
                }
                case 0 -> System.out.println("Volviendo al menú de clientes.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        System.out.println("Saliendo del menú de ver clientes.");
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}