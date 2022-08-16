package fr.epita.QuizManager.launcher;

import fr.epita.QuizManager.Services.data.Authentication;
import fr.epita.QuizManager.Services.data.ExportBLService;
import fr.epita.QuizManager.Services.data.QuestionBLService;
import fr.epita.QuizManager.Services.data.dao.QuestionDAO;
import fr.epita.QuizManager.Services.data.dao.QuizDAO;
import fr.epita.QuizManager.datamodel.MCQChoice;
import fr.epita.QuizManager.datamodel.MCQQuestion;
import fr.epita.QuizManager.datamodel.Quiz;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Launcher {

    public static void main(String[] args) throws SQLException, IOException {

        Scanner scanner = new Scanner(System.in);

       Boolean isAuth = Authentication.isAuthenticated();

        if (isAuth) {

            System.out.println("Credential is ok, Welcome");

            if (Authentication.username.equals("admin")) {
                adminFlow();
            } else {
                studentFlow();
            }
        } else {
            System.out.println("Bye");

            return;
        }

        scanner.close();
    }

    private static void adminFlow() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int option = 5;

        while (option != 0) {
            System.out.println("---MENU---");
            System.out.println("1. Create question");
            System.out.println("2. Update question");
            System.out.println("3. Delete question");
            System.out.println("4. Search question");
            System.out.println("0. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 0:
                    return;
                case 1:

                    System.out.println("Enter Quiz Title : ");
                    String quizTitle = scanner.next();
                    int quizID = QuizDAO.create(new Quiz(quizTitle));

                    System.out.println("Topic : ");
                    String strtopic = scanner.next();
                    String topicArray[] = QuestionBLService.convertStringtoArray(strtopic);

                    System.out.println("Question Type[mcq(m)/open(o)] : ");
                    String queType = scanner.next();
                    if (queType.equals("m") || queType.equals("mcq")) {
                        queType = "m";
                    } else {
                        queType = "o";
                    }

                    System.out.println("Question : ");
                    scanner.nextLine();
                    String que = scanner.nextLine();

                    System.out.println("Difficulty[1-5] : ");
                    int difficulty = 3;
                    difficulty =  scanner.nextInt();
                    if (difficulty > 5) {
                        difficulty = 5;
                    }

                    MCQChoice choices[] = new MCQChoice[0];
                    if (queType.equals("m")) {
                        choices = QuestionBLService.getOptionFromInput();
                    }

                    MCQQuestion mcqQue = new MCQQuestion(que, topicArray, difficulty, choices, quizID, queType);

                    QuestionDAO.create(mcqQue);

                    break;

                case 2:

                    System.out.println("Question Type[mcq(m)/open(o)] : ");
                    queType = scanner.next();
                    if (queType.equals("m") || queType.equals("mcq")) {
                        queType = "m";
                    } else {
                        queType = "o";
                    }

                    System.out.println("Question : ");
                    que = scanner.next();

                    choices = new MCQChoice[0];
                    if (queType.equals("m")) {
                        choices = QuestionBLService.getOptionFromInput();
                    }
                    QuestionDAO.update(queType, que, choices);
                    break;

                case 3:
                    QuestionDAO.readAll();
                    System.out.println("Enter Question ID : ");
                    int id = scanner.nextInt();
                    QuestionDAO.delete(id);
                    break;

                case 4:
                    System.out.println("1. Search by Topic");
                    System.out.println("2. Search by Quiz Title");
                    System.out.println("Choose Option");

                    int optionSearch = scanner.nextInt();


                    List<MCQQuestion> ques = null;
                    if (optionSearch == 1) {
                        System.out.println("Enter Topic : ");
                        String topicSearch = scanner.next();
                        ques = QuestionDAO.search(topicSearch,"");
                    } else if (optionSearch == 2) {
                        System.out.println("Enter Quiz Title : ");
                        String titleQuiz = scanner.next();
                        ques = QuestionDAO.search("",titleQuiz);
                    }
                    
                    QuestionBLService.printList(ques);
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }


    }

    private static void studentFlow() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int option = 5;

        while (option != 0) {
            System.out.println("---MENU---");
            System.out.println("1. Start MCQ Quiz");
            System.out.println("2. Export MCQ Quiz");
            System.out.println("0. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 0:
                    break;
                case 1:
                    System.out.println("Topic : ");
                    String strtopic = scanner.next();

                    List<MCQQuestion> queArray = QuestionDAO.search(strtopic,"");
                    List<MCQQuestion> mcqQuestions = new ArrayList<MCQQuestion>();
                    for (MCQQuestion mcqs: queArray) {
                        if(mcqs.getQuestionType().equals("m")){
                            mcqQuestions.add(mcqs);
                        }
                    }

                    int marks = 0;
                    int ques = 0;
                    for(MCQQuestion que: mcqQuestions) {
                       ques++;
                        int count = 0;
                        System.out.println(que.getQuestion());
                        for(MCQChoice choice: que.getChoices()) {
                            count++;
                            System.out.println(count + ". " + choice.getChoice());
                        }
                        System.out.println("Choose Answer : ");
                        int ans = 0;
                        while (true) {
                            ans = scanner.nextInt();
                            if (ans > 4) {
                                System.out.println("Invalid input");
                            } else {
                                break;
                            }
                        }

                        if (que.getChoices()[ans-1].getValid()) {
                            marks++;
                        }
                    }

                    System.out.println("Total Question : " + ques);
                    System.out.println("Total Marks : " + marks);
                    break;
                case 2:
                    System.out.println("Topic : ");
                    strtopic = scanner.next();
                    queArray = QuestionDAO.search(strtopic,"");
                    mcqQuestions = new ArrayList<MCQQuestion>();
                    for (MCQQuestion mcqs: queArray) {
                        if(mcqs.getQuestionType().equals("m")){
                            mcqQuestions.add(mcqs);
                        }
                    }
                    ExportBLService.exportQuiz(mcqQuestions);
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }


}