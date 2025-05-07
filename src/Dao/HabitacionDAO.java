package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Habitaciones;

public class HabitacionDAO {

    public void insertar(Habitaciones habitacion) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Habitaciones (NumeroHabitacion, TipoHabitacion, Capacidad, PrecioNoche, Estado) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, habitacion.getNumero()); // Asigna el valor del teléfono
                stmt.setString(2, habitacion.getTipo()); 
                stmt.setInt(3, habitacion.getCapacidad());
                stmt.setDouble(4, habitacion.getPrecio());
                stmt.setString(5, habitacion.getEstado());
                
                stmt.executeUpdate(); // Ejecuta la consulta de inserción
                System.out.println("Habitacion agregada exitosamente.");
                
            }catch (SQLException e) {
                System.out.println("Error al agregar habitacion: " + e.getMessage());
            }
        }

    }

    public void actualizar(String atributo, String valor, String numero) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
           
                String query = "UPDATE Habitaciones SET ? = ? WHERE NumeroHabitacion = ?";
                try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                    
                    stmt.setString(1, atributo ); // Columna que deseamos cambiar
                    stmt.setString(2,  valor); // valor que le queremos dar
                    stmt.setString(3, numero); // Asigna el numero de la habitacion
                    stmt.executeUpdate(); // Ejecuta la actualización
                    
                } catch (SQLException e) {
                    System.out.println("Error al actualizar" + atributo + ":" + e.getMessage());
                }

            
            
            }
            System.out.println("No se ha podido conectar con la base de datos");
        
           
        

    }

    public void eliminar(String numero) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {String query = "DELETE FROM Habitaciones WHERE NumeroHabitacion = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {stmt.setString(1, numero); // Asigna el numero de habitacion
                stmt.executeUpdate(); // Ejecuta la eliminación
                System.out.println("habitacion eliminada.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar habitacion: " + e.getMessage());
            }
        }

    }

    public Habitaciones buscarPorNumero(int numero){
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            Habitaciones habitacion;
            String tipo;
            int capacidad;
            double precio;
            String estado;
        
            PreparedStatement stmt = null;
            ResultSet rs = null;
        

            try {
                String query = "SELECT NumeroHabitacion, TipoHabitacion, Capacidad, PrecioNoche, Estado" + "FROM Habitaciones WHERE NumeroHabitacion = ?";

                stmt = conexion.prepareStatement(query);
                stmt.setInt(1, numero.trim()); // Usamos trim() para limpiar espacios

                rs = stmt.executeQuery();

                if (rs.next()) {
                    // Extraemos los datos del ResultSet
                    numero = rs.getInt("NumeroHabitacion");
                    tipo = rs.getString("TipoHabitacion");
                    capacidad = rs.getInt("Capacidad");
                    precio = rs.getDouble("PrecioNoche");
                    estado = rs.getString("Estado");
                    

                    habitacion = new Habitaciones(numero, tipo, capacidad, precio, estado);
                    return habitacion;
                }


                } catch (SQLException e) {
                System.err.println("Error al buscar habitación por número: " + e.getMessage());
                // Podrías lanzar una excepción personalizada aquí si lo prefieres
            } finally {
                // Cerramos recursos en orden inverso a su creación
                try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignorar */ }
                try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
                
            }

        
        }
        return null;
    }

    public ArrayList<Habitaciones> obtenerTodos() {
              // Establecer conexión
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            // Consulta SQL para obtener todos los Cliente
            String query = "SELECT * FROM Habitaciones"; 
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                ArrayList<Habitaciones> habitaciones = new ArrayList<>();
                Habitaciones habitacion; 
                String tipo;
                int capacidad;
                double precio;
                String estado;
                int numero;
                
                // Iterar sobre los resultados
                while (rs.next()) {
                    numero = rs.getInt("NumeroHabitacion");
                    tipo = rs.getString("TipoHabitacion");
                    capacidad = rs.getInt("Capacidad");
                    precio = rs.getDouble("PrecioNoche");
                    estado = rs.getString("Estado");
                    habitacion = new Habitaciones(numero, tipo, capacidad, precio, estado);
                    habitaciones.add(habitacion);

                }  

                return habitaciones;

            }catch (SQLException e) {
                System.out.println("Error al realizar la consulta: " + e.getMessage());
            }
        }
        return null;
    }
}
