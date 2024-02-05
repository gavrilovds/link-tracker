package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.command.Command.HELP;
import static edu.java.bot.command.Command.LIST;
import static edu.java.bot.command.Command.TRACK;
import static edu.java.bot.command.Command.UNTRACK;

@Log4j2
public class HelpCommandExecutor extends CommandExecutor {

    private static final String HELP_MESSAGE = """
        <b>Доступные команды:</b>
        %s - %s
        %s - %s
        %s - %s
        %s - %s""".formatted(TRACK.getCommandName(),
        TRACK.getCommandDescription().toLowerCase(),
        UNTRACK.getCommandName(),
        UNTRACK.getCommandDescription().toLowerCase(),
        LIST.getCommandName(), LIST.getCommandDescription().toLowerCase(),
        HELP.getCommandName(), HELP.getCommandDescription().toLowerCase()
    );

    @Override
    protected SendMessage check(String command, long chatId) {
        if (!command.equals(HELP.getCommandName())) {
            return checkNext(command, chatId);
        }
        log.info("Command /help has executed");
        return new SendMessage(chatId, HELP_MESSAGE).parseMode(ParseMode.HTML);
    }
}
