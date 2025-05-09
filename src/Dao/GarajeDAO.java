package Dao;

import Model.Garaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GarajeDAO {
    public void insertar(Garaje garaje) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Garaje (NumeroPlaza,  Estado) VALUES (?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, garaje.getNumeroPlaza()); 
                stmt.setString(2, garaje.getEstado()); 
  
                stmt.executeUpdate(); // Ejecuta la consulta de inserción
                
                
            }catch (SQLException e) {
                System.out.println("Error al agregar la plaza: " + e.getMessage());
            }
        }

    }

    public void modificarNumeroPlaza(int numeroPlaza) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Garaje SET numeroPlaza = ? WHERE numeroPlaza = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, numeroPlaza);
                ps.setInt(2, numeroPlaza);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al modificar el numero de la plaza: " + e.getMessage());
            }
        }
    }

    public void modificarEstado(int numeroPlaza, String estado) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "UPDATE Garaje SET Estado  = ? WHERE NumeroPlaza  = ?";
            try (PreparedStatement ps = conexion.prepareStatement(query)) {
                ps.setInt(1, numeroPlaza);
                ps.setString(2, estado);
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al modificar el numero de la plaza: " + e.getMessage());
            }
        }
    }

    public void eliminarPlazaGaraje(int numeroPlaza) {

        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {String query = "DELETE FROM Garaje WHERE numeroPlaza = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {stmt.setInt(1, numeroPlaza); // Asigna el ID del cliente
            stmt.executeUpdate(); // Ejecuta la eliminación
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar la plaza: " + e.getMessage());
        }
    }
    }

    public void buscarPlaza(int numeroPlaza) {
    String query = "SELECT * FROM Garaje WHERE NumeroPlaza = ?";
    
    try (Connection conexion = ConexionDB.conectar();
         PreparedStatement stmt = conexion.prepareStatement(query)) {
        
        // Asignar el parámetro a la consulta
        stmt.setInt(1, numeroPlaza);
        
        // Ejecutar la consulta y procesar resultados
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("\n--- Plaza Encontrada ---");
                System.out.println("Número de Plaza: " + rs.getInt("NumeroPlaza"));
                System.out.println("Estado: " + rs.getString("Estado"));
            } else {
                System.out.println("No se encontró ninguna plaza con el número: " + numeroPlaza);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error en la búsqueda: " + e.getMessage());
        e.printStackTrace(); 
    }
}
}
