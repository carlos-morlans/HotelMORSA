import java.sql.Connection;

import Dao.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    Connection conexion = null;

    //horas mensuales = 40 horas semanales * 4 semanas = 160 horas mensuales aproximadamente

    //Meter en HistorialPagos el ultimo dia del mes el salario + (salario/horasMensuales * horasExtra), fecha(fechaActual), Concepto(Gastos), reservaID(null), dniEmpleado(dniEmpleado), tipo("Gastos"), cantidad(salario + (salario/horasMensuales * horasExtra))
    //Reset horasExtra en tabla empleados


    public static void main(String[] args){


    }





    private Double calcularSalarioMensual(){
        Connection conexion = null;
        String queryInsert = "INSERT INTO HistorialPagos (Cuantia, Fecha, Concepto, ReservaID, EmpleadoDni) VALUES (?, ?, ?, ?, ?)";
        String querySelect = "SELECT EmpleadoDni, Salario, HorasExtra FROM Empleados";

        PreparedStatement stmt = null;


        
            Double cuantia = 0.0;
            String empleadoDni;

            try {
            conexion = ConexionDB.conectar();
            if (conexion != null){
                stmt = conexion.prepareStatement(queryInsert);
                stmt.setDouble(1, cuantia);
                stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
                stmt.setString(3, "Gastos");
                stmt.setString(4, null);
                stmt.setString(5, empleadoDni);
            }
            }catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            } 
        
        

    }


}