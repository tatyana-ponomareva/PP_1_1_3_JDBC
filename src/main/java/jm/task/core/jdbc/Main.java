package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {


        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Таня", "Пон", (byte)27);
        userService.saveUser("Леха", "Пон", (byte)30);
        userService.saveUser("Алина", "Сам", (byte)29);
        userService.saveUser("Катя", "Белк", (byte)40);

        List<User> users = userService.getAllUsers();
        logger.info("Список всех пользователей:");
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
       userService.dropUsersTable();
    }
}
