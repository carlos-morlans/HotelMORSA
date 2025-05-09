package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PagosDAO {

    Connection conexion = ConexionDB.conectar();

    public void filtroCuantia(int limite, Double cuantiaMaxima, Double cuantiaMinima, String tipo) {
        
        String query = "SELECT * FROM HistorialPagos WHERE Cuantia BETWEEN ? AND ? LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setDouble(1, cuantiaMinima);
            stmt.setDouble(2, cuantiaMaxima);
            stmt.setInt(3, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                // Procesar los resultados
                System.out.println("Pago ID: " + rs.getInt("HistorialPagosID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("fechaPago"));
                System.out.println("Concepto: " + rs.getInt("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por cuantía: " + e.getMessage());
        }

       
       
    }

    public void filtroDNIEmpleado(int limite, String dniEmpleado, String tipo) {

        String query = "SELECT * FROM HistorialPagos WHERE EmpleadoDNI = ? LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, dniEmpleado);
            stmt.setInt(2, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagosID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("fechaPago"));
                System.out.println("Concepto: " + rs.getInt("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por DNI de empleado: " + e.getMessage());
        }
       
    }

    public void filtroIDReserva(int limite, int idReserva, String tipo) {

        String query = "SELECT * FROM HistorialPagos WHERE ReservaID = ? LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idReserva);
            stmt.setInt(2, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagosID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("fechaPago"));
                System.out.println("Concepto: " + rs.getInt("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al filtrar por ID de reserva: " + e.getMessage());
        }
       
    }

    public void filtroRecientes(int limite, String tipo) {

        String query = "SELECT * FROM HistorialPagos ORDER BY FechaPago DESC LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagosID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("fechaPago"));
                System.out.println("Concepto: " + rs.getInt("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por pagos recientes: " + e.getMessage());
        }
       
    }

    public void filtroFecha(int limite, LocalDate fechaInicio, LocalDate fechaFin, String tipo, int suma) {
        String query = "SELECT * FROM HistorialPagos WHERE FechaPago BETWEEN ? AND ? LIMIT ?";
        if(suma == 2){
        
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
            stmt.setDate(2, java.sql.Date.valueOf(fechaFin));
            stmt.setInt(3, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagosID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("fechaPago"));
                System.out.println("Concepto: " + rs.getInt("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por fecha: " + e.getMessage());
        }
        }
        if (suma == 1) {
            double total = 0;
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
                stmt.setDate(2, java.sql.Date.valueOf(fechaFin));
                stmt.setInt(3, limite);
                
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    total += rs.getDouble("Cuantia");
                }
            } catch (SQLException e) {
                System.out.println("Error al calcular la suma de los pagos: " + e.getMessage());
            }
            System.out.println("La suma de los pagos es: " + total);
        }
       
    }



}
