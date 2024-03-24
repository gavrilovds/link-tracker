package edu.java.repository.chat;

import edu.java.dto.Chat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChatRepository extends ChatRepository, JpaRepository<Chat, Long> {

    @Override
    void add(long chatId);

    @Override
    void remove(long chatId);

    @Override
    List<Chat> findAll();

    @Override
    boolean doesExist(long chatId);
}
