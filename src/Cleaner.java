import Dao.ClienteDAO;
import java.sql.Connection;

import Dao.ConexionDB;
import Model.RegistroEmpleado;
import Dao.RegistroEmpleadoDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Cleaner {

    //Connection conexion = null;

    //horas mensuales = 40 horas semanales * 4 semanas = 160 horas mensuales aproximadamente

    //Meter en HistorialPagos el ultimo dia del mes el salario + (salario/horasMensuales * horasExtra), fecha(fechaActual), Concepto(Gastos), reservaID(null), dniEmpleado(dniEmpleado), tipo("Gastos"), cantidad(salario + (salario/horasMensuales * horasExtra))
    //Reset horasExtra en tabla empleados


    public static void main(String[] args) {
        Connection conexion = ConexionDB.conectar();

        if (conexion == null) {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            return; 
        }

        RegistroEmpleadoDAO registroEmpleadoDAO = new RegistroEmpleadoDAO(conexion);

    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n--- Menú de Registro de Empleados ---");
        System.out.println("1. Crear Registro de Empleado");
        System.out.println("2. Mostrar Todos los Registros");
        System.out.println("3. Mostrar Registro por ID");
        System.out.println("4. Actualizar Registro");
        System.out.println("5. Eliminar Registro");
        System.out.println("6. Salir");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el DNI del empleado: ");
                String dni = scanner.nextLine();
                System.out.print("Ingrese el tipo de registro (Entrada/Salida): ");
                String tipo = scanner.nextLine();
                System.out.print("Ingrese la hora (HH:mm:ss): ");
                String hora = scanner.nextLine();
                System.out.print("Ingrese la fecha (AAAA-MM-DD): ");
                String fecha = scanner.nextLine();

                try {
                    if (dni == null || dni.isEmpty() || tipo == null || tipo.isEmpty() || hora == null || fecha == null) {
                        System.out.println("Error: Todos los campos son obligatorios.");
                        break;
                    }
                    RegistroEmpleado nuevoRegistro = new RegistroEmpleado();
                    nuevoRegistro.setEmpleadoDni(dni);
                    nuevoRegistro.setTipo(tipo);
                    nuevoRegistro.setHora(Time.valueOf(hora));
                    nuevoRegistro.setFechaRegistro(java.sql.Date.valueOf(fecha));
                    registroEmpleadoDAO.insertarRegistro(nuevoRegistro);
                    System.out.println("Registro de empleado creado exitosamente.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Formato de hora o fecha incorrecto. Use HH:mm:ss y AAAA-MM-DD.");
                } catch (SQLException e) {
                    System.out.println("Error al crear el registro: " + e.getMessage());
                }
                break;
            case 2:
                try {
                    List<RegistroEmpleado> registros = registroEmpleadoDAO.obtenerTodosLosRegistros();
                    if (registros.isEmpty()) {
                        System.out.println("No hay registros de empleados.");
                    } else {
                        System.out.println("Lista de Registros de Empleados:");
                        for (RegistroEmpleado registro : registros) {
                            System.out.println(registro);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error al obtener los registros: " + e.getMessage());
                }
                break;
            case 3:
                System.out.print("Ingrese el ID del registro a mostrar: ");
                int idMostrar = scanner.nextInt();
                scanner.nextLine();
                try {
                    RegistroEmpleado registro = registroEmpleadoDAO.obtenerRegistroPorId(idMostrar);
                    if (registro == null) {
                        System.out.println("No existe un registro de empleado con el ID: " + idMostrar);
                    } else {
                        System.out.println("Registro de Empleado con ID " + idMostrar + ":");
                        System.out.println(registro);
                    }
                } catch (SQLException e) {
                    System.out.println("Error al obtener el registro: " + e.getMessage());
                }
                break;
            case 4:
                System.out.print("Ingrese el ID del registro a actualizar: ");
                int idActualizar = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese el nuevo DNI del empleado: ");
                String nuevoDni = scanner.nextLine();
                System.out.print("Ingrese el nuevo tipo de registro (Entrada/Salida): ");
                String nuevoTipo = scanner.nextLine();
                System.out.print("Ingrese la nueva hora (HH:mm:ss): ");
                String nuevaHora = scanner.nextLine();
                System.out.print("Ingrese la nueva fecha (AAAA-MM-DD): ");
                String nuevaFecha = scanner.nextLine();
                try {
                    if (nuevoDni == null || nuevoDni.isEmpty() || nuevoTipo == null || nuevoTipo.isEmpty() || nuevaHora == null || nuevaFecha == null) {
                        System.out.println("Error: Todos los campos son obligatorios.");
                        break;
                    }
                    RegistroEmpleado registroExistente = registroEmpleadoDAO.obtenerRegistroPorId(idActualizar);
                    if (registroExistente == null) {
                        System.out.println("No existe un registro con el id proporcionado");
                        break;
                    }
                    RegistroEmpleado registroActualizado = new RegistroEmpleado();
                    registroActualizado.setRegistroID(idActualizar);
                    registroActualizado.setEmpleadoDni(nuevoDni);
                    registroActualizado.setTipo(nuevoTipo);
                    registroActualizado.setHora(Time.valueOf(nuevaHora));
                    registroActualizado.setFechaRegistro(java.sql.Date.valueOf(nuevaFecha));
                    registroEmpleadoDAO.actualizarRegistro(registroActualizado);
                    System.out.println("Registro actualizado correctamente");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Formato de hora o fecha incorrecto. Use HH:mm:ss y AAAA-MM-DD.");
                } catch (SQLException e) {
                    System.out.println("Error al actualizar el registro: " + e.getMessage());
                }
                break;
            case 5:
                System.out.print("Ingrese el ID del registro a eliminar: ");
                int idEliminar = scanner.nextInt();
                scanner.nextLine();
                try {
                    RegistroEmpleado registroExistente = registroEmpleadoDAO.obtenerRegistroPorId(idEliminar);
                    if (registroExistente == null) {
                        System.out.println("No existe un registro con el id proporcionado");
                        break;
                    }
                    registroEmpleadoDAO.eliminarRegistro(idEliminar);
                    System.out.println("Registro eliminado correctamente.");
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el registro: " + e.getMessage());
                }
                break;
            case 6:
                System.out.println("Saliendo de la aplicación.");
                scanner.close();
                return;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }
}
}