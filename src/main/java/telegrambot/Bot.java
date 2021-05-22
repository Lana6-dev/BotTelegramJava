package telegrambot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private String botUSerName = "My_testJavaChatBot";

    private String botToken = "1632157131:AAEy9wNCVDptmDNT6hVhWrDwhhJzEFBWEok";

    private Questions questions;

    private Answers answers;

    private ReplyKeyboardMarkup clava;

    private UserAnswers userAnswers;

    private static int counter = 0;


    public Bot() {
        super(new DefaultBotOptions());
        questions = new Questions();
        answers = new Answers();
        clava = new ReplyKeyboardMarkup();
        userAnswers = new UserAnswers();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                sendMsg(update.getMessage().getChatId(),
                        update.getMessage().getText());
            }
        }
    }

    private void sendMsg(Long chatId, String message) {

        SendMessage sendMessage;
        String send = "";

        if (message.equals("/start") || message.equals("/Start")) {
            //нужно в кавычках добавить приветствующий текст
            send = "Здравствуйте! Вы готовы начать?";
            sendMessage = createMessage(send, chatId);
            ReplyKeyboardMarkup clava = new ReplyKeyboardMarkup();
            KeyboardRow go = new KeyboardRow();
            go.add("Начать");
            clava.setKeyboard(Arrays.asList(go));
            sendMessage.setReplyMarkup(clava);

        } else if (answers.getAll().contains(message) || message.equals("Начать")) {
            if(!message.equals("Начать")) {
                userAnswers.addAnswer(message);
            }

            String question = createQuestion(counter);
            if (question == null) {
                String endMessage = createEndMessage();
                sendMessage = createMessage(endMessage,chatId);
                sendMessage.setReplyMarkup(null);

            }else {
                ReplyKeyboardMarkup clava = createClava(counter);
                sendMessage = createMessage(question, chatId);
                sendMessage.setReplyMarkup(clava);
                counter++;
            }

        } else {
            //тут надо добавить сообщени если пользователь пытается ввести что-то не так
            sendMessage = createMessage("Что-то пошло не так", chatId);
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup createClava(int counter) {
        List<String> answers = this.answers.getAnswers(counter + 1);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        for (String answer : answers) {
            KeyboardRow ansverKeyBoard = new KeyboardRow();
            ansverKeyBoard.add(answer);
            keyboardRows.add(ansverKeyBoard);
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;

    }

    private String createEndMessage() {

        List<String> answers = userAnswers.getAnswers();

        TrueAnswer trueAnswer = new TrueAnswer();

        List<String> allTrueAnswers = trueAnswer.getAllTrueAnswers();

        int counterTrueAnswer = 0;

        for (int i = 0; i < answers.size(); i++) {
            if(answers.get(i).equals(allTrueAnswers.get(i))){
                counterTrueAnswer++;
            }
        }

        double procentTrue = counterTrueAnswer*(double)100/answers.size();
        //тут написать что-нибудь о завершении теста
        String answer = "Вы молодцы тест пройден на "+procentTrue;
        return answer;

    }

    private String createQuestion(int number) {
        if (questions.hasNext()) {
            String question = questions.nextQuestion();
            return question;
        }
        return null;

    }

    private SendMessage createMessage(String send, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(send);
        return sendMessage;
    }


    @Override
    public String getBotUsername() {
        return botUSerName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

