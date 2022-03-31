package com.corso.jpa0.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Family {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="family_id")
    private String id;

    //il name solo nel caso vogliamo cambiare il nome nella colonna
    //length mi specifica la lunghezza massima di questa stringa
    @Column(name="desc", length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private List<Person> persons= new ArrayList<Person>();


}
