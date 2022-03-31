package com.corso.esercizioDb2;

import java.sql.*;

public class ThreadCreateTable extends Thread {

    static final LOG L = LOG.getInstance();

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;




    @Override
    public void run() {
        String sqlCreateTable = "" +
                "CREATE TABLE IF NOT EXISTS academyjdbc.auto (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  marchio VARCHAR(45) NOT NULL,\n" +
                "  nazione VARCHAR(45) NOT NULL,\n" +
                "  fatturato DOUBLE NOT NULL,\n" +
                "  dipendenti INT NOT NULL,\n" +
                "  PRIMARY KEY (id));";

        try {
            connection = DriverManager.getConnection(DbConstant.DB_URL, DbConstant.DB_User, DbConstant.DB_Passw);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sqlCreateTable);

            int res = 0;
            res = preparedStatement.executeUpdate();
            if(res==0)
                L.info("Tabella inserita o già esistente");

            } catch (SQLException e) {
                e.printStackTrace();
        }
    }

}
