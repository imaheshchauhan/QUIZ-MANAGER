package fr.epita.test;

import fr.epita.QuizManager.Services.data.dao.QuestionDAO;

import java.sql.*;

public class TestDBConnection {

    public static void main(String[] args) throws SQLException {
        Connection connection = QuestionDAO.getConnection();

        String createTableQuery = "CREATE TABLE IF NOT EXISTS student( id varchar(30), name varchar(255),  password varchar(255))";

        connection.prepareStatement(createTableQuery).execute();

        String insertQuery = "INSERT INTO STUDENTS(id, name) values ('mahesh@epita.fr', 'Mahesh', 'mahesh')";

        connection.prepareStatement(insertQuery).execute();
        connection.close();

    }

}
