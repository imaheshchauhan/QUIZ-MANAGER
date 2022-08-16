package fr.epita.QuizManager.Services.data.dao;

import fr.epita.QuizManager.Services.data.QuestionBLService;
import fr.epita.QuizManager.datamodel.MCQChoice;
import fr.epita.QuizManager.datamodel.MCQQuestion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionDAO {

    public static void create(MCQQuestion question) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO question(quiz_id, question, image, topic, difficulty, que_type) VALUES (?,?,?,?,?,?) RETURNING question_id;");

        preparedStatement.setInt(1, question.getQuizID());
        preparedStatement.setString(2, question.getQuestion());
        preparedStatement.setString(3, "nothing");
        preparedStatement.setString(4, QuestionBLService.convertArraytoString(question.getTopics()));
        preparedStatement.setInt(5, question.getDifficulty());
        preparedStatement.setString(6, question.getQuestionType());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next() && question.getQuestionType().equals("m")) {
            long getQuestionId = rs.getLong(1);
            System.out.println(getQuestionId);
            System.out.println(rs.getLong(1));
            PreparedStatement st = connection.prepareStatement("INSERT INTO option(question_id, option, is_right) VALUES (?,?,?);");

            for(MCQChoice mcqChoice: question.getChoices()) {
                st.setInt(1, (int)getQuestionId);
                st.setString(2, mcqChoice.getChoice());
                st.setInt(3, mcqChoice.getValid() ? 1 : 0);
                st.addBatch();
            }

            st.executeBatch();
        }
        connection.close();
    }

    public static void update(String queType, String que, MCQChoice[] choices) throws SQLException {

        Connection connection = getConnection();

        readAll();

        System.out.println("Enter Question ID : ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        // Remove previous Option
        PreparedStatement stmtDeleteOption = connection.prepareStatement("delete from \"option\" where question_id = ?");
        stmtDeleteOption.setInt(1, id);
        stmtDeleteOption.execute();


        String SQL = "update question set question=?, que_type=? where question_id=?";

        PreparedStatement stmtUpdate = connection.prepareStatement(SQL);
        stmtUpdate.setString(1, que);
        stmtUpdate.setString(2, queType);
        stmtUpdate.setInt(3, id);
        stmtUpdate.execute();

        // New Option insert

        if (queType.equals("m")) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO option(question_id, option, is_right) VALUES (?,?,?);");

            for(MCQChoice mcqChoice: choices) {
                st.setInt(1, id);
                st.setString(2, mcqChoice.getChoice());
                st.setInt(3, mcqChoice.getValid() ? 1 : 0);
                st.addBatch();
            }

            st.executeBatch();
        }
        connection.close();
    }

    public static void readAll() throws SQLException {

        Connection connection = getConnection();

        String sqlQuery = "select * from question";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("question_id") + ". " + resultSet.getString("question"));

            String sqlQuery2 = "select * from public.\"option\" o, question q where o.question_id = q.question_id and q.question_id = ?";

            PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery2);
            preparedStatement2.setInt(1, (int)resultSet.getInt("question_id"));
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int count = 0;
            while (resultSet2.next()){
                count++;
                System.out.println(count  + ". " + resultSet2.getString("option") );
            }
            System.out.println("\n");
        }
        connection.close();
    }

    public static void delete(int id) throws SQLException {

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM question WHERE question_id = ?");
        ) {

            preparedStatement.setInt(1, (id));
            preparedStatement.execute();

            PreparedStatement stmtDeleteOption = connection.prepareStatement("delete from \"option\" where question_id = ?");
            stmtDeleteOption.setInt(1, id);
            stmtDeleteOption.execute();
        }


    }

    public static List<MCQQuestion> search(String topic, String quizTitle) {
        List<MCQQuestion> resultList = new ArrayList<>();
        try (
                Connection connection = getConnection();
        ) {
            String query = "";

            if (!quizTitle.equals("")) {
                 query = "SELECT q.question_id, q.quiz_id, q2.title , q.question, q.image, q.topic, q.difficulty, q.que_type\n" +
                        "FROM public.question q, public.quiz q2 \n" +
                        "WHERE q2.title = '%" + quizTitle + "%' and q2.quiz_id = q.quiz_id ";

            } else if (!topic.equals("")) {
                 query = "SELECT q.question_id, q.quiz_id, q2.title , q.question, q.image, q.topic, q.difficulty, q.que_type\n" +
                        "FROM public.question q, public.quiz q2 \n" +
                        "WHERE q.topic like  '%" + topic + "%' and q2.quiz_id = q.quiz_id ";

            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                int queID = resultSet.getInt("question_id");
                String question = resultSet.getString("question");
                String topics = resultSet.getString("topic");
                int difficulty = resultSet.getInt("difficulty");

                String sqlQuery2 = "select * from public.\"option\" o, question q where o.question_id = q.question_id and q.question_id = ?";

                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery2);
                preparedStatement2.setInt(1, queID);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                MCQChoice choices[] = new MCQChoice[4];

                int count = 0;
                while (resultSet2.next()){

                    if (count < 4) {
                        choices[count] = new MCQChoice(resultSet2.getString("option"),resultSet2.getInt("is_right") == 1 ? true : false);
                    }
                    count++;
                }

                int quizID = resultSet.getInt("quiz_id");
                String queType = resultSet.getString("que_type");

                resultList.add(new MCQQuestion(question, QuestionBLService.convertStringtoArray(topics), difficulty,choices, quizID, queType));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultList;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
    }
}