package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    private static SessionFactory sessionFactory = null;
    private static final String URL = "jdbc:mysql://localhost:3306/testbd";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Aezakmi42@";

    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", URL);
            prop.setProperty("hibernate.connection.username", USERNAME);
            prop.setProperty("hibernate.connection.password", PASSWORD);
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");

            prop.setProperty("hibernate.hbm2ddl.auto", "create");

            Configuration configuration = new Configuration();
            configuration.addProperties(prop);
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration
                    .buildSessionFactory()
            ;
        }
        catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        } return connection;
    }
    public static Session getSession(){
        return sessionFactory.openSession();
    }

}
