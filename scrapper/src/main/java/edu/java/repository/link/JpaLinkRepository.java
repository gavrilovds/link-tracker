package edu.java.repository.link;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.Link;
import edu.java.dto.ListLinksResponse;
import edu.java.link_type_resolver.LinkType;
import edu.java.repository.entity.LinkEntity;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLinkRepository extends LinkRepository, JpaRepository<LinkEntity, Long> {

    @Override
    ListLinksResponse findAll(long chatId);

    @Override
    long add(long chatId, AddLinkRequest addLinkRequest, LinkType linkType);

    @Override
    void remove(long linkId);

    @Override
    Optional<Link> findByUrl(String url);

    @Override
    Optional<Link> findById(long linkId);

    @Override
    ListLinksResponse findAllByChatId(long chatId);

    @Override
    List<Link> findUncheckedLinks(Duration checkPeriod);

    @Override
    void updateInfo(long linkId, OffsetDateTime updatedAt);
}
