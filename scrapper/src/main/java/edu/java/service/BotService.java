package edu.java.service;

import edu.java.dto.LinkUpdate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface BotService {

    @PostExchange("/updates")
    void sendUpdate(@RequestBody LinkUpdate linkUpdate);
}
