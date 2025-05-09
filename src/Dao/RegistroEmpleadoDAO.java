package Dao;

import Model.RegistroEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistroEmpleadoDAO {

    Connection conexion = ConexionDB.conectar();

    public void insertarRegistro(RegistroEmpleado registro) throws SQLException {
        String sql = "INSERT INTO RegistroEmpleados (EmpleadoDni, Tipo, HORA, FechaRegistro) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, registro.getEmpleadoDni());
            ps.setString(2, registro.getTipo());
            ps.setTime(3, registro.getHora());
            ps.setDate(4, new java.sql.Date(registro.getFechaRegistro().getTime()));
            ps.executeUpdate();
        }
    }

    public List<RegistroEmpleado> obtenerTodosLosRegistros() throws SQLException {
        List<RegistroEmpleado> registros = new ArrayList<>();
        String sql = "SELECT RegistroID, EmpleadoDni, Tipo, HORA, FechaRegistro FROM RegistroEmpleados";
        try (Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                RegistroEmpleado registro = new RegistroEmpleado();
                registro.setRegistroID(rs.getInt("RegistroID"));
                registro.setEmpleadoDni(rs.getString("EmpleadoDni"));
                registro.setTipo(rs.getString("Tipo"));
                registro.setHora(rs.getTime("HORA"));
                registro.setFechaRegistro(rs.getDate("FechaRegistro"));
                registros.add(registro);
            }
        }
        return registros;
    }

    public RegistroEmpleado obtenerRegistroPorId(int id) throws SQLException {
        String sql = "SELECT RegistroID, EmpleadoDni, Tipo, HORA, FechaRegistro FROM RegistroEmpleados WHERE RegistroID = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        RegistroEmpleado registro = new RegistroEmpleado();
                        registro.setRegistroID(rs.getInt("RegistroID"));
                        registro.setEmpleadoDni(rs.getString("EmpleadoDni"));
                        registro.setTipo(rs.getString("Tipo"));
                        registro.setHora(rs.getTime("HORA"));
                        registro.setFechaRegistro(rs.getDate("FechaRegistro"));
                        return registro;
                    }
                }
            }
            return null;
        }

        public void actualizarRegistro(RegistroEmpleado registro) throws SQLException {
            String sql = "UPDATE RegistroEmpleados SET EmpleadoDni = ?, Tipo = ?, HORA = ?, FechaRegistro = ? WHERE RegistroID = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, registro.getEmpleadoDni());
                ps.setString(2, registro.getTipo());
                ps.setTime(3, registro.getHora());
                ps.setDate(4, new java.sql.Date(registro.getFechaRegistro().getTime()));
                ps.setInt(5, registro.getRegistroID());
                ps.executeUpdate();
            }
        }

        public void eliminarRegistro(int id) throws SQLException {
            String sql = "DELETE FROM RegistroEmpleados WHERE RegistroID = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
}