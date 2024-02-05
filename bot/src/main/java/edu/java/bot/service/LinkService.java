package edu.java.bot.service;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    public List<String> getAllTrackedLinks(long chatId) {
        return List.of(
            "https://github.com/sanyarnd/tinkoff-java-course-2023/",
            "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"
        );
    }

    public void trackLink(long chatId, String link) {
        // TO DO
    }

    public void untrackLink(long chatId, String link) {
        // TO DO
    }
}
