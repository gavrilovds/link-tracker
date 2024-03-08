package edu.java.exception;

import edu.java.dto.AddLinkRequest;
import org.springframework.http.HttpStatus;

public class LinkAlreadyTrackedException extends ApiErrorException {

    public LinkAlreadyTrackedException(AddLinkRequest addLinkRequest) {
        super(
            HttpStatus.CONFLICT.value(),
            "Ссылка уже отслеживается",
            LinkAlreadyTrackedException.class.getSimpleName(),
            "Ссылка %s уже отслеживается".formatted(addLinkRequest.link())
        );
    }
}
