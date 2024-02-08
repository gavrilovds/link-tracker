package edu.java.bot.update_resolver;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.LinkService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class CallbackUpdateResolver extends UpdateResolver {

    private final LinkService linkService;

    @Override
    public SendMessage resolve(Update update) {
        if (update.callbackQuery() == null) {
            return resolveNext(update);
        }
        processCallback(update.callbackQuery().from().id(), update.callbackQuery().data());
        log.info("Link has been untracked");
        return new SendMessage(update.callbackQuery().from().id(), "Ссылка больше не отслеживается");
    }

    private void processCallback(long chatId, String data) {
        if (!data.startsWith("/untrack:")) {
            throw new RuntimeException("Invalid callback");
        }
        linkService.untrackLink(chatId, UUID.fromString(data.split(":")[1]));
    }
}
