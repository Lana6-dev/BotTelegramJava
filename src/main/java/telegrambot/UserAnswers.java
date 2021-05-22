package telegrambot;

import java.util.ArrayList;
import java.util.List;

public class UserAnswers {

    private List<String> userAnswer;

    public UserAnswers() {
        userAnswer = new ArrayList<>();

    }

    public void addAnswer(String message) {
        userAnswer.add(message);
    }

    public List<String> getAnswers() {
        return userAnswer;
    }
}
