// reference con la classe family di tipo 1-->N
//è scambiato, quindi qua la one to many e di la manytoone
package com.corso.jpa0.domain;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="person_id")
    private String id;

    @Column(name="desc", length = 30)
    private String name;
    @Column(name="desc", length = 40)
    private String lastname;

    @OneToMany(
            //sarebbe la classe
            mappedBy = "family",
            cascade = {CascadeType.ALL}
    )

    private Family family;
}
