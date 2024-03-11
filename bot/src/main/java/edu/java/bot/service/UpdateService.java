package edu.java.bot.service;

import edu.java.bot.dto.LinkUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateService {

    private final MessageSender messageSender;

    public void sendUpdate(LinkUpdate linkUpdate) {

    }
}
