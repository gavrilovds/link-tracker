package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public abstract class CommandExecutor {

    private static final String ERROR_MESSAGE = """
        <b>Ошибка:</b> Команда не существует. ❌
        Пожалуйста, воспользуйтесь командой /help для получения списка доступных команд.
        """;

    private CommandExecutor next;

    public static CommandExecutor link(CommandExecutor first, CommandExecutor... chain) {
        CommandExecutor head = first;
        for (CommandExecutor nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    protected abstract SendMessage execute(String command, long chatId);

    protected SendMessage executeNext(String command, long chatId) {
        if (next == null) {
            return new SendMessage(chatId, ERROR_MESSAGE).parseMode(ParseMode.HTML);
        }
        return next.execute(command, chatId);
    }
}
