package com.corso.lombok;

public class TestStudent {
    public static void main(String[] args) {
        //LombokStudent lombokStudent= new LombookStudent();
        //ci escono automaticamente i metodi
        //lombokStudent.getAge();

        //CON BUILDER
        LombokStudent lombokStudent= LombokStudent.builder()
                .name("")
                .age(2)
                .build();
    }
}
