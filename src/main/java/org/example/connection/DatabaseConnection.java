package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //método que faz a conexão com o banco de dados postgres
    public static Connection makingConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "1234");
//            if (connection != null) {
//                System.out.println("Conexão com o banco de dados bem sucedida! :D");
//            } else {
//                System.out.println("Conexão com o banco infelizmente FALHOU! :(");
//            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

