package com.corso.recursive;

public class FactorialRecursive {

    public static int factRecursive(int n){

        if(n==1)
            return 1;
        if(n==2)
            return 2;
            else {
                return n * factRecursive(n-1) ;
            }
    }

    public static int fib(int n){
        if(n==1) return 1;
            if(n==2) return 1;

                else return (fib(n-1)+ fib(n-2));
    }


    public static void main(String[] args) {
        System.out.println(FactorialRecursive.factRecursive(4));

        System.out.println(FactorialRecursive.fib(7));

    }


}
