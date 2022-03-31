package com.corso.jpa0.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JpaTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;
    private EntityTransaction et;
    private Query q;


    public void print(){
        int numRows= q.getResultList().size();
    }


    public static void main(String[] args) {
        JpaTest jpaTest= new JpaTest();
        jpaTest.createTransaction();
    }

    public void init(){
        entityManagerFactory= JpaUtils.getEntityManagerFactory();
        em= entityManagerFactory.createEntityManager();
    }

    public void createTransaction(){
        this.init();
        //oppure solo init(), vedere
        em.getTransaction();
        et.begin();

        Family family;
        Person person;

        //creazione query
        q= em.createQuery("select * from Person p");
    }

}
