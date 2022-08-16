package fr.epita.QuizManager.Services.data.dao;

import fr.epita.QuizManager.datamodel.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static fr.epita.QuizManager.Services.data.dao.QuestionDAO.getConnection;

public class QuizDAO {

    public static int create(Quiz quiz) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO quiz(title) VALUES (?) RETURNING quiz_id;");

        preparedStatement.setString(1, quiz.getTitle());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            long getQuestionId = rs.getLong(1);
            return (int)getQuestionId;
        }
        connection.close();
        return 0;
    }

    public static int getIDFromTitle(String title) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from public.quiz where title ilike ?");

        preparedStatement.setString(1, title);

        ResultSet rs = preparedStatement.executeQuery();

        connection.close();

        if (rs.next()) {
            long getQuestionId = rs.getLong(1);
            return (int)getQuestionId;
        }

        return 0;
    }
}
