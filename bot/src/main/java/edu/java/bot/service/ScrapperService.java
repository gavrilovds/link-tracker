package edu.java.bot.service;

import edu.java.bot.dto.AddLinkRequest;
import edu.java.bot.dto.LinkResponse;
import edu.java.bot.dto.ListLinksResponse;
import edu.java.bot.dto.RemoveLinkRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface ScrapperService {

    @GetExchange("/links/{chat_id}")
    ListLinksResponse getAllLinks(@PathVariable("chat_id") long chatId);

    @PostExchange("/links/{chat_id}")
    LinkResponse trackLink(@PathVariable("chat_id") long chatId, @RequestBody AddLinkRequest addLinkRequest);

    @DeleteExchange("/links/{chat_id}")
    LinkResponse untrackLink(
        @PathVariable("chat_id") long chatId,
        @RequestBody RemoveLinkRequest removeLinkRequest
    );

    @PostExchange("/tg-chat/{id}")
    void registerChat(@PathVariable("id") long chatId);

    @DeleteExchange("/tg-chat/{id}")
    void deleteChat(@PathVariable("id") long chatId);
}
