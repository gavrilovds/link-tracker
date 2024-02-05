package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.command.Command.TRACK;

@Log4j2
public class TrackCommandExecutor extends CommandExecutor {

    @Override
    protected SendMessage check(String command, long chatId) {
        if (!command.equals(TRACK.getCommandName())) {
            return checkNext(command, chatId);
        }
        log.info("Command /track has executed");
        return new SendMessage(chatId, "test");
    }
}
