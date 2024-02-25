package edu.java.bot.client.scrapper;

import edu.java.bot.client.AbstractWebClient;
import edu.java.bot.dto.AddLinkRequest;
import edu.java.bot.dto.LinkResponse;
import edu.java.bot.dto.ListLinksResponse;
import edu.java.bot.dto.RemoveLinkRequest;

public class ScrapperClient extends AbstractWebClient {

    private static final String BASE_URL = "http://localhost:8080/";
    private final ScrapperService scrapperService;

    public ScrapperClient() {
        this(BASE_URL);
    }

    public ScrapperClient(String baseUrl) {
        super(baseUrl);
        scrapperService = factory.createClient(ScrapperService.class);
    }

    public ListLinksResponse getAllTrackedLinks(long chatId) {
        return scrapperService.getAllLinks(chatId);
    }

    public LinkResponse trackLink(long chatId, AddLinkRequest addLinkRequest) {
        return scrapperService.trackLink(chatId, addLinkRequest);
    }

    public LinkResponse untrackLink(long chatId, RemoveLinkRequest removeLinkRequest) {
        return scrapperService.untrackLink(chatId, removeLinkRequest);
    }
}
