package com.dairyfarm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final String DEFAULT_DB_URL = "jdbc:postgresql://localhost:5432/dairy_farm_db";
    private static final String DEFAULT_DB_USERNAME = "postgres";

    private static SessionFactory sessionFactory;

    private static synchronized SessionFactory buildSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            String url = env("DB_URL", DEFAULT_DB_URL);
            String username = env("DB_USERNAME", DEFAULT_DB_USERNAME);
            String password = System.getenv("DB_PASSWORD");
            if (password == null || password.isBlank()) {
                throw new IllegalStateException(
                    "DB_PASSWORD environment variable is not set. " +
                    "Set DB_URL, DB_USERNAME and DB_PASSWORD before starting the application.");
            }

            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.setProperty("connection.url", url);
            configuration.setProperty("connection.username", username);
            configuration.setProperty("connection.password", password);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    private static String env(String name, String defaultValue) {
        String value = System.getenv(name);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    public static SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}