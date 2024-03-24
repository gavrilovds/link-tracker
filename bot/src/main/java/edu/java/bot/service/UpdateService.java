package edu.java.bot.service;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.dto.LinkUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateService {

    private final MessageSender messageSender;

    public void sendUpdate(LinkUpdate linkUpdate) {
        for (Long chatId : linkUpdate.tgChatIds()) {
            messageSender.sendMessage(new SendMessage(chatId, linkUpdate.link() + " - " + linkUpdate.description()));
        }
    }
}
