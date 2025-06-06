package Dao;

import Model.Eventos;
import java.sql.*;
import java.util.ArrayList;

public class EventosDAO {
    public void insertar(Eventos evento) {
        Connection conexion = ConexionDB.conectar();
        java.util.Date fechaEvento = evento.getFechaEvento();
        if (conexion != null) {
            String query = "INSERT INTO Eventos (EventoID, NombreEvento, FechaEvento, HoraInicio, Precio, Capacidad) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, evento.getEventoID()); // Asigna el valor del teléfono
                stmt.setString(2, evento.getNombreEvento()); 
                stmt.setDate(3, new java.sql.Date(fechaEvento.getTime()));                
                stmt.setTime(4, evento.getHoraInicio());
                stmt.setDouble(5, evento.getPrecio());
                stmt.setInt(6, evento.getCapacidad());
                
                stmt.executeUpdate(); // Ejecuta la consulta de inserción
                
                
            }catch (SQLException e) {
                System.out.println("Error al agregar evento: " + e.getMessage());
            }
        }

    }

    public void actualizar(String atributo, String valor, int id) {
    Connection conexion = ConexionDB.conectar();
    if (conexion == null) {
        System.out.println("Error de conexión a la base de datos");
        return;
    }

    // Validar que el atributo es uno de los permitidos (seguridad contra SQL injection)
    String[] columnasPermitidas = {"NombreEvento", "FechaEvento", "HoraInicio", "Precio", "Capacidad"};
    boolean atributoValido = false;
    
    for (String columna : columnasPermitidas) {
        if (columna.equals(atributo)) {
            atributoValido = true;
            break;
        }
    }

    if (!atributoValido) {
        System.out.println("Error: Atributo no válido para modificación");
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
        return;
    }

    String query = "UPDATE Eventos SET " + atributo + " = ? WHERE EventoID = ?";
    
    try (PreparedStatement stmt = conexion.prepareStatement(query)) {
        // Asignar el valor según el tipo de dato correspondiente
        switch(atributo) {
            case "NombreEvento":
                stmt.setString(1, valor);
                break;
            case "FechaEvento":
                stmt.setDate(1, java.sql.Date.valueOf(valor));
                break;
            case "HoraInicio":
                stmt.setTime(1, java.sql.Time.valueOf(valor + ":00")); // Asegurar formato HH:MM:SS
                break;
            case "Precio":
                stmt.setDouble(1, Double.parseDouble(valor));
                break;
            case "Capacidad":
                stmt.setInt(1, Integer.parseInt(valor));
                break;
        }
        
        stmt.setInt(2, id);
        
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Campo '" + atributo + "' actualizado correctamente");
        } else {
            System.out.println("No se encontró evento con ID " + id + " o no hubo cambios");
        }
    } catch (SQLException e) {
        System.out.println("Error SQL al actualizar: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.out.println("Error: Formato incorrecto para " + atributo + " - " + e.getMessage());
    } finally {
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}

    public void eliminar(int id) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {String query = "DELETE FROM Eventos WHERE EventoID = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {stmt.setInt(1, id); // Asigna el ID del cliente
                stmt.executeUpdate(); // Ejecuta la eliminación
            } catch (SQLException e) {
                System.out.println("Error al eliminar evento: " + e.getMessage());
            }
        }

    }

    public Eventos buscarPorID(int id){
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            Eventos evento;
            String nombre;
            Date fecha;
            Time hora;
            Double precio;
            int capacidad;
        
            PreparedStatement stmt = null;
            ResultSet rs = null;
        

            try {
                String query = "SELECT EventoID, NombreEvento, FechaEvento, HoraInicio, Precio, Capacidad " + "FROM Eventos WHERE EventoID = ?";

                stmt = conexion.prepareStatement(query);
                stmt.setInt(1, id); 

                rs = stmt.executeQuery();

                if (rs.next()) {
                    // Extraemos los datos del ResultSet
                    nombre = rs.getString("NombreEvento");
                    fecha = rs.getDate("FechaEvento");
                    hora = rs.getTime("HoraInicio");
                    precio = rs.getDouble("Precio");
                    capacidad = rs.getInt("Capacidad");
                    

                    evento = new Eventos(id, nombre, fecha, hora, precio, capacidad);
                    return evento;
                }
                

            } catch (SQLException e) {
                System.err.println("Error al buscar evento por ID: " + e.getMessage());
                // Podrías lanzar una excepción personalizada aquí si lo prefieres
            } finally {
                // Cerramos recursos en orden inverso a su creación
                try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignorar */ }
                try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
                
            }

        
        }
        return null;
    }

    public ArrayList<Eventos> obtenerTodos() {
              // Establecer conexión
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            // Consulta SQL para obtener todos los Cliente
            String query = "SELECT * FROM Eventos"; 
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                ArrayList<Eventos> eventos = new ArrayList<>();
                Eventos evento; 
                String nombre;
                Date fecha;
                Time hora;
                Double precio;
                int capacidad;
                int id;
                
                // Iterar sobre los resultados
                while (rs.next()) {
                    
                    nombre = rs.getString("NombreEvento");
                    fecha = rs.getDate("FechaEvento");
                    hora = rs.getTime("HoraInicio");
                    id = rs.getInt("EventoID");
                    capacidad = rs.getInt("Capacidad");
                    precio = rs.getDouble("Precio");
                    evento = new Eventos(id, nombre, fecha, hora, precio, capacidad);
                    eventos.add(evento);

                }  

                return eventos;

            }catch (SQLException e) {
                System.out.println("Error al realizar la consulta: " + e.getMessage());
            }
        }
        return null;
    }
}
