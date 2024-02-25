package edu.java.bot.update_resolver;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.command.CommandChain;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageUpdateResolver extends UpdateResolver {

    private final CommandChain commandChain;

    @Override
    public SendMessage resolve(Update update) {
        if (update.message() == null) {
            return resolveNext(update);
        }
        return commandChain.executeCommand(update.message().text(), update.message().chat().id());
    }
}
