package edu.java.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record LinkUpdate(@JsonProperty("id") long linkId, @JsonProperty("url") String link, String description,
                         List<Long> tgChatIds) {

}
