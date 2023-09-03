package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    private final Properties dbProperties;

    public H2ConnectionFactory() {
        this.dbProperties = loadDatabaseProperties();
    }

    @Override
    public Connection createConnection() {
        try {
            // Load the H2 JDBC driver
            Class.forName(dbProperties.getProperty("jdbc_driver"));

            // Create a connection using the properties provided
            String url = dbProperties.getProperty("db_url");
            String username = dbProperties.getProperty("user");
            String password = dbProperties.getProperty("password");

            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions as needed
            e.printStackTrace();
            throw new RuntimeException("Failed to create a database connection.");
        }
    }
    private static Properties loadDatabaseProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/h2database.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database properties from the configuration file.");
        }
        return properties;
    }
}

