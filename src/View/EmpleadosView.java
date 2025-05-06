package View;

import Dao.EmpleadosDAO;
import Model.Empleados;
import java.util.ArrayList;
import java.util.Scanner;

public class EmpleadosView{

    Scanner sc = new Scanner(System.in);
    Empleados empleado;
    EmpleadosDAO empleadosDAO = new EmpleadosDAO();
    ArrayList<Empleados> listaEmpleados;

    public void menuEmpleado(){

        int opcion;

        System.out.println("Menu de Empleados: ");
        System.out.println("1.Buscar DNI Empleado");
        System.out.println("2.Modificar Empleado");
        System.out.println("3.Añadir Empleado");
        System.out.println("4.Eliminar Empleado");

        opcion = sc.nextInt();
        sc.nextLine();

        switch(opcion){
            case 1: {this.buscarDNI();} break;
            case 2: {this.modificarEmpleado();} break;
            case 3: {this.añadirEmpleado();} break;
            case 4: {this.eliminarEmpleado();} break;
            default: System.out.println("Opción no válida"); break;
        }
    }

    public void buscarDNI(){
        
        System.out.println("DNI del empleado a buscar: ");
        String dni = sc.nextLine();
        empleado = empleadosDAO.buscarPorDni(dni);

        if (empleado !=null){

            System.out.println("Empleado encontrado: ");
            System.out.println("DNI: " + empleado.getEmpleadoDni());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Apellido: " + empleado.getApellido());
            System.out.println("Telefono: " + empleado.getTelefono());
            System.out.println("Email: " + empleado.getEmail());
            System.out.println("Puesto: " + empleado.getPuesto());
            System.out.println("Jornada: " + empleado.getJornada());
            System.out.println("Horas Extras: " + empleado.getHorasExtra());   
        }else{
            System.out.println("No se ha encontrado ningun empleado con ese DNI");
        }
    }

    public void modificarEmpleado(){
        System.out.println("DNI del empleado a modificar: ");
        String dni = sc.nextLine();

        empleado = empleadosDAO.buscarPorDni(dni);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
            }

            System.out.println("Datos actuales del empleado: ");
            System.out.println(empleado);

            System.out.println("¿Que quieres modificar?");
            System.out.println("1.Nombre");
            System.out.println("2.Apellido");
            System.out.println("3.Telefono");
            System.out.println("4.Email");
            System.out.println("5.Puesto");
            System.out.println("6.Jornada");
            System.out.println("7.Horas Extras");

            int opcion = sc.nextInt();
            sc.nextLine();
            String atributo ="";
            String valor ="";

            switch(opcion){
                case 1: {
                    atributo = "Nombre";
                    System.out.println("Nuevo nombre: ");
                    valor = sc.nextLine();
                    break;
                }
                case 2: {
                    atributo = "Apellido";
                    System.out.println("Nuevo apellido: ");
                    valor = sc.nextLine();
                    break;
                }
                case 3: {
                    atributo = "Telefono";
                    System.out.println("Nuevo telefono: ");
                    valor = sc.nextLine();
                    break;
                }
                case 4: {
                    atributo = "Email";
                    System.out.println("Nuevo email: ");
                    valor = sc.nextLine();
                    break;
                }
                case 5: {
                    atributo = "Puesto";
                    System.out.println("Nuevo puesto: ");
                    valor = sc.nextLine();
                    break;
                }
                case 6: {
                    atributo = "Jornada";
                    System.out.println("Nueva jornada: ");
                    valor = sc.nextLine();
                    break;
                }
                case 7: {
                    atributo = "Horas Extras";
                    System.out.println("Nuevas horas extras: ");
                    valor = sc.nextLine();
                    break;
                }
                default: {
                    System.out.println("Opcion no valida");
                    return;
                }
            }
            empleadosDAO.actualizar(atributo, valor, dni);
            System.out.println("Empleado modificado");
    }

    public void añadirEmpleado(){

        System.out.println("Añadir un nuevo empleado");

        System.out.println("DNI: ");
        String dni = sc.nextLine();
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Apellidos: ");
        String apellido = sc.nextLine();
        System.out.println("Telefono: ");
        String telefono = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Puesto: ");
        String puesto = sc.nextLine();
        System.out.println("Jornada: ");
        String jornada = sc.nextLine();
        System.out.println("Horas Extras: ");
        int horasExtras = sc.nextInt();
        sc.nextLine();

        Empleados nuevoEmpleado = new Empleados(dni, nombre, apellido, puesto, email, telefono, jornada, horasExtras);
        empleadosDAO.insertar(nuevoEmpleado);

        System.out.println("Nuevo empleado añadido.");
    }

    public void eliminarEmpleado(){

        System.out.println("Eliminar emplado.");
        System.out.println("DNI del empleado a eliminar: ");
        String dni = sc.nextLine();

        System.out.println("¿Seguro que quieres eliminar este empleado (Si/No)?");
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("Si")) {
            empleadosDAO.eliminar(dni);
            System.out.println("Empleado eliminado.");
        }else{
            System.out.println("Operacion cancelada");
        }
    }

}