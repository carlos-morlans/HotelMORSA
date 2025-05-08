package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import Model.Reservas;

public class ReservasDAO {

    Connection conexion = ConexionDB.conectar();

      public void crearReserva(Reservas reserva) {
        //el id de la cita se asignará automáticamente después en la BD
        String query = "INSERT INTO Reservas (ClienteDni, NumeroHabitacion, FechaEntrada, FechaSalida, NumeroAdultos, NumeroNinos, FechaReserva, EstadoReserva, PrecioTotal, FechaCancelacion, MotivoCancelacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            
            stmt.setString(1, reserva.getClienteDni());
            stmt.setInt(2, reserva.getNumeroHabitacion());
            stmt.setDate(3, new java.sql.Date(reserva.getFechaEntrada().getTime()));
            stmt.setDate(4, new java.sql.Date(reserva.getFechaSalida().getTime()));
            stmt.setInt(5, reserva.getNumeroAdultos());
            stmt.setInt(6, reserva.getNumeroNinos());
            stmt.setTimestamp(7, Timestamp.valueOf(reserva.getFechaReserva()));
            stmt.setString(8, reserva.getEstadoReserva());
            stmt.setDouble(9, reserva.getPrecioTotal());
            stmt.setDate(10, new java.sql.Date(reserva.getFechaCancelacion().getTime()));
            stmt.setString(11, reserva.getMotivoCancelacion());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reserva creada correctamente.");
            } else {
                System.out.println("Error al crear la reserva.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
        }
        
        
        
    }

    public void actualizarReserva(Reservas reserva) {
        String query = "UPDATE Reservas SET ClienteDni = ?, NumeroHabitacion = ?, FechaEntrada = ?, FechaSalida = ?, NumeroAdultos = ?, NumeroNinos = ?, FechaReserva = ?, EstadoReserva = ?, PrecioTotal = ?, FechaCancelacion = ?, MotivoCancelacion = ? WHERE ReservaID = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, reserva.getClienteDni());
            stmt.setInt(2, reserva.getNumeroHabitacion());
            stmt.setDate(3, new java.sql.Date(reserva.getFechaEntrada().getTime()));
            stmt.setDate(4, new java.sql.Date(reserva.getFechaSalida().getTime()));
            stmt.setInt(5, reserva.getNumeroAdultos());
            stmt.setInt(6, reserva.getNumeroNinos());
            stmt.setTimestamp(7, Timestamp.valueOf(reserva.getFechaReserva()));
            stmt.setString(8, reserva.getEstadoReserva());
            stmt.setDouble(9, reserva.getPrecioTotal());
            stmt.setDate(10, new java.sql.Date(reserva.getFechaCancelacion().getTime()));
            stmt.setString(11, reserva.getMotivoCancelacion());
            stmt.setInt(12, reserva.getReservaID());

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
        String query = "UPDATE Reservas SET EstadoReserva = 'Cancelada', MotivoCancelacion = ? WHERE ReservaID = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, motivoCancelacion);
            stmt.setInt(2, reservaID);

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

    
    

}