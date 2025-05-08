package View;

import Dao.EventosDAO;
import Model.Eventos;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class EventoView {

    public void menuEventos() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        System.out.println("Menu de Empleados: ");
        System.out.println("1.Buscar por id");
        System.out.println("2.Modificar Evento");
        System.out.println("3.Añadir Evento");
        System.out.println("4.Eliminar Evento");

        opcion = sc.nextInt();
        sc.nextLine();

        switch(opcion){
            case 1: {this.buscarID();} break;
            case 2: {this.gestionarEventos();} break;
            case 3: {this.crearEventos();} break;
            case 4: {this.eventosMostrar();} break;
            default: System.out.println("Opción no válida"); break;
        }
    }

    public void gestionarEventos() {

        EventosDAO evdao = new EventosDAO();
        Scanner sc = new Scanner(System.in);
        int eleccion;
        int respuesta;
        String contesta;
        String nombre = "NombreEvento";
        String fecha = "FechaEvento";
        String hora = "HoraInicio";
        String precio = "Precio";
        String capacidad = "Capacidad";
        

        
        System.out.println("¿Qué cliente desea gestionar? Introduzca su dni: ");
        respuesta = sc.nextInt();
        Eventos eventos = evdao.buscarPorID(respuesta);
        
        
        do{       

            System.out.println("¿Qué desea gestionar de su cliente?");
            System.out.println("1. Nombre");
            System.out.println("2. Fecha");
            System.out.println("3. Hora");
            System.out.println("4. Precio");
            System.out.println("5. Capacidad");
            System.out.println("6. Salir");
            System.out.println("Introduzca una opcion: ");

            eleccion = sc.nextInt();

            switch(eleccion){
                case 1 -> {
                    System.out.println("Introduzca el nombre nuevo: ");
                    contesta = sc.nextLine();sc.next();
                    evdao.actualizar(nombre, contesta, eventos.getEventoID());
                    
                    

                }
                case 2 -> {
                    System.out.println("Introduzca la fecha nueva: ");
                    contesta = sc.nextLine();sc.next();
                    evdao.actualizar(fecha, contesta, eventos.getEventoID());

                    
                }
                case 3 -> {
                    System.out.println("Introduzca la hora nueva: ");
                    contesta = sc.nextLine(); sc.next();
                    evdao.actualizar(hora, contesta, eventos.getEventoID());
                    
                }
                case 4 -> {
                    System.out.println("Introduzca el precio nuevo: ");
                    contesta = sc.nextLine(); sc.next();
                    evdao.actualizar(precio, contesta, eventos.getEventoID());
                }
                case 5 -> {
                    System.out.println("Introduzca la capacidad nueva: ");
                    contesta = sc.nextLine(); sc.next();
                    evdao.actualizar(capacidad, contesta, eventos.getEventoID());
                }
                default -> {
                    System.out.println("Introduzca una opcion válida");
                }
            }
    
            

        }while(eleccion != 6);

        
    }

    public void crearEventos() {
        EventosDAO evdao = new EventosDAO();
        Scanner sc = new Scanner(System.in);
        String nombre;
        Date fecha;
        Time hora;
        Double precio;
        int capacidad;
        int id;

        System.out.println("Bienvenido a la creacion de clientes");
        System.out.println("Introduzca los datos de su cliente: ");

        System.out.println("Id");
        id = sc.nextInt();

        System.out.println("Nombre");
        nombre = sc.nextLine();sc.next();

        System.out.println("fecha");
        fecha = Date.valueOf(sc.nextLine());

        System.out.println("Hora");
        hora = Time.valueOf(sc.nextLine());

        System.out.println("Precio");
        precio = sc.nextDouble();

        System.out.println("Capacidad");
        capacidad = sc.nextInt();

        Eventos evento = new Eventos(id, nombre, fecha, hora, precio, capacidad);
        evdao.insertar(evento);
    }

    public void eliminarEventos() {

        Scanner sc = new Scanner(System.in);
        EventosDAO evdao = new EventosDAO();
        int respuesta;
        boolean control = false;

        if (evdao.obtenerTodos() != null) {

            do{
                System.out.println("¿Qué evento desea eliminar?");
                System.out.println("Introduzca su ID: ");
                respuesta = sc.nextInt();
            
                
                if ( evdao.buscarPorID(respuesta) != null) {
        
                    evdao.eliminar(respuesta);
                    System.out.println("Evento eliminado con exito");
                    control = true;
                } else {
                    System.out.println("El ID que ha introducido no esta relacionado con ningun cliente existente");
                    
                }
    
            }while(!control);

        } else {
            System.out.println("Si no hay eventos como vas a borrarlos, iluminado");
        }
            
        

    }

    public void eventosMostrar() {
        EventosDAO evdao = new EventosDAO();

        if (evdao.obtenerTodos() != null) {
            ArrayList<Eventos> eventos = evdao.obtenerTodos();
            for (Eventos evento : eventos) {
                System.out.println(evento);
            }
            System.out.println("Eventos obtenidos");
            
            
            
        }else {
            System.out.println("¿Otra vez intentando conseguir cosas que no existen?");
            
        }
    }

    public void buscarID(){
        Scanner sc = new Scanner(System.in);
        EventosDAO evdao = new EventosDAO();
        Eventos evento;
        
        System.out.println("ID del evento a buscar: ");
        int id = sc.nextInt();
        evento = evdao.buscarPorID(id);

        if (evento !=null){

            System.out.println("Empleado encontrado: ");
            System.out.println("ID: " + evento.getEventoID());
            System.out.println("Nombre: " + evento.getNombreEvento());
            System.out.println("Fecha: " + evento.getFechaEvento());
            System.out.println("Hora: " + evento.getHoraInicio());
            System.out.println("Precio: " + evento.getPrecio());
            System.out.println("Capacidad: " + evento.getCapacidad());
               
        }else{
            System.out.println("No se ha encontrado ningun evento con ese ID");
        }
    }
}