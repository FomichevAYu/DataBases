package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.cleanUsersTable();
        User user1 = new User("федор", "Петухов", (byte)16);
        User user2 = new User("хопов", "петухов", (byte)23);
        User user3 = new User("педор", "ебаков", (byte)26);
        User user4 = new User("ебар", "фетухов", (byte)16);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        List<User>list = userService.getAllUsers();
        System.out.println(list.size());
        for (User user: list){
            System.out.println(user.toString());
        }
    }
}
