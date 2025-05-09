package Dao;

import Model.Habitaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HabitacionDAO {

    public void insertar(Habitaciones habitacion) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Habitaciones (NumeroHabitacion, TipoHabitacion, Capacidad, PrecioNoche, Estado) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, habitacion.getNumeroHabitacion()); // Asigna el valor del teléfono
                stmt.setString(2, habitacion.getTipoHabitacion()); 
                stmt.setInt(3, habitacion.getCapacidad());
                stmt.setDouble(4, habitacion.getPrecioNoche());
                stmt.setString(5, habitacion.getEstado());
                
                stmt.executeUpdate(); // Ejecuta la consulta de inserción
                System.out.println("Habitacion agregada exitosamente.");
                
            }catch (SQLException e) {
                System.out.println("Error al agregar habitacion: " + e.getMessage());
            }
        }

    }

    public void actualizar(String atributo, String valor, int numeroHabitacion) {
        String[] columnasPermitidas = {"TipoHabitacion", "Capacidad", "PrecioNoche", "Estado"};
        boolean atributoValido = false;
        
        for (String columna : columnasPermitidas) {
            if (columna.equals(atributo)) {
                atributoValido = true;
                break;
            }
        }

        if (!atributoValido) {
            System.out.println("Error: Atributo no válido para modificación");
            return;
        }

        String query = "UPDATE Habitaciones SET " + atributo + " = ? WHERE NumeroHabitacion = ?";
        
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            
            // Convertir y asignar el valor según el tipo de dato
            switch(atributo) {
                case "TipoHabitacion":
                case "Estado":
                    stmt.setString(1, valor);
                    break;
                case "Capacidad":
                    stmt.setInt(1, Integer.parseInt(valor));
                    break;
                case "PrecioNoche":
                    stmt.setDouble(1, Double.parseDouble(valor));
                    break;
            }
            
            stmt.setInt(2, numeroHabitacion);
            
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Campo '" + atributo + "' actualizado correctamente");
            } else {
                System.out.println("No se encontró habitación con número " + numeroHabitacion);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al actualizar: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Formato numérico incorrecto para " + atributo);
        }
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

    public Habitaciones buscarPorNumero(int numeroHabitacion) {
        String query = "SELECT NumeroHabitacion, TipoHabitacion, Capacidad, PrecioNoche, Estado " + 
                      "FROM Habitaciones WHERE NumeroHabitacion = ?";
        
        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement stmt = conexion.prepareStatement(query)) {
            
            stmt.setInt(1, numeroHabitacion);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Habitaciones(
                        rs.getInt("NumeroHabitacion"),
                        rs.getString("TipoHabitacion"),
                        rs.getInt("Capacidad"),
                        rs.getDouble("PrecioNoche"),
                        rs.getString("Estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar habitación: " + e.getMessage());
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
