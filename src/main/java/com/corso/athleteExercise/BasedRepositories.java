package com.corso.athleteExercise;

import java.sql.SQLException;
import java.text.ParseException;

public interface BasedRepositories<K> {

    void insertObject(K object) throws SQLException, ParseException;
    void updateObject(K object) throws SQLException;
    void deleteObject(K object) throws SQLException;


    void closeConnections() throws SQLException;
}
