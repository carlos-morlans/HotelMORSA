package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PagosDAO {

    Connection conexion = ConexionDB.conectar();

    public void filtroCuantia(int limite, Double cuantiaMaxima, Double cuantiaMinima, String tipo) {
        
        String query = "SELECT * FROM HistorialPagos WHERE Cuantia BETWEEN ? AND ? AND Concepto = ? LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setDouble(1, cuantiaMinima);
            stmt.setDouble(2, cuantiaMaxima);
            stmt.setString(3, tipo);
            stmt.setInt(4, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                // Procesar los resultados
                System.out.println("Pago ID: " + rs.getInt("HistorialPagoID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("Fecha"));
                System.out.println("Concepto: " + rs.getString("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por cuantía: " + e.getMessage());
        }

       
       
    }

    public void filtroDNIEmpleado(int limite, String dniEmpleado, String tipo) {

        String query = "SELECT * FROM HistorialPagos WHERE EmpleadoDNI = ? AND Concepto = ? LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, dniEmpleado);
            stmt.setString(2, tipo);
            stmt.setInt(3, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagoID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("Fecha"));
                System.out.println("Concepto: " + rs.getString("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por DNI de empleado: " + e.getMessage());
        }
       
    }

    public void filtroIDReserva(int limite, int idReserva, String tipo) {

        String query = "SELECT * FROM HistorialPagos WHERE ReservaID = ? AND Concepto = ? LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, idReserva);
            stmt.setString(2, tipo);
            stmt.setInt(3, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagoID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("Fecha"));
                System.out.println("Concepto: " + rs.getString("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al filtrar por ID de reserva: " + e.getMessage());
        }
       
    }

    public void filtroRecientes(int limite, String tipo) {

        String query = "SELECT * FROM HistorialPagos WHERE Concepto = ? ORDER BY Fecha DESC LIMIT ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, tipo);
            stmt.setInt(2, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagoID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("Fecha"));
                System.out.println("Concepto: " + rs.getString("Concepto"));
                System.out.println("Reserva ID: " + rs.getInt("ReservaID"));
                System.out.println("Empleado DNI: " + rs.getString("EmpleadoDni"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por pagos recientes: " + e.getMessage());
        }
       
    }

    public void filtroFecha(int limite, LocalDate fechaInicio, LocalDate fechaFin, String tipo, int suma) {
        String query = "SELECT * FROM HistorialPagos WHERE Fecha BETWEEN ? AND ? AND Concepto = ? LIMIT ?";
        if(suma == 2){
        
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setDate(1, java.sql.Date.valueOf(fechaInicio));
            stmt.setDate(2, java.sql.Date.valueOf(fechaFin));
            stmt.setString(3, tipo);
            stmt.setInt(4, limite);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                System.out.println("Pago ID: " + rs.getInt("HistorialPagoID"));
                System.out.println("Cuantía: " + rs.getDouble("Cuantia"));
                System.out.println("Fecha Pago: " + rs.getDate("Fecha"));
                System.out.println("Concepto: " + rs.getString("Concepto"));
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
