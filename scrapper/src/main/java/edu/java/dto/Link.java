package edu.java.dto;

import edu.java.link_type_resolver.LinkType;
import java.time.OffsetDateTime;
import lombok.Builder;

@Builder
public record Link(long id, String url, LinkType linkType, OffsetDateTime updatedAt, OffsetDateTime lastCheckedAt) {

}
