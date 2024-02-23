package edu.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LinkResponse(@JsonProperty("id") long linkId, @JsonProperty("url") String link) {

}
