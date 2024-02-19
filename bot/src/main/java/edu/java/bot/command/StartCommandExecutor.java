package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import static edu.java.bot.command.Command.START;
import static edu.java.bot.util.MessagesUtils.WELCOME_MESSAGE;

@Log4j2
@Component
public class StartCommandExecutor implements CommandExecutor {

    @Override
    public SendMessage execute(String command, long chatId) {
        log.info("Command /start has executed");
        return new SendMessage(chatId, WELCOME_MESSAGE).parseMode(ParseMode.HTML);
    }

    @Override
    public String getCommandName() {
        return START.getName();
    }
}
