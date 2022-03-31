package com.corso.employeeExercise;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Employee {

    private int id;
    private String name;
    private String surname;

    public Employee(){

    }

    public Employee(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

}
