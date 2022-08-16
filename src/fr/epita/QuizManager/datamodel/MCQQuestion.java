package fr.epita.QuizManager.datamodel;

import java.util.Scanner;

public class MCQQuestion extends Question {

    MCQChoice choices[] = new MCQChoice[4];
    int quizID;
    String questionType;
    public MCQQuestion(String question, String[] topics, int difficulty, MCQChoice[] choices, int quizID, String questionType) {
        super(question, topics, difficulty);
        this.choices = choices;
        this.quizID = quizID;
        this.questionType = questionType;
    }

    public MCQChoice[] getChoices() {
        return choices;
    }

    public void setChoices(MCQChoice[] choices) {
        this.choices = choices;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
