// USARE EMPLOYEE COME APPOGGIO PER OGNI SCANNER USATO PER INSERIRE O MENO

package com.corso.employeeExercise;


public class Test {
    static LOG L= LOG.getInstance();

    public static void main(String[] args)  {

            try{
                    ThreadMaster threadCreateTable= new ThreadCreateTable();
                    threadCreateTable.run();
                    //threadCreateTable.print();
                    System.out.println("\n");
                    ThreadOperations threadOperations= new ThreadOperations();
                    threadOperations.run();
                    //threadOperations.print();

            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
