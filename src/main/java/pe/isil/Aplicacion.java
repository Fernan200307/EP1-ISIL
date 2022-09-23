package pe.isil;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Aplicacion {
        //Creamos la conexión (siempre crear la dependency en pom.xml)
    public static void main(String[] args) throws Exception {
        System.out.println("Hola profe");

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/isilep1",
                "root",
                "root"
        );

        // 1. Utilizamos STATEMENT
        Statement stmt= con.createStatement();

        //1.1 Actualizar

        int filasAfectada = stmt.executeUpdate("update Users set city='Madrid' where apellido= 'Ronaldo'");
        System.out.println("STATEMENT-ACTUALIZAR: Filas afectadas: " + filasAfectada);

        Statement stmt2= con.createStatement();
        ResultSet result = stmt2.executeQuery("select * from Users where name='Cristiano'");

        // 1.2 Mostrar el resultado (los datos que deseamos que nos envie)

        while (result.next()){
            System.out.println(result.getString("id"));
            System.out.println(result.getString("name"));
            System.out.println(result.getString("apellido"));
            System.out.println(result.getString("phone"));
            System.out.println(result.getString("city"));
            System.out.println(result.getString("camiseta"));
            System.out.println(result.getString("nacionalidad"));
        }

        // 2. Prepared Statement

        // 2.1 mostrar un resultado (en este caso Paolo)

        PreparedStatement preparedStatement =
                con.prepareStatement("select * from Users where name=?");

        preparedStatement.setString(1,"Paolo");
        System.out.println("PREPARED STATEMENT-MOSTRAR Filas afectadas: " + filasAfectada);

        ResultSet resultSet2 = preparedStatement.executeQuery();

        while (resultSet2.next()){
            System.out.println(resultSet2.getString("name"));
            System.out.println(resultSet2.getString("apellido"));
            System.out.println(resultSet2.getString("phone"));
        }

        // 2.2 Creamos un nuevo registro (crearemos al jugador Riquelme)

        Statement stCreate = con.createStatement();
        int filasAfectadas=
                stCreate.executeUpdate("INSERT INTO Users VALUES (4,'Juan', 'Riquelme', '984755234', 'Buenos Aires', '10', 'Argentina');");

        System.out.println("PREPARED STATEMENT CREAR- Filas afectadas: " + filasAfectadas);

        PreparedStatement st = con.prepareStatement("select * from Users where name =?");
        preparedStatement.setString(1,"Juan");

        ResultSet resultSet3 = preparedStatement.executeQuery();

        while (resultSet3.next()){
            System.out.println(resultSet3.getString("name"));
            System.out.println(resultSet3.getString("apellido"));
            System.out.println(resultSet3.getString("phone"));
        }

        //3. CallableStatement

        //3.1 Listar el resultado-user seleccionado

        //Crear Statement
        Statement statement = con.createStatement();
        CallableStatement callSp4 = con.prepareCall("{CALL listarUserPerId(?)}");
        System.out.println("CALLABLE STATEMENT LISTAR - Filas afectadas: " + filasAfectada);
        callSp4.setInt(1,1);
        ResultSet resultadoSp4 = callSp4.executeQuery();

        while (resultadoSp4.next())
        {
            System.out.println(resultadoSp4.getString("city"));
        }
    }

}
