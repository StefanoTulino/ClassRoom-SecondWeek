package com.corso.esercizioDb2;

import java.sql.*;
import java.util.Scanner;

public class ThreadOperations extends Thread {

    static final LOG L = LOG.getInstance();

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    //Scanner utils for methods
    private Scanner s = new Scanner(System.in);


    public ThreadOperations() {

    }

    @Override
    public void run() {
        try {
            this.insertRecord();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.operation();

        try {
            this.closeMethod();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertRecord() throws SQLException {

        connection = DriverManager.getConnection(DbConstant.DB_URL, DbConstant.DB_User, DbConstant.DB_Passw);
        statement = connection.createStatement();


        L.info("Inserisci i dati come segue:\n"+
                "Marchio,  Nazione,  Fatturato(di tipo double) , Numero Dipendenti");

        String sqlInsertTable= "" +
                "INSERT INTO academyjdbc.auto ( marchio, nazione, fatturato, dipendenti) " +
                "VALUES (?, ?, ?, ?)" ;

        preparedStatement=connection.prepareStatement(sqlInsertTable);
        preparedStatement.setString(1,s.next());
        preparedStatement.setString(2,s.next());
        preparedStatement.setDouble(3, s.nextDouble());
        preparedStatement.setInt(4,s.nextInt());
        boolean temp=preparedStatement.execute();
        L.info("Aggiunto record");
    }


    public void operation()   {
        System.out.println("\n");

        L.info("Decidi cosa vuoi stampare : \n" +
                "1: Elenca il marchio ed il fatturato in ordine discendente. \n" +
                "2: Elenca il numero di marchi per nazione. \n" +
                "3: Elenca il numero di dipendenti per nazione \n" +
                "4: Elenca per ogni marchio il fatturato per dipendenti in ordine decrescente. \n"+
                "5: Elenca il fatturato per nazione in ordine decrescente. \n" +
                "0: Per uscire");

        int scelta = s.nextInt();

            try {

                switch (scelta) {
                    case 1:
                        printQ1();break;
                    case 2:
                        printQ2();break;
                    case 3:
                        printQ3();break;
                    case 4:
                        printQ4();break;
                    case 5:
                        printQ5();break;
                    default: L.error("Arrivederci");break;
                }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void printQ1() throws SQLException {
        String printQ1="" +
                "SELECT a.marchio, a.fatturato \n" +
                "FROM academyjdbc.auto a\n" +
                "ORDER BY a.fatturato DESC;" ;

        resultSet = statement.executeQuery(printQ1);
        while (resultSet.next()) {
            L.info(resultSet.getString("marchio") + "\t"+
            resultSet.getString("fatturato"));
        }

    }


    public void printQ2() throws SQLException {
        String printQ2= "" +
                "SELECT a.marchio\n" +
                "FROM academyjdbc.auto a\n" +
                "where a.nazione= \"Germania\";" ;

        resultSet = statement.executeQuery(printQ2);
        while (resultSet.next()) {
            L.info(resultSet.getString("marchio"));
        }
    }


    public void printQ3() throws SQLException {
        String printQ3= "" +
                "SELECT a.dipendenti\n" +
                "FROM academyjdbc.auto a\n" +
                "where a.nazione= \"Germania\";" ;

        resultSet = statement.executeQuery(printQ3);
        while (resultSet.next()) {
            L.info(resultSet.getString("dipendenti"));
        }
    }


    public void printQ4() throws SQLException {
        String printQ4= "" +
                "SELECT a.marchio,a.fatturato\n" +
                "FROM academyjdbc.auto a\n" +
                "ORDER BY a.dipendenti DESC;" ;

        resultSet = statement.executeQuery(printQ4);
        while (resultSet.next()) {
            L.info(resultSet.getString("marchio")+"\t"+
                    resultSet.getString("fatturato") );
        }
    }


    public void printQ5() throws SQLException {
        String printQ5= "" +
                "SELECT a.nazione,a.fatturato\n" +
                "FROM academyjdbc.auto a\n" +
                "ORDER BY a.nazione DESC;" ;

        resultSet = statement.executeQuery(printQ5);
        while (resultSet.next()) {
            L.info(resultSet.getString("nazione")+"\t"+
                    resultSet.getString("fatturato"));
        }
    }



        protected void closeMethod() throws SQLException {
            preparedStatement.close();
            resultSet.close();
            statement.close();
            connection.close();

            System.out.println("\n");
            L.info("Connessioni chiuse");
        }
}


