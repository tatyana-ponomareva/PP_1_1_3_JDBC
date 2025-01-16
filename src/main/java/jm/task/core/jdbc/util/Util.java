package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    Class.forName(DRIVER);
    private static final String URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String USERNAME= "root";
    private static final String PASSWORD="root";



    public Connection getConnection() {
        Connection connection = null;
        try{
            connection= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Cоединение с БД установлено");
        } catch (SQLException e) {
            System.out.println("Не удалось закрыть класс драйвер");
        }
        return connection;
    }
}
