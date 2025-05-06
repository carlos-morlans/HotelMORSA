package View;

import Dao.GarajeDAO;
import Model.Garaje;
import java.util.Scanner;

public class GarajeView {
    
    Garaje nuevogaraje;
    GarajeDAO garajeDAO = new GarajeDAO();
    
    public void mostrarMenuGaraje() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Menu de Garaje: ");
        System.out.println("1.Crear Plaza");
        System.out.println("2.Modificar Plaza");
        System.out.println("3.Buscar por numero de Plaza");
        System.out.println("4.Eliminar Empleado");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch(opcion){
            case 1: {this.crearPlaza();} break;
            case 2: {this.modificarPlaza();} break;
            case 3: {this.BuscarPorPlaza();} break;
            case 4: {this.eliminarPlaza();} break;
            default: System.out.println("Opción no válida"); break;
        }
    }

    public void crearPlaza() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("  Introduce el Numero de Plaza: ");
        int numeroPlaza = scanner.nextInt();

        System.out.println("  Introduce el estado de la plaza: ");
        String estado = scanner.nextLine();

        Garaje nuevogaraje = new Garaje(numeroPlaza, estado);
 
        garajeDAO.insertar(nuevogaraje);
 
        System.out.println("Plaza creada correctamente");


    }

    public void modificarPlaza(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Modificar plaza");
        System.out.print("Numero de plaza: ");
        int numeroPlaza = sc.nextInt();
        System.out.println("1.estado");

        System.out.println("  Introduce el nuevo esttado: (ocuapado/libre)");
        String estado = sc.nextLine();
        garajeDAO.modificarEstado(numeroPlaza, estado);
        System.out.println("estado modificado correctamente.");
                
        System.out.println("Desea modificar otro dato?");
        System.out.println("1. Si");
        System.out.println("2. No");
        int opcion = sc.nextInt();
        switch(opcion){
            case 1: {this.modificarPlaza();} break;
            case 2: {this.mostrarMenuGaraje();} break;
            default: System.out.println("Opción no válida"); break;
        }
    }

    public void BuscarPorPlaza(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el numero de la plaza que quieres buscar: ");
        int numeroPlaza = sc.nextInt();
        garajeDAO.buscarPlaza(numeroPlaza);

    }

    public void eliminarPlaza(){

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Eliminar plaza.");
        System.out.println("Escribe el numero de la plaza que queires eliminar: ");
        int numeroPlaza = sc.nextInt();

        System.out.println("¿Seguro que quieres eliminar este empleado (Si/No)?");
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("Si")) {
            garajeDAO.eliminarPlazaGaraje(numeroPlaza);
            System.out.println("Empleado eliminado.");
        }else{
            System.out.println("Operacion cancelada");
        }
    }
}
