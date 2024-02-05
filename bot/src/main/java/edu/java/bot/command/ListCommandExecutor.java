package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.command.Command.LIST;

@Log4j2
public class ListCommandExecutor extends CommandExecutor {

    @Override
    protected SendMessage check(String command, long chatId) {
        if (!command.equals(LIST.getCommandName())) {
            return checkNext(command, chatId);
        }
        log.info("Command /list has executed");
        return buildMessage(chatId);
    }

    private SendMessage buildMessage(long chatId) {
        return new SendMessage(chatId, "test");
    }
}
