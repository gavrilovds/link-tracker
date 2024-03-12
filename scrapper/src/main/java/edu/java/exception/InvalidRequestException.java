package edu.java.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ApiErrorException {

    public InvalidRequestException() {
        super(
            HttpStatus.BAD_REQUEST.value(),
            "Некорректный запрос",
            InvalidRequestException.class.getSimpleName(),
            "Проверьте передавыемые параметры"
        );
    }
}
