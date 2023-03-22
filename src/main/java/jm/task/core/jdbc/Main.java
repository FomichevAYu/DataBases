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
    userService.dropUsersTable();
    userService.createUsersTable();
    userService.saveUser("ололошка", "Ололоев", (byte)12);
        userService.saveUser("ололошка1", "Ололоев1", (byte)123);
        userService.removeUserById(1);
    }
}
