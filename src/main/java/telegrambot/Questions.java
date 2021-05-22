package telegrambot;

import java.util.ArrayList;
import java.util.List;

public class
Questions {

    private List<String> questionsAnswers;


    private int counter;

    public Questions() {

        questionsAnswers = new ArrayList<>();

        counter = 0;

        //добовляем вопросы
        //сколько угодно вопросов
        questionsAnswers.add("1. Вопрос");
        questionsAnswers.add("2. Вопрос");
        questionsAnswers.add("3. Вопрос");
        questionsAnswers.add("4. Вопрос");
        questionsAnswers.add("5. Вопрос");

    }

    public String nextQuestion() {

        return questionsAnswers.get(counter++);

    }

    public boolean hasNext() {
        return counter < questionsAnswers.size();
    }
}
