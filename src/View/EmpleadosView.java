package View;

import Dao.EmpleadosDAO;
import Model.Empleados;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        System.out.print("DNI del empleado a buscar: ");
        String dni = scanner.nextLine();
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
        System.out.print("DNI del empleado a modificar: ");
        String dni = scanner.nextLine();

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
                case 1: {
                    atributo = "Nombre";
                    System.out.print("Nuevo nombre: ");
                    valor = scanner.nextLine();
                    break;
                }
                case 2: {
                    atributo = "Apellido";
                    System.out.print("Nuevo apellido: ");
                    valor = scanner.nextLine();
                    break;
                }
                case 3: {
                    atributo = "Telefono";
                    System.out.print("Nuevo teléfono: ");
                    valor = scanner.nextLine();
                    break;
                }
                case 4: {
                    atributo = "Email";
                    System.out.print("Nuevo email: ");
                    valor = scanner.nextLine();
                    break;
                }
                case 5: {
                    atributo = "Puesto";
                    System.out.print("Nuevo puesto: ");
                    valor = scanner.nextLine();
                    break;
                }
                case 6: {
                    atributo = "Jornada";
                    System.out.print("Nueva jornada: ");
                    valor = scanner.nextLine();
                    break;
                }
                case 7: {
                    atributo = "Horas Extras";
                    System.out.print("Nuevas horas extras: ");
                    valor = scanner.nextLine();
                    break;
                }
                case 0: {
                    System.out.println("Volviendo al menú de empleados.");
                    break;
                }
                default: {
                    if (opcionModificar != -1) {
                        System.out.println("Opción no válida.");
                    }
                }
            }
            if (!atributo.isEmpty()) {
                empleadosDAO.actualizar(atributo, valor, dni);
                System.out.println("Empleado modificado.");
            }
        } while (opcionModificar != 0);
    }

    public void añadirEmpleado() {
        System.out.println("\n--- Añadir un Nuevo Empleado ---");
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellido = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Puesto: ");
        String puesto = scanner.nextLine();
        System.out.print("Jornada: ");
        String jornada = scanner.nextLine();
        int horasExtras = 0;
        try {
            System.out.print("Horas Extras: ");
            horasExtras = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, introduce un número para las horas extras.");
            scanner.next(); // Limpiar el buffer
            return; // O podrías establecer un valor por defecto y continuar
        }
        scanner.nextLine(); // Consumir la nueva línea

        Empleados nuevoEmpleado = new Empleados(dni, nombre, apellido, puesto, email, telefono, jornada, horasExtras);
        empleadosDAO.insertar(nuevoEmpleado);
        System.out.println("Nuevo empleado añadido exitosamente.");
    }

    public void eliminarEmpleado() {
        System.out.println("\n--- Eliminar Empleado ---");
        System.out.print("DNI del empleado a eliminar: ");
        String dni = scanner.nextLine();

        System.out.print("¿Seguro que quieres eliminar a este empleado (Si/No)? ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("Si")) {
            empleadosDAO.eliminar(dni);
            System.out.println("Empleado eliminado.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}