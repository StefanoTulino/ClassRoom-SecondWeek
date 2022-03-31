package com.corso.employeeExercise;



import java.io.IOException;
import java.sql.*;

public class ThreadCreateTable extends ThreadMaster {

    static final LOG L = LOG.getInstance();

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;



    @Override
    public void run() {
        String sqlCreateTable = "" +
                "CREATE TABLE IF NOT EXISTS academyjdbc.employee (\n" +
                "  id INT NOT NULL,\n" +
                "  name VARCHAR(45) NOT NULL,\n" +
                "  lastname VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (id));";

        try {
            //abstract class
            connection=connect();

            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sqlCreateTable);
            int res = 0;
            res = preparedStatement.executeUpdate();
            if(res==0)
                L.info("Tabella inserita o già esistente");
            } catch (SQLException e) {
                e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
            }
        }

    @LoggerAnnotation("siamo nel thread createTable")
    @Override
    void print() {
    }

}
