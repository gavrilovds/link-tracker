package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.client.ScrapperClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import static edu.java.bot.command.Command.START;
import static edu.java.bot.util.MessagesUtils.CHAT_ALREADY_EXIST;
import static edu.java.bot.util.MessagesUtils.WELCOME_MESSAGE;

@Log4j2
@Component
@RequiredArgsConstructor
public class StartCommandExecutor implements CommandExecutor {

    private final ScrapperClient scrapperClient;

    @Override
    public SendMessage execute(String command, long chatId) {
        log.info("Command /start has executed");
        try {
            scrapperClient.registerChat(chatId);
            return new SendMessage(chatId, WELCOME_MESSAGE).parseMode(ParseMode.HTML);
        } catch (Exception e) {
            return new SendMessage(chatId, CHAT_ALREADY_EXIST);
        }
    }

    @Override
    public String getCommandName() {
        return START.getName();
    }
}
