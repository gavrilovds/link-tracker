package edu.java.exception;

import org.springframework.http.HttpStatus;

public class ChatAlreadyRegisteredException extends ApiErrorException {

    public ChatAlreadyRegisteredException(long chatId) {
        super(
            HttpStatus.CONFLICT.value(),
            "Чат уже зарегистрирован",
            ChatAlreadyRegisteredException.class.getSimpleName(),
            "Чат id = %d уже зарегистрирован".formatted(chatId)
        );
    }
}
