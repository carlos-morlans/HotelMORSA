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
            case 1 -> {this.buscarDNI();}
            case 2 -> {this.modificarEmpleado();}
            case 3 -> {this.añadirEmpleado();}
            case 4 -> {this.eliminarEmpleado();}
            default -> System.out.println("Opción no válida");

        }

    }

    public void buscarDNI(){
        
        System.out.println("Ingrese el DNI del empleado a buscar:");
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
            System.out.println("Horas Extras: " + empleado.getHorasExtras());   
        }else{
            System.out.println("No se ha encontrado ningun empleado con ese DNI");
        }
    }

    public void modificarEmpleado(){
        System.out.println("DNI del empleado a modificar:");
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
            

    }

    public void añadirEmpleado(){
    }

    public void eliminarEmpleado(){
    }

}