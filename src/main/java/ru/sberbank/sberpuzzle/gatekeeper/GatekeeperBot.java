package ru.sberbank.sberpuzzle.gatekeeper;

import io.sentry.Sentry;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GatekeeperBot extends TelegramLongPollingBot {
    @Value("${bot.gatekeeper.name}")
    String botUsername;

    @Value("${bot.gatekeeper.token}")
    String botToken;

    @PostConstruct
    public void registerBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            Sentry.captureException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.enableHtml(true);

            if (message.hasText()) {
                String text = message.getText().toLowerCase().replace(" ", "");
                sendMessage.setText(inputProcessor(text));
            } else {
                sendMessage.setText("Я жду от вас кодовое слово в текстовом формате!");
            }
            execute(sendMessage);
        } catch (TelegramApiException e) {
            Sentry.captureException(e);
        }
    }

    private String inputProcessor(String text) {
        switch (text) {
            case "/start": return "Введите кодовое слово.";
            case "spirallingdowntotherabbitshole": return "Аа. Так ты из наших? Не признал сразу. Держи ссылку: https://t.me/joinchat/VIIlcs1MICEwZTVi";
            case "/help": return "\uD83D\uDD39<b>Подсказка</b>\uD83D\uDD39\n\n<i>Вы можете получить секретный код, расшифровав второе сообщение на сайте.</i>";
            default: return "Вы ввели неверное кодовое слово!";
        }
    }
}
