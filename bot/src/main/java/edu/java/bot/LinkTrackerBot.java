package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import edu.java.bot.command.CommandChain;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static edu.java.bot.command.Command.HELP;
import static edu.java.bot.command.Command.LIST;
import static edu.java.bot.command.Command.TRACK;
import static edu.java.bot.command.Command.UNTRACK;

@Log4j2
@Component
public class LinkTrackerBot extends TelegramBot {

    private final BotCommand[] commands = new BotCommand[] {
        new BotCommand(TRACK.getCommandName(), TRACK.getCommandDescription()),
        new BotCommand(UNTRACK.getCommandName(), UNTRACK.getCommandDescription()),
        new BotCommand(LIST.getCommandName(), LIST.getCommandDescription()),
        new BotCommand(HELP.getCommandName(), HELP.getCommandDescription())
    };

    private final CommandChain commandChain;

    public LinkTrackerBot(@Value("${app.telegram-token}") String botToken, CommandChain commandChain) {
        super(botToken);
        this.commandChain = commandChain;
        initBot();
    }

    private void initBot() {
        execute(new SetMyCommands(commands));
        setUpdatesListener(updates -> {
            for (Update update : updates) {
                log.info("A message \"{}\" has been received", update.message().text());
                processUpdate(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private void processUpdate(Update update) {
        long chatId = update.message().chat().id();
        SendMessage toSend = commandChain.parseCommand(update.message().text(), chatId);
        execute(toSend);
    }
}
