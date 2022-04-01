package com.corso.athleteExercise;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class AthleteOperations implements BasedRepositories<Athlete> {

    static final LOG L = LOG.getInstance();

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private ReadProperties readProperties = new ReadProperties();

    private Scanner s = new Scanner(System.in);


    public Connection connect() throws IOException {
        readProperties.read("applicationAthlete.properties");

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

    public void createTable() {
        String sqlCreateTable = "" +
                "CREATE TABLE IF NOT EXISTS olympic.athlete (\n" +
                "  code INT NOT NULL,\n" +
                "  name VARCHAR(45) NOT NULL,\n" +
                "  nation VARCHAR(45) NOT NULL,\n" +
                "  birthday VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (code));";

        try {
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sqlCreateTable);
            int res = 0;
            res = preparedStatement.executeUpdate();
            if (res == 0)
                L.info("Tabella inserita o già esistente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public final Athlete findById(int code) throws SQLException {
        String sqlFindId = "" +
                "SELECT * FROM olympic.athlete " +
                "WHERE code=? ";

        preparedStatement = connection.prepareStatement(sqlFindId);
        preparedStatement.setInt(1, code);
        preparedStatement.execute();
        resultSet= preparedStatement.getResultSet();
        while (resultSet.next()) {
            L.info(resultSet.getString("name") + "\t" +
                    resultSet.getString("nation") + "\t" +
                    resultSet.getString("birthday") + "\t" +
                    resultSet.getString("altezza"));
        }
        return null;

    }

    public List<Athlete> printAll() throws SQLException {
        String sqlAll = "" +
                "SELECT * FROM olympic.athlete";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlAll);
        while (resultSet.next()) {
            L.info(resultSet.getString("code") + "\t" +
                    resultSet.getString("name") + "\t" +
                    resultSet.getString("nation") + "\t" +
                    resultSet.getString("birthday") + "\t" +
                    resultSet.getString("altezza"));
        }
        return null;
    }

    public List<Athlete> findByHeight(double h) throws SQLException {
        String sqlFindHeight = "" +
                "SELECT * \n" +
                "FROM olympic.athlete a\n" +
                "WHERE a.altezza> ?";
        preparedStatement = connection.prepareStatement(sqlFindHeight);
        preparedStatement.setDouble(1, h);
        preparedStatement.execute();
        ResultSet resto = preparedStatement.getResultSet();
        L.info("Lista atleti con altezza maggiore: ");
        while (resto.next()) {
            L.info( resto.getString("name") +"\t"+
                    resto.getString("nation") + "\t" +
                            resto.getString("birthday") + "\t" +
                            resto.getString("altezza"));
        }
        return null;
    }


    //override methods of the interface
    @Override
    public void insertObject(Athlete object) throws SQLException, ParseException {

        String sqlInsertTable = "" +
                "INSERT INTO olympic.athlete ( code, name, nation, birthday, altezza) " +
                "VALUES (?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(sqlInsertTable);
        preparedStatement.setInt(1, object.getCode());
        preparedStatement.setString(2, object.getName());
        preparedStatement.setString(3, object.getNation());
        preparedStatement.setString(4, object.getBirthday());
        preparedStatement.setDouble(5, object.getAltezza());

        preparedStatement.execute();
        L.info("Aggiunto record");
    }

    @Override
    public void updateObject(Athlete object) throws SQLException {
        String printQ2 = "" +
                "UPDATE olympic.athlete " +
                "SET name = ? " +
                "WHERE code = ?";
        preparedStatement = connection.prepareStatement(printQ2);

        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getCode());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteObject(Athlete object) throws SQLException {
        String printQ3 = "" +
                "DELETE FROM olympic.athlete " +
                "WHERE code = ? ";

        preparedStatement = connection.prepareStatement(printQ3);
        L.info("Inserisci l'id da voler eliminare");
        preparedStatement.setInt(1, object.getCode());
        preparedStatement.execute();
    }

    @Override
    public void closeConnections() throws SQLException {
        preparedStatement.close();
        statement.close();
        connection.close();

        System.out.println("\n");
        L.info("Connessioni chiuse");
    }

}
