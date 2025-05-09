package View;

import Dao.EmpleadosDAO;
import Model.Empleados;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmpleadosView {

    private Scanner scanner;
    private Empleados empleado;
    private EmpleadosDAO empleadosDAO;
    private ArrayList<Empleados> listaEmpleados;

    public EmpleadosView() {
        this.scanner = new Scanner(System.in);
        this.empleadosDAO = new EmpleadosDAO();
    }

    public void menuEmpleado() {
        int opcion = -1;
        do {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("** Menú de Empleados **");
            System.out.println("=".repeat(35));
            System.out.println("1. [BUSCAR] Empleado por DNI");
            System.out.println("2. [MODIFICAR] Empleado");
            System.out.println("3. [AÑADIR] Nuevo Empleado");
            System.out.println("4. [ELIMINAR] Empleado");
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
                case 1 -> buscarDNI();
                case 2 -> modificarEmpleado();
                case 3 -> añadirEmpleado();
                case 4 -> eliminarEmpleado();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                }
            }
        } while (opcion != 0);
    }

    public void buscarDNI() {
        System.out.println("\n--- Buscar Empleado por DNI ---");
        String dni = pedirDNI("DNI del empleado a buscar");
        empleado = empleadosDAO.buscarPorDni(dni);

        if (empleado != null) {
            System.out.println("\n--- Datos del Empleado ---");
            System.out.println("DNI: " + empleado.getEmpleadoDni());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Apellido: " + empleado.getApellido());
            System.out.println("Teléfono: " + empleado.getTelefono());
            System.out.println("Email: " + empleado.getEmail());
            System.out.println("Puesto: " + empleado.getPuesto());
            System.out.println("Jornada: " + empleado.getJornada());
            System.out.println("Horas Extras: " + empleado.getHorasExtra());
        } else {
            System.out.println("No se ha encontrado ningún empleado con ese DNI.");
        }
    }

    public void modificarEmpleado() {
        System.out.println("\n--- Modificar Empleado ---");
        String dni = pedirDNI("DNI del empleado a modificar");

        empleado = empleadosDAO.buscarPorDni(dni);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.println("\n--- Datos actuales del empleado ---");
        System.out.println(empleado);

        int opcionModificar = -1;
        do {
            System.out.println("\n--- ¿Qué quieres modificar de " + empleado.getNombre() + " " + empleado.getApellido() + "? ---");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Teléfono");
            System.out.println("4. Email");
            System.out.println("5. Puesto");
            System.out.println("6. Jornada");
            System.out.println("7. Horas Extras");
            System.out.println("0. Volver al menú de empleados");
            System.out.print("Seleccione una opción: ");
            try {
                opcionModificar = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, introduce un número como opción.");
                scanner.next();
                opcionModificar = -1;
                continue;
            }
            scanner.nextLine();

            String atributo = "";
            String valor = "";

            switch (opcionModificar) {
                case 1:
                    atributo = "Nombre";
                    System.out.print("Nuevo nombre: ");
                    valor = scanner.nextLine().trim();
                    break;
                case 2:
                    atributo = "Apellido";
                    System.out.print("Nuevo apellido: ");
                    valor = scanner.nextLine().trim();
                    break;
                case 3:
                    atributo = "Telefono";
                    valor = pedirTelefono();
                    break;
                case 4:
                    atributo = "Email";
                    valor = pedirEmail();
                    break;
                case 5:
                    atributo = "Puesto";
                    System.out.print("Nuevo puesto: ");
                    valor = scanner.nextLine().trim();
                    break;
                case 6:
                    atributo = "Jornada";
                    System.out.print("Nueva jornada: ");
                    valor = scanner.nextLine().trim();
                    break;
                case 7:
                    atributo = "Horas Extras";
                    int horasExtras = pedirHorasExtras();
                    if (horasExtras != -1) {
                        atributo = "Horas Extras";
                        valor = String.valueOf(horasExtras);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú de empleados.");
                    break;
                default:
                    if (opcionModificar != -1) {
                        System.out.println("Opción no válida.");
                    }
            }
            if (!atributo.isEmpty() && !valor.isEmpty()) {
                empleadosDAO.actualizar(atributo, valor, dni);
                System.out.println("Empleado modificado.");
            } else if (atributo.equals("Horas Extras") && valor.equals(String.valueOf(-1))) {
                // No se realiza la actualización si pedirHorasExtras devuelve -1 (error)
            }

        } while (opcionModificar != 0);
    }

    public void añadirEmpleado() {
        System.out.println("\n--- Añadir un Nuevo Empleado ---");
        String dni = pedirDNI("DNI");
        String nombre = pedirNombre();
        String apellido = pedirApellido();
        String telefono = pedirTelefono();
        String email = pedirEmail();
        System.out.print("Puesto: ");
        String puesto = scanner.nextLine().trim();
        System.out.print("Jornada: ");
        String jornada = scanner.nextLine().trim();
        int horasExtras = pedirHorasExtras();
        if (horasExtras == -1) {
            return; // No se añade el empleado si hay un error al introducir las horas extras
        }

        Empleados nuevoEmpleado = new Empleados(dni, nombre, apellido, puesto, email, telefono, jornada, horasExtras);
        empleadosDAO.insertar(nuevoEmpleado);
        System.out.println("Nuevo empleado añadido exitosamente.");
    }

    public void eliminarEmpleado() {
        System.out.println("\n--- Eliminar Empleado ---");
        String dni = pedirDNI("DNI del empleado a eliminar");

        System.out.print("¿Seguro que quieres eliminar a este empleado (Si/No)? ");
        String respuesta = scanner.nextLine().trim();
        if (respuesta.equalsIgnoreCase("Si")) {
            empleadosDAO.eliminar(dni);
            System.out.println("Empleado eliminado.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private String pedirDNI(String prompt) {
        String dni;
        boolean valido;
        do {
            System.out.print(prompt + " (Ejemplo: 12345678X): ");
            dni = scanner.nextLine().trim().toUpperCase();
            valido = validarDNI(dni);
            if (!valido) {
                System.out.println("Error: El formato del DNI no es válido. Debe tener 8 números seguidos de una letra.");
            }
        } while (!valido);
        return dni;
    }

    private boolean validarDNI(String dni) {
        Pattern pattern = Pattern.compile("^[0-9]{8}[A-Z]$");
        Matcher matcher = pattern.matcher(dni);
        return matcher.matches();
    }

    private String pedirNombre() {
        System.out.print("Nombre: ");
        return scanner.nextLine().trim();
    }

    private String pedirApellido() {
        System.out.print("Apellidos: ");
        return scanner.nextLine().trim();
    }

    private String pedirEmail() {
        String email;
        boolean valido;
        do {
            System.out.print("Email (Ejemplo: usuario@dominio.com): ");
            email = scanner.nextLine().trim();
            valido = validarEmail(email);
            if (!valido) {
                System.out.println("Error: El formato del email no es válido.");
            }
        } while (!valido);
        return email;
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String pedirTelefono() {
        String telefono;
        boolean valido;
        do {
            System.out.print("Teléfono (Ejemplo: 6XXXXXXXX o 9XXXXXXXX): ");
            telefono = scanner.nextLine().trim();
            valido = validarTelefono(telefono);
            if (!valido) {
                System.out.println("Error: El formato del teléfono no es válido. Debe empezar por 6 o 9 y tener 9 dígitos.");
            }
        } while (!valido);
        return telefono;
    }

    private boolean validarTelefono(String telefono) {
        Pattern pattern = Pattern.compile("^[69][0-9]{8}$");
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }

    private int pedirHorasExtras() {
        try {
            System.out.print("Horas Extras: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número entero para las horas extras.");
            scanner.next(); // Limpiar el buffer
            return -1; // Devolver un valor especial para indicar error
        } finally {
            scanner.nextLine(); // Consumir la nueva línea
        }
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}