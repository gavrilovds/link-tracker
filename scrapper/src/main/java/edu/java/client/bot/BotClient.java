package edu.java.client.bot;

import edu.java.client.AbstractWebClient;
import edu.java.dto.LinkUpdate;
import edu.java.service.BotService;

public class BotClient extends AbstractWebClient<BotService> {

    public BotClient(String baseUrl) {
        super(baseUrl);
    }

    public void sendUpdate(LinkUpdate update) {
        service.sendUpdate(update);
    }
}
