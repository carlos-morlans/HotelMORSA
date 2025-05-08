package Dao;
import Model.Clientes; // Importamos la clase Clientes (corregido)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO {

    public void insertar(Clientes cliente) { // Usamos Clientes
        Connection conexion = null; // Inicializamos a null
        PreparedStatement stmt = null;
        try {
            conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "INSERT INTO Clientes (ClienteDni, Nombre, Apellido, Email, Telefono, Direccion) VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(query);
                stmt.setString(1, cliente.getClienteDni()); // Asigna el DNI
                stmt.setString(2, cliente.getNombre()); // Usamos getNombre()
                stmt.setString(3, cliente.getApellido()); // Usamos getApellido()
                stmt.setString(4, cliente.getEmail());
                stmt.setString(5, cliente.getTelefono());
                stmt.setString(6, cliente.getDireccion());

                stmt.executeUpdate(); // Ejecuta la consulta de inserción
                System.out.println("Cliente agregado exitosamente.");
            } else {
                System.out.println("No se ha podido conectar con la base de datos para insertar.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        } finally {
            // Aseguramos cerrar la conexión y el statement en el bloque finally
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (conexion != null) conexion.close(); } catch (SQLException e) { /* ignorar */ }
        }

    }

    public void actualizar(String atributo, String valor, String dni) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        try {
            conexion = ConexionDB.conectar();
            if (conexion != null) {
                // Usamos concatenación segura para el nombre de la columna
                String query = "UPDATE Clientes SET " + atributo + " = ? WHERE ClienteDni = ?";
                stmt = conexion.prepareStatement(query);
                stmt.setString(1, valor); // Nuevo valor
                stmt.setString(2, dni); // DNI del cliente a actualizar
                int filasActualizadas = stmt.executeUpdate(); // Ejecuta la actualización
                if (filasActualizadas > 0) {
                    System.out.println("Cliente modificado correctamente.");
                } else {
                    System.out.println("No se encontró ningún cliente con ese DNI para actualizar.");
                }

            } else {
                System.out.println("No se ha podido conectar con la base de datos para actualizar.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar " + atributo + ": " + e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (conexion != null) conexion.close(); } catch (SQLException e) { /* ignorar */ }
        }
    }

    public void eliminar(String dni) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        try {
            conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "DELETE FROM Clientes WHERE ClienteDni = ?";
                stmt = conexion.prepareStatement(query);
                stmt.setString(1, dni); // Asigna el DNI del cliente
                int filasEliminadas = stmt.executeUpdate(); // Ejecuta la eliminación
                if (filasEliminadas > 0) {
                    System.out.println("Cliente eliminado.");
                } else {
                    System.out.println("No se encontró ningún cliente con ese DNI para eliminar.");
                }
            } else {
                System.out.println("No se ha podido conectar con la base de datos para eliminar.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (conexion != null) conexion.close(); } catch (SQLException e) { /* ignorar */ }
        }
    }

    public Clientes buscarPorDni(String dni){ // Usamos Clientes
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes cliente = null; // Usamos Clientes

        try {
            conexion = ConexionDB.conectar();
            if (conexion != null) {
                String query = "SELECT Nombre, Apellido, ClienteDni, Direccion, Telefono, Email " +
                               "FROM Clientes WHERE ClienteDni = ?";
                stmt = conexion.prepareStatement(query);
                stmt.setString(1, dni.trim()); // Usamos trim() para limpiar espacios
                rs = stmt.executeQuery();

                if (rs.next()) {
                    String nombre = rs.getString("Nombre");
                    String apellido = rs.getString("Apellido"); // Correcto nombre de la columna
                    String clienteDni = rs.getString("ClienteDni");
                    String direccion = rs.getString("Direccion");
                    String telefono = rs.getString("Telefono"); // Correcto nombre de la columna
                    String email = rs.getString("Email");

                    cliente = new Clientes(clienteDni, nombre, apellido, email, telefono, direccion); // Usamos Clientes
                }
            } else {
                System.out.println("No se ha podido conectar con la base de datos para buscar.");
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por DNI: " + e.getMessage());
            // Podrías lanzar una excepción personalizada aquí si lo prefieres
        } finally {
            // Cerramos recursos en orden inverso a su creación
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (conexion != null) conexion.close(); } catch (SQLException e) { /* ignorar */ }
        }
        return cliente;
    }

    public ArrayList<Clientes> obtenerTodos() { // Usamos Clientes
        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Clientes> clientes = new ArrayList<>(); // Usamos Clientes

        try {
            conexion = ConexionDB.conectar();
            if (conexion != null) {
                // Consulta SQL para obtener todos los Clientes
                String query = "SELECT ClienteDni, Nombre, Apellido, Email, Telefono, Direccion FROM Clientes";
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(query);

                // Iterar sobre los resultados
                while (rs.next()) {
                    String clienteDni = rs.getString("ClienteDni");
                    String nombre = rs.getString("Nombre");
                    String telefono = rs.getString("Telefono");
                    String apellido = rs.getString("Apellido"); // Correcto nombre de la columna
                    String direccion = rs.getString("Direccion");
                    String email = rs.getString("Email");
                    Clientes cliente = new Clientes(clienteDni, nombre, apellido, email, telefono, direccion); // Usamos Clientes
                    clientes.add(cliente);
                }
            } else {
                System.out.println("No se ha podido conectar con la base de datos para obtener todos los clientes.");
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
            try { if (conexion != null) conexion.close(); } catch (SQLException e) { /* ignorar */ }
        }
        return clientes;
    }

}