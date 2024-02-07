package edu.java.bot.service;

import edu.java.bot.dto.Link;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    public List<Link> getAllTrackedLinks(long chatId) {
        return List.of(
            new Link(
                UUID.fromString("bcdf9843-7543-479b-8ac0-f2065335f820"),
                "https://github.com/sanyarnd/tinkoff-java-course-2023"
            ),
            new Link(
                UUID.fromString("86aeb965-7a88-421c-9792-36d95d6e0425"),
                "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"
            )
        );
        /*return Collections.emptyList();*/
    }

    public void trackLink(long chatId, String link) {
        // TO DO
    }

    public void untrackLink(long chatId, UUID linkId) {
        // TO DO
    }
}
