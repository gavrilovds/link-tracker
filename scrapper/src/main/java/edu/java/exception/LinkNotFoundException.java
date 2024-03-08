package edu.java.exception;

import edu.java.dto.RemoveLinkRequest;
import org.springframework.http.HttpStatus;

public class LinkNotFoundException extends ApiErrorException {

    public LinkNotFoundException(RemoveLinkRequest removeLinkRequest) {
        super(
            HttpStatus.NOT_FOUND.value(),
            "Ссылка не найдена",
            LinkNotFoundException.class.getSimpleName(),
            "Ссылка id = %s не найдена".formatted(removeLinkRequest.linkId())
        );
    }
}
