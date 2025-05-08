package Dao;

import Model.Reservas;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

 

public class ReservasDAO {

    Connection conexion = ConexionDB.conectar();

      public void crearReserva(Reservas reserva) {
        
        
        String sql = "{call CalcularReserva(?, ?, ?, ?, ?, ?, ?)}";
        
        try (CallableStatement stmt = conexion.prepareCall(sql)) {
            // Establecer parámetros de entrada
            stmt.setString(1, reserva.getClienteDni());
            stmt.setInt(2, reserva.getNumeroHabitacion());
            
            // Conversión de LocalDate a java.sql.Date
            stmt.setDate(3, java.sql.Date.valueOf(reserva.getFechaEntrada()));
            stmt.setDate(4, java.sql.Date.valueOf(reserva.getFechaSalida()));
            
            stmt.setInt(5, reserva.getNumeroAdultos());
            stmt.setInt(6, reserva.getNumeroNinos());
            
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
        }
    }

    public void actualizarReserva(Reservas reserva, int idReserva) {
        String query = "UPDATE Reservas SET ClienteDni = ?, NumeroHabitacion = ?, FechaEntrada = ?, FechaSalida = ?, NumeroAdultos = ?, NumeroNinos = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, reserva.getClienteDni());
            stmt.setInt(2, reserva.getNumeroHabitacion());
            stmt.setDate(3, java.sql.Date.valueOf(reserva.getFechaEntrada()));
            stmt.setDate(4, java.sql.Date.valueOf(reserva.getFechaSalida()));
            stmt.setInt(5, reserva.getNumeroAdultos());
            stmt.setInt(6, reserva.getNumeroNinos());
            

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reserva actualizada correctamente.");
            } else {
                System.out.println("Error al actualizar la reserva.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    public void cancelarReserva(int reservaID, String motivoCancelacion) {
        String query = "UPDATE Reservas SET EstadoReserva = 'Cancelada', MotivoCancelacion = ?, FechaCancelacion = ? WHERE ReservaID = ? ";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, motivoCancelacion);
            stmt.setInt(2, reservaID);
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reserva cancelada correctamente.");
            } else {
                System.out.println("Error al cancelar la reserva.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cancelar la reserva: " + e.getMessage());
        }
    }

    public void consultarReserva(int reservaID) {
        String query = "SELECT * FROM Reservas WHERE ReservaID = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, reservaID);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n=== DATOS COMPLETOS DE LA RESERVA ===");
                    
                    // Imprimir cada columna con su valor
                    System.out.println("ID Reserva: " + rs.getInt("ReservaID"));
                    System.out.println("DNI Cliente: " + rs.getString("ClienteDni"));
                    System.out.println("Número Habitación: " + rs.getInt("NumeroHabitacion"));
                    System.out.println("Fecha Entrada: " + rs.getDate("FechaEntrada"));
                    System.out.println("Fecha Salida: " + rs.getDate("FechaSalida"));
                    System.out.println("Número Adultos: " + rs.getInt("NumeroAdultos"));
                    System.out.println("Número Niños: " + rs.getInt("NumeroNinos"));
                    System.out.println("Fecha Reserva: " + rs.getTimestamp("FechaReserva"));
                    System.out.println("Estado Reserva: " + rs.getString("EstadoReserva"));
                    System.out.println("Precio Total: " + rs.getDouble("PrecioTotal"));
                    System.out.println("Precio Total: " + rs.getDate("FechaCancelacion"));
                    System.out.println("Precio Total: " + rs.getString("MotivoCancelacion"));
                    
                } else {
                    System.out.println("No se encontró reserva con ID: " + reservaID);
                }
            }
        }catch (SQLException e) {
            System.out.println("Error al consultar la reserva: " + e.getMessage());
        }
    }


}