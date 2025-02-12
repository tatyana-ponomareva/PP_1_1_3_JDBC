package jm.task.core.jdbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    private static final String URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String USERNAME= "root";
    private static final String PASSWORD="root";



    public Connection getConnection() {
        Connection connection = null;
        try{
            connection= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Cоединение с БД установлено");
        } catch (SQLException e) {
            logger.error("Не удалось закрыть класс драйвер");
        }
        return connection;
    }
}
