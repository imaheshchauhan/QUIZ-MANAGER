package fr.epita.QuizManager.datamodel;

public abstract class Question {

    String question;
    String[] topics;
    int difficulty;

    public Question(String question, String[] topics, int difficulty) {
        this.question = question;
        this.topics = topics;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
