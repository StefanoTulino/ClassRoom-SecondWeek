package com.corso.employeeExercise;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class ThreadOperations extends ThreadMaster implements CommandLine {

    static final LOG L = LOG.getInstance();

    private Connection connection=connect();
    private Statement statement= connect().createStatement();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //Scanner utils for methods
    private Scanner s = new Scanner(System.in);


    public ThreadOperations() throws IOException, SQLException {

    }

    @Override
    public void run() {
        try {
            this.printQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.operation();

    }



    @Override
    public void insertEmployee() throws IOException, SQLException {


        L.info("Inserisci i dati come segue:\n"+
                "id, nome, cognome");

        String sqlInsertTable= "" +
                "INSERT INTO academyjdbc.employee ( id, name, lastname) " +
                "VALUES (?, ?, ?)" ;

        preparedStatement=connection.prepareStatement(sqlInsertTable);
        preparedStatement.setInt(1,s.nextInt());
        preparedStatement.setString(2,s.next());
        preparedStatement.setString(3, s.next());
            preparedStatement.execute();
            L.info("Aggiunto record");
    }


    public void operation(){
        System.out.println("\n");

        L.info("Decidi cosa vuoi fare : \n" +
                "1: Stampa i dati nel db \n" +
                "2: Inserisci un nuovo impiegato \n" +
                "3: Modifica un impiegato e vedi il risultato \n" +
                "4: Elimina un impiegato \n"+
                "0: Per uscire");

        int scelta = s.nextInt();

            try {

            switch (scelta) {
                case 1:
                    printQuery();break;
                case 2:
                    insertEmployee();break;
                case 3:
                    updateEmployee();break;
                case 4:
                    deleteEmployee();break;
                default: L.error("Numero inserito errato, arrivedercik");break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @LoggerAnnotation("siamo nel threadOperations")
    @Override
    public void print() throws SQLException {

    }

    public void printQuery() throws SQLException {
        String printQ1="" +
                "SELECT *" +
                "FROM academyjdbc.employee";

        resultSet = statement.executeQuery(printQ1);
        while (resultSet.next()) {
            L.info(resultSet.getString("id") + "\t"+
                    resultSet.getString("name") + "\t"+
                    resultSet.getString("lastname"));
        }
    }


    @Override
    public void updateEmployee() throws SQLException {
        String printQ2= "" +
                "UPDATE academyjdbc.employee " +
                "SET lastname = ? " +
                "WHERE id = ?" ;

        preparedStatement=connection.prepareStatement(printQ2);

        L.info("Inserisci il cognome da sostituire ad un id(int) sopra elencato");
        preparedStatement.setString(1,s.next());
        preparedStatement.setInt(2,s.nextInt());
        preparedStatement.executeUpdate();

    }


    @Override
    public void deleteEmployee() throws SQLException {
        String printQ3= "" +
                "DELETE FROM academyjdbc.employee " +
                "WHERE id = ? ";

            preparedStatement=connection.prepareStatement(printQ3);
            L.info("Inserisci l'id da voler eliminare");
            preparedStatement.setInt(1,s.nextInt());
            preparedStatement.execute();
        }



        protected void closeMethod() throws SQLException {
            preparedStatement.close();
            //resultSet.close();
            statement.close();
            connection.close();

            System.out.println("\n");
            L.info("Connessioni chiuse");
        }






}


