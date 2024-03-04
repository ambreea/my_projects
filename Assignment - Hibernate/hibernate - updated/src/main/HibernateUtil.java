package main;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;

import javax.swing.*;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;


    public static SessionFactory createSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.addResource("main/employee.hbm.xml");
            configuration.addResource("main/department.hbm.xml");
            configuration.configure();
            configuration.getProperties().setProperty("hibernate.connection.url", User.getNewUrl());
            configuration.getProperties().setProperty("hibernate.connection.password", User.getNewPassword());
            configuration.getProperties().setProperty("hibernate.connection.user", User.getNewUser());
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return sessionFactory;
    }

    public static void close() {
        sessionFactory.close();
        JOptionPane.showMessageDialog(null, "Connection closed");
    }

}
