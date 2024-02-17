package edu.java.client.dto.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record GetQuestionResponse(@JsonProperty("title") String questionTitle,
                                  @JsonProperty("last_activity_date") OffsetDateTime lastUpdate) {

}
