package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

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

    protected abstract SendMessage check(String command, long chatId);

    protected SendMessage checkNext(String command, long chatId) {
        if (next == null) {
            return new SendMessage(chatId, ERROR_MESSAGE).parseMode(ParseMode.HTML);
        }
        return next.check(command, chatId);
    }
}
