package fr.epita.QuizManager.Services.data;

import fr.epita.QuizManager.datamodel.MCQChoice;
import fr.epita.QuizManager.datamodel.MCQQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionBLService {
    public static String convertArraytoString(String[] stringArray) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < stringArray.length; i++) {
            sb.append(stringArray[i] + ",");
        }
        String str = sb.toString();
        return str;
    }

    public static String[] convertStringtoArray(String str) {
        return str.split("[,]", 10);
    }

    public static MCQChoice[] getOptionFromInput() {
        MCQChoice choices[] = new MCQChoice[4];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < choices.length; i++) {
            System.out.println("Option [" + (i+1) + "] : ");
            String choice = scanner.next();
            System.out.println("isRight?[y/n]");
            String isRight = scanner.next();
            Boolean isRightOption = (isRight.equals("") || isRight.equals("n")|| isRight.equals("0"));
            choices[i] = new MCQChoice(choice,!isRightOption);
        }

        return choices;
    }

    public static void printList(List<MCQQuestion> list) {
        for(MCQQuestion que: list) {
            System.out.println("que. : " + que.getQuestion());
            int count = 0;
            for(MCQChoice choice: que.getChoices()) {
                count++;
                if(que.getQuestionType().equals("m")) {
                    System.out.println(count + ". " + choice.getChoice());
                }

            }
            System.out.println("\n");
        }
    }
}
