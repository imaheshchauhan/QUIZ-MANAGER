package fr.epita.QuizManager.datamodel;

public class MCQChoice {

    String choice;
    Boolean valid;

    public MCQChoice(String choice, Boolean valid) {
        this.choice = choice;
        this.valid = valid;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
