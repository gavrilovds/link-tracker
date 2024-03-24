package edu.java.service.chat;

import edu.java.exception.ChatAlreadyRegisteredException;
import edu.java.repository.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    @Transactional
    public void registerChat(long chatId) {
        if (chatRepository.doesExist(chatId)) {
            throw new ChatAlreadyRegisteredException(chatId);
        }
        chatRepository.add(chatId);
    }

    @Override
    @Transactional
    public void deleteChat(long chatId) {

    }
}
