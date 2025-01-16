package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users (id bigint auto_increment primary key , " +
                "name varchar(80),lastName varchar(80), age tinyint)";

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.executeUpdate();
            System.out.println("Таблица создалась успешно");
        } catch (SQLException e) {
            System.out.println("При создании таблицы пользователей произошло исключение" + e.getMessage());
        }
    }


    public void dropUsersTable() {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DROP TABLE users")) {

            preparedStatement.executeUpdate();
            System.out.println("Таблица удалилась");
        } catch (SQLException e) {
            System.out.println("При удалении таблицы произошло исключение" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sgl = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sgl)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователи добавились в таблицу");
        } catch (SQLException e) {
            System.out.println("Во время сохранения пользователей произошло исключение" + e.getMessage());
        }
        System.out.println("User с именем — " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            System.out.println("Пользователь по id удалился");
        } catch (SQLException e) {
            System.out.println("При удаления пользователя по id произошло исключение" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);

                users.add(user);
                System.out.println("Все пользователи в коллекции");
            }
        } catch (SQLException e) {
            System.out.println("При попытке достать всех пользователей из базы данных произошло исключение" + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM users")) {

            preparedStatement.executeUpdate();
            System.out.println("Пользователи удалились из таблицы");
        } catch (SQLException e) {
            System.out.println("При очистки таблицы произошло исключение" + e);
        }
    }
}
