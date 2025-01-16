package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Таня", "Пон", (byte)27);
        userService.saveUser("Леха", "Пон", (byte)30);
        userService.saveUser("Алина", "Сам", (byte)29);
        userService.saveUser("Катя", "Белк", (byte)40);

        List<User> users = userService.getAllUsers();
        System.out.println("Список всех пользователей:");
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
       userService.dropUsersTable();
    }
}
