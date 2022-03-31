package com.corso.employeeExercise;

import java.io.IOException;
import java.sql.*;

public abstract class ThreadMaster implements Runnable {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ReadProperties readProperties= new ReadProperties();

    public Connection connect() throws IOException {
        readProperties.read("application.properties");

        String url = readProperties.getProperties().getProperty("db.url");
        String user = readProperties.getProperties().getProperty("db.user");
        String passw = readProperties.getProperties().getProperty("db.passw");

        try {
            connection = DriverManager.getConnection(url, user, passw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    abstract void print() throws SQLException;
}


    /*
    public Connection connect() throws IOException {
        readProperties.read("application.properties");

        String url = readProperties.getProperties().getProperty("db.url");
        String user = readProperties.getProperties().getProperty("db.user");
        String passw = readProperties.getProperties().getProperty("db.passw");

        try {
            connection = DriverManager.getConnection(url, user, passw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

     */

