package edu.java.client.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record GetRepoResponse(@JsonProperty("full_name") String repoFullName,
                              @JsonProperty("updated_at") OffsetDateTime lastUpdate) {

}
