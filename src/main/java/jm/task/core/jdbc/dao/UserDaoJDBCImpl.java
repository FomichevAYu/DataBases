package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement();) {
            statement.execute("CREATE TABLE users (id BIGINT, name varchar(50), lastname varchar(50), age TINYINT)");
        } catch (SQLException e){

        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement();) {
            statement.execute("DROP TABLE users");
        } catch (SQLException e){

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String request = "INSERT INTO users VALUES(null, '"+name+"' ,'"+lastName+"', "+age+")";


        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(request);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sqlRequest = "DELETE FROM users  WHERE ID IS "+id;
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlRequest);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User>list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DELETE FROM users");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
