package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement();) {
            statement.execute("CREATE TABLE users (id BIGINT auto_increment primary key, name varchar(50), lastname varchar(50), age TINYINT)");
        } catch (SQLException e){
        }
    }
    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement();) {
            statement.execute("DROP TABLE users");
        } catch (SQLException e){

        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String request = "INSERT INTO users VALUES(null, ?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(request)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {
        String sqlRequest = "DELETE FROM users WHERE ID = ?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sqlRequest)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
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
    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute("DELETE FROM users");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
