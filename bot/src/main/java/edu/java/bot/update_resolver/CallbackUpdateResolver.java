package edu.java.bot.update_resolver;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.LinkService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CallbackUpdateResolver extends UpdateResolver {

    private final LinkService linkService;

    @Override
    public SendMessage resolve(Update update) {
        if (update.callbackQuery() == null) {
            return resolveNext(update);
        }
        return new SendMessage(update.message().chat().id(), "callback");
    }
}
