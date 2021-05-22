package telegrambot;

import java.util.*;

public class Answers {

    private Map<Integer, List<String>> answers;

    public Answers(){
        answers = new HashMap<>();
        //добовляем ответы в соответсвием с номером вопроса
        answers.put(1, Arrays.asList("1a) первый ответ", "б) второй ответ", "в) третий ответ","г) четвертый ответ"));
        answers.put(2, Arrays.asList("2a) пе ответ", "б) второй ответ", "в) третий ответ","г) четвертый ответ"));
        answers.put(3, Arrays.asList("3a) перв ответ", "б) второй ответ", "в) третий ответ","г) четвертый ответ"));
        answers.put(4, Arrays.asList("4a) первый ответ", "б) второй ответ", "в) третий ответ","г) четвертый ответ"));
        answers.put(5, Arrays.asList("5a) перй ответ", "б) второй ответ", "в) третий ответ","г) четвертый ответ"));
    }

    public List<String> getAnswers(int number){
        return answers.get(number);
    }

    public List<String> getAll(){
        List<String> result = new ArrayList<>();
        for (List<String> value : answers.values()) {
            result.addAll(value);
        }
        return result;
    }
}
