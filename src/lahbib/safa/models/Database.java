package lahbib.safa.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/authisme","root","");
            } catch (ClassNotFoundException e) {
                System.out.println("Erreur du connection au base de donnée : "+e.getMessage());
            } catch (SQLException throwables) {
                System.out.println("Erreur du connection au base de donnée : "+throwables.getMessage());
            }
        }
        return connection;
    }
}
