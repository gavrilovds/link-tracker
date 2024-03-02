package edu.java.client.bot;

import edu.java.client.AbstractWebClient;
import edu.java.service.BotService;

public class BotClient extends AbstractWebClient<BotService> {

    private static final String BASE_URL = "http://localhost:8090/";

    public BotClient() {
        this(BASE_URL);
    }

    public BotClient(String baseUrl) {
        super(baseUrl);
    }
}
