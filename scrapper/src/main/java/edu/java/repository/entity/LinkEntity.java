package edu.java.repository.entity;

import edu.java.link_type_resolver.LinkType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "link")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String url;
    @Enumerated(EnumType.STRING)
    private LinkType linkType;
    private OffsetDateTime updatedAt;
    private OffsetDateTime lastCheckedAt;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "links")
    private Set<ChatEntity> tgChats = new HashSet<>();
}
