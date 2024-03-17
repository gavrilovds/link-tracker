package edu.java.repository.chat;

import edu.java.dto.Chat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbcChatRepository implements ChatRepository {

    private final JdbcClient jdbcClient;

    @Override
    public void add(long chatId) {
        jdbcClient.sql("INSERT INTO chat(id) VALUES (:chatId)")
            .param("chatId", chatId)
            .update();
    }

    @Override
    public void remove(long chatId) {

    }

    @Override
    public List<Chat> findAll() {
        return jdbcClient.sql("SELECT * FROM chat")
            .query(Chat.class)
            .list();
    }

    @Override
    public boolean doesExist(long chatId) {
        return jdbcClient.sql("SELECT id FROM chat WHERE id = :id")
            .param("id", chatId)
            .query(Chat.class)
            .optional()
            .isPresent();
    }
}
