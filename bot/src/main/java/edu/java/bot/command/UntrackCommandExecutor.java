package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.command.Command.UNTRACK;

@Log4j2
public class UntrackCommandExecutor extends CommandExecutor {

    @Override
    protected SendMessage check(String command, long chatId) {
        if (!command.equals(UNTRACK.getCommandName())) {
            return checkNext(command, chatId);
        }
        log.info("Command /untrack has executed");
        return new SendMessage(chatId, "test");
    }
}
