package edu.java.exception;

import org.springframework.http.HttpStatus;

public class ChatNotFoundException extends ApiErrorException {

    public ChatNotFoundException(long chatId) {
        super(
            HttpStatus.NOT_FOUND.value(),
            "Чат не найден",
            ChatNotFoundException.class.getSimpleName(),
            "Чат id = %d не найден".formatted(chatId)
        );
    }
}
