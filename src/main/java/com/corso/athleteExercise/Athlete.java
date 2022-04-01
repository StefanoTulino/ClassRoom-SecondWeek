package com.corso.athleteExercise;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;


@Data
public class Athlete {
    private int code;
    private String name;
    private String nation;
    private String birthday;
    private double altezza;

    public Athlete(){

    }


    public Athlete(int code, String name, String nation, String birthday, double altezza) {
        this.code = code;
        this.name = name;
        this.nation = nation;
        this.birthday = birthday;
        this.altezza = altezza;
    }

}
