package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        //Создание таблицы
        userService.createUsersTable();

        User user1 = new User("Thomas", "Angelo", (byte) 35);
        User user2 = new User("Don", "Salieri", (byte) 61);
        User user3 = new User("Paulie", "Lombardo", (byte) 36);
        User user4 = new User("Sam", "Trapani", (byte) 101);
        //Добавление пользователей
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем – " +user1.getName()+ " добавлен в базу данных");
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем – " +user2.getName()+ " добавлен в базу данных");
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем – " +user3.getName()+ " добавлен в базу данных");
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем – " +user4.getName()+ " добавлен в базу данных");
        //Получение всех User из базы и вывод в консоль*/
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        //Удаление таблицы
        userService.dropUsersTable();
    }
}
