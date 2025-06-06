package Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // URL de conexión a la base de datos MySQL
 private static final String URL = "jdbc:mysql://preda.es:4141/hotel"; // Cambia el nombre de la base de datos
 
  private static final String USUARIO = "hotel"; // Nombre de usuario de MySQL
 
  private static final String CONTRASENA = "GestorHotel6713"; // Contrasena del usuario de MySQL
 
  // Método para establecer la conexión con la base de datos
  public static Connection conectar() {
    try {
  // Establecer la conexión con la base de datos
      return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    } catch (SQLException e) {
      System.out.println("Error al conectar a la base de datos: " + e.getMessage());
      return null;
    }
  }
}