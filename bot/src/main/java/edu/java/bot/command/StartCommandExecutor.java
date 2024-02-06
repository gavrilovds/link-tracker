package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.command.Command.START;

@Log4j2
public class StartCommandExecutor extends CommandExecutor {

    private static final String WELCOME_MESSAGE = """
        <b>Привет! 😊</b>
        Это бот для отслеживания изменений на GitHub и StackOverFlow. 🚀
        Справка по командам - /help""";

    @Override
    protected SendMessage execute(String command, long chatId) {
        if (!command.equals(START.getCommandName())) {
            return executeNext(command, chatId);
        }
        log.info("Command /start has executed");
        return new SendMessage(chatId, WELCOME_MESSAGE).parseMode(ParseMode.HTML);
    }
}
