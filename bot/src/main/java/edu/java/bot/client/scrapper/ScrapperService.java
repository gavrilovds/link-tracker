package edu.java.bot.client.scrapper;

import edu.java.bot.dto.AddLinkRequest;
import edu.java.bot.dto.LinkResponse;
import edu.java.bot.dto.ListLinksResponse;
import edu.java.bot.dto.RemoveLinkRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface ScrapperService {

    @GetExchange("/links")
    ListLinksResponse getAllLinks(@RequestHeader("Tg-Chat-Id") long chatId);

    @PostExchange("/links")
    LinkResponse trackLink(@RequestHeader("Tg-Chat-Id") long chatId, @RequestBody AddLinkRequest addLinkRequest);

    @DeleteExchange("/links")
    LinkResponse untrackLink(
        @RequestHeader("Tg-Chat-Id") long chatId,
        @RequestBody RemoveLinkRequest removeLinkRequest
    );
}
