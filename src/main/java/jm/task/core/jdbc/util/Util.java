package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import org.hibernate.cfg.Environment;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;





public  class Util {
    // реализуйте настройку соеденения с БД

    private static final String userName ="root";
    private static final String password = "1111";
    private static final String connectURL = "jdbc:mysql://localhost:3306/users";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection connection ;
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, driver);
                settings.put(Environment.URL, connectURL);
                settings.put(Environment.USER, userName);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection()  {
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(connectURL, userName, password);
                System.out.println("getConnection");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Do not open - Exception!!!");
        }
        return connection;
    }
    public static void closeConnection(){
        if(connection != null) {
            try {
                System.out.println("closeConnection");
                connection.close();
            } catch (SQLException e) {
                System.out.println("Do not closed - Exception!!!");
            }
        }
    }

}
