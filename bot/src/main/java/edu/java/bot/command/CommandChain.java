package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CommandChain {

    private final CommandExecutor parser = CommandExecutor.link(
        new StartCommandExecutor(),
        new HelpCommandExecutor(),
        new ListCommandExecutor(),
        new TrackCommandExecutor(),
        new UntrackCommandExecutor()
    );

    public SendMessage executeCommand(String command, long chatId) {
        if (command == null) {
            log.info("Null message has been received");
            return new SendMessage(chatId, "Для отправки доступен только текст!");
        }
        return parser.execute(command, chatId);
    }
}
