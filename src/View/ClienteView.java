package View;
import Model.Cliente; // Importamos la clase Cliente (ahora corregida en Model)
import Dao.ClienteDAO;

import java.util.*;

public class ClienteView {

    // Atributos
    Scanner sc = new Scanner(System.in);
    Cliente cliente;
    ClienteDAO clienteDAO = new ClienteDAO();
    ArrayList<Cliente> listaClientes;


    public void menuCliente(){

        int opcion;

        System.out.println("Bienvenido al menu del cliente");
        System.out.println("1. Añadir cliente");
        System.out.println("2. Modificar cliente");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Ver clientes");

        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion){
            case 1 -> { this.añadirCliente(); }
            case 2 -> { this.modificarCliente(); }
            case 3 -> { this.eliminarCliente(); }
            case 4 -> { this.verClientes(); }
            default -> { System.out.println("Opcion no valida"); }
        }


    }


    public void añadirCliente(){
        System.out.println("Añadir cliente");
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine();
        cliente = new Cliente(dni, nombre, apellido, email, telefono, direccion);

        clienteDAO.insertar(cliente);

        System.out.println("Cliente añadido correctamente");

    }

    public void modificarCliente(){
        System.out.print("DNI del cliente a modificar: ");
        String dni = sc.nextLine();
        String atributo = null;
        int opcion2 ;

        do {

            System.out.println("Modificar cliente");
            System.out.println("Qué quieres modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Email");
            System.out.println("4. Telefono");
            System.out.println("5. Direccion");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> { atributo = "Nombre"; }
                case 2 -> { atributo = "Apellido"; }
                case 3 -> { atributo = "Email"; }
                case 4 -> { atributo = "Telefono"; }
                case 5 -> { atributo = "Direccion"; }
                default -> { System.out.println("Opción no válida");}
            }
            if (atributo != null) {
                System.out.print("Nuevo valor para " + atributo + ": ");
                String valor = sc.nextLine();
                clienteDAO.actualizar(atributo, valor, dni);
                System.out.println("Cliente modificado correctamente");
            }
            System.out.println("Desea modificar otro dato del mismo cliente?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("Opción: ");
            opcion2 = sc.nextInt();
            sc.nextLine();
        } while (opcion2 == 1);

        System.out.println("Saliendo del menu de modificar cliente");

    }


    public void eliminarCliente(){
        String dni;
        int opcion;

        do{
            System.out.println("Eliminar cliente");
            System.out.print("DNI del cliente a eliminar: ");
            dni = sc.nextLine();
            clienteDAO.eliminar(dni);


            System.out.println("Desea eliminar otro cliente?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

        }while (opcion == 1);
        System.out.println("Saliendo del menu de eliminar cliente");
    }


    public void verClientes(){

        int opcion;
        int opcion2;

        System.out.println("Ver clientes");

        do{
            System.out.println("1. Ver todos los clientes");
            System.out.println("2. Ver cliente por DNI");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1-> {
                    listaClientes = clienteDAO.obtenerTodos();
                    if (listaClientes != null && !listaClientes.isEmpty()) {
                        for (Cliente cliente : listaClientes) {
                            System.out.println(cliente.toString());
                        }
                    } else {
                        System.out.println("No hay clientes registrados.");
                    }
                }
                case 2-> {
                    System.out.println("Introduce el DNI del cliente que quieres ver");
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    cliente = clienteDAO.buscarPorDni(dni);
                    if (cliente != null) {
                        System.out.println(cliente.toString());
                    } else {
                        System.out.println("No se encontró ningún cliente con ese DNI.");
                    }
                }
                default -> System.out.println("Opción no válida.");
            }

            System.out.println("Desea ver otro cliente?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("Opción: ");
            opcion2 = sc.nextInt();
            sc.nextLine();


        }while (opcion2 == 1);
        System.out.println("Saliendo del menu de ver clientes");
    }

}