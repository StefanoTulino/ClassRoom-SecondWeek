package com.corso.employeeExercise;

import java.io.IOException;
import java.sql.SQLException;

public interface CommandLine {

    void insertEmployee() throws IOException, SQLException;
    void updateEmployee() throws SQLException;
    void     deleteEmployee() throws SQLException;
}
