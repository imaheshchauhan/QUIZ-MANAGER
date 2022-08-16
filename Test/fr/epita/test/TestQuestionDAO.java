package fr.epita.test;

import fr.epita.QuizManager.Services.data.QuestionBLService;
import fr.epita.QuizManager.Services.data.dao.QuestionDAO;
import fr.epita.QuizManager.datamodel.MCQQuestion;

import java.util.List;

public class TestQuestionDAO {

    public static void main(String[] args) {
        List<MCQQuestion> ques = QuestionDAO.search("maths","");

        QuestionBLService.printList(ques);
    }
}
