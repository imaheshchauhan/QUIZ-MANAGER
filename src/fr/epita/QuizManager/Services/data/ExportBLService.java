package fr.epita.QuizManager.Services.data;

import fr.epita.QuizManager.datamodel.MCQChoice;
import fr.epita.QuizManager.datamodel.MCQQuestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExportBLService {
    private static final File file = new File("./mcq.txt");

    public static void exportQuiz( List<MCQQuestion> mcqQuestions) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(outputStream);

        for(MCQQuestion que: mcqQuestions) {
            int count = 0;
            pw.println(que.getQuestion());
            for (MCQChoice choice : que.getChoices()) {
                count++;
                pw.println(count + ". " + choice.getChoice());
            }
        }
        System.out.println("Exported to : " + file.getAbsolutePath());
        pw.flush();
        pw.close();
    }
}
