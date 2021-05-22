package telegrambot;

import java.util.ArrayList;
import java.util.List;

public class TrueAnswer {

    private List<String> answers;

    public TrueAnswer(){
        answers = new ArrayList<>();
        //тут дожен быть верный ответ
        //он должен полность совпадать с ответами с класса Answers
        answers.add("1a) первый ответ");
        answers.add("a) первый ответ");
        answers.add("a) первый ответ");
        answers.add("a) первый ответ");
        answers.add("a) первый ответ");
    }

    public List<String> getAllTrueAnswers(){
        return answers;
    }


}
