package edu.java.bot.client;

import edu.java.bot.dto.AddLinkRequest;
import edu.java.bot.dto.LinkResponse;
import edu.java.bot.dto.ListLinksResponse;
import edu.java.bot.dto.RemoveLinkRequest;
import edu.java.bot.service.ScrapperService;

public class ScrapperClient extends AbstractWebClient<ScrapperService> {

    public ScrapperClient(String baseUrl) {
        super(baseUrl);
    }

    public ListLinksResponse getAllTrackedLinks(long chatId) {
        return service.getAllLinks(chatId);
    }

    public LinkResponse trackLink(long chatId, AddLinkRequest addLinkRequest) {
        return service.trackLink(chatId, addLinkRequest);
    }

    public LinkResponse untrackLink(long chatId, RemoveLinkRequest removeLinkRequest) {
        return service.untrackLink(chatId, removeLinkRequest);
    }

    public void registerChat(long chatId) {
        service.registerChat(chatId);
    }

    public void deleteChat(long chatId) {
        service.deleteChat(chatId);
    }
}
