package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class CommandChain {

    private final CommandExecutor commandExecutor;

    public SendMessage executeCommand(String command, long chatId) {
        if (command == null) {
            log.info("Null message has been received");
            return new SendMessage(chatId, "Для отправки доступен только текст!");
        }
        return commandExecutor.execute(command, chatId);
    }
}
