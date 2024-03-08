package edu.java.exception;

import edu.java.dto.AddLinkRequest;
import org.springframework.http.HttpStatus;

public class UnsupportedLinkTypeException extends ApiErrorException {

    public UnsupportedLinkTypeException(AddLinkRequest addLinkRequest) {
        super(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Тип ссылки не поддерживается",
            UnsupportedLinkTypeException.class.getSimpleName(),
            "Тип ссылки %s не поддерживается".formatted(addLinkRequest.link())
        );
    }
}
