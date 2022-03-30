package com.corso.esercizioDb2;


public class Test {
    static LOG L= LOG.getInstance();

    public static void main(String[] args)  {


            try{
                    Thread threadCreateTable= new ThreadCreateTable();
                    threadCreateTable.setName("Thread-creazione");
                    threadCreateTable.start();
                    threadCreateTable.join();

                    ThreadOperations threadOperations= new ThreadOperations();
                    threadOperations.setName("Thread-operazioni");
                    threadOperations.start();


            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
