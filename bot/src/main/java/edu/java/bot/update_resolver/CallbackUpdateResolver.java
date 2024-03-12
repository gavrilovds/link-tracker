package edu.java.bot.update_resolver;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.client.ScrapperClient;
import edu.java.bot.dto.RemoveLinkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.util.MessagesUtils.LINK_HAS_BEEN_UNTRACKED;
import static edu.java.bot.util.MessagesUtils.UNTRACK_ERROR;

@RequiredArgsConstructor
@Log4j2
public class CallbackUpdateResolver extends UpdateResolver {

    private final ScrapperClient scrapperClient;

    @Override
    public SendMessage resolve(Update update) {
        if (update.callbackQuery() == null) {
            return resolveNext(update);
        }
        return processCallback(update.callbackQuery().from().id(), update.callbackQuery().data());
    }

    private SendMessage processCallback(long chatId, String data) {
        if (!data.startsWith("/untrack:")) {
            throw new RuntimeException("Invalid callback");
        }
        try {
            scrapperClient.untrackLink(chatId, new RemoveLinkRequest(Long.parseLong(data.split(":")[1])));
            log.info("Link has been untracked");
            return new SendMessage(chatId, LINK_HAS_BEEN_UNTRACKED);
        } catch (Exception e) {
            return new SendMessage(chatId, UNTRACK_ERROR);
        }
    }
}
