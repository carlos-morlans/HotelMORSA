package Dao;

import Model.Empleados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadosDAO {

    public void insertar(Empleados empleado) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Empleados (EmpleadoDni, Nombre, Apellido, Puesto, Email, Telefono, Jornada, HorasExtra) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, empleado.getEmpleadoDni()); // Asigna el valor del teléfono
                stmt.setString(2, empleado.getNombre()); 
                stmt.setString(3, empleado.getApellido());
                stmt.setString(4, empleado.getPuesto());
                stmt.setString(5, empleado.getEmail());
                stmt.setString(6, empleado.getTelefono());
                stmt.setString(7, empleado.getJornada());
                stmt.setInt(8, empleado.getHorasExtra());
                
                stmt.executeUpdate(); // Ejecuta la consulta de inserción
                
                
            }catch (SQLException e) {
                System.out.println("Error al agregar empleado: " + e.getMessage());
            }
        }

    }

    public void actualizar(String atributo, String valor, String dni) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
           
                String query = "UPDATE Empleados SET ? = ? WHERE EmpleadoDni = ?";
                try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                    
                    stmt.setString(1, atributo ); // Columna que deseamos cambiar
                    stmt.setString(2,  valor); // valor que le queremos dar
                    stmt.setString(3, dni); // Asigna el ID del empleado
                    stmt.executeUpdate(); // Ejecuta la actualización
                    
                } catch (SQLException e) {
                    System.out.println("Error al actualizar" + atributo + ":" + e.getMessage());
                }

            
            
            }
            System.out.println("No se ha podido conectar con la base de datos");
        
           
        

    }

    public void eliminar(String dni) {
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {String query = "DELETE FROM Empleados WHERE EmpleadoDni = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {stmt.setString(1, dni); // Asigna el ID del empleado
                stmt.executeUpdate(); // Ejecuta la eliminación
                
            } catch (SQLException e) {
                System.out.println("Error al eliminar empleado: " + e.getMessage());
            }
        }

    }

    public Empleados buscarPorDni(String dni){
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            Empleados empleado = null; // Inicializamos a null
            String nombre;
            String apellidos;
            String telefono;
            String email;
            String puesto;
            String jornada;
            int horas = 0; // Inicializamos horas
    
            PreparedStatement stmt = null;
            ResultSet rs = null;
    
            try {
                String query = "SELECT Nombre, Apellidos, Puesto, Telefono, Email, Jornada, HorasExtra " + "FROM Empleados WHERE EmpleadoDni = ?"; // Incluimos Puesto, Jornada y HorasExtra
    
                stmt = conexion.prepareStatement(query);
                stmt.setString(1, dni.trim()); // Usamos trim() para limpiar espacios
    
                rs = stmt.executeQuery();
    
                if (rs.next()) {
                    // Extraemos los datos del ResultSet
                    nombre = rs.getString("Nombre");
                    apellidos = rs.getString("Apellidos");
                    puesto = rs.getString("Puesto");
                    telefono = rs.getString("Telefono");
                    email = rs.getString("Email");
                    jornada = rs.getString("Jornada");
                    horas = rs.getInt("HorasExtra");
    
                    empleado = new Empleados(nombre, apellidos, dni, puesto, email, telefono, jornada, horas); // Orden correcto
                    return empleado;
                }
    
    
            } catch (SQLException e) {
                System.err.println("Error al buscar empleado por DNI: " + e.getMessage());
                // Podrías lanzar una excepción personalizada aquí si lo prefieres
            } finally {
                // Cerramos recursos en orden inverso a su creación
                try { if (rs != null) rs.close(); } catch (SQLException e) { /* ignorar */ }
                try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* ignorar */ }
    
            }
    
    
        }
        return null;
    }

    public ArrayList<Empleados> obtenerTodos() {
        // Establecer conexión
        Connection conexion = ConexionDB.conectar();
        if (conexion != null) {
            // Consulta SQL para obtener todos los Cliente
            String query = "SELECT * FROM Empleados";
            try (Statement stmt = conexion.createStatement(); 
                ResultSet rs = stmt.executeQuery(query)) {
                ArrayList<Empleados> empleados = new ArrayList<>();
                Empleados empleado = null; // Declaramos fuera del try
                String nombre;
                String apellidos;
                String dni;
                String telefono;
                String email;
                String puesto;
                String jornada;
                int horas;

                // Iterar sobre los resultados
                while (rs.next()) {
                    nombre = rs.getString("Nombre");
                    telefono = rs.getString("Telefono");
                    apellidos = rs.getString("Apellido");
                    dni = rs.getString("EmpleadoDni");
                    puesto = rs.getString("Puesto");
                    email = rs.getString("Email");
                    jornada = rs.getString("Jornada");
                    horas = rs.getInt("HorasExtra");
                    empleado = new Empleados(nombre, apellidos, dni, puesto, email, telefono, jornada, horas);
                    empleados.add(empleado);
                }

                return empleados;

            } catch (SQLException e) {
                System.out.println("Error al realizar la consulta: " + e.getMessage());
            }
        }
        return null;
        }
}
