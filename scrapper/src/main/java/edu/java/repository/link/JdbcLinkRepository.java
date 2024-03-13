package edu.java.repository.link;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;
import edu.java.link_type_resolver.LinkType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcLinkRepository implements LinkRepository {

    private final JdbcClient jdbcClient;

    @Override
    public ListLinksResponse getAllLinks(long chatId) {
        return new ListLinksResponse(jdbcClient.sql("""
            SELECT link_id, url as link FROM link
            JOIN  chat_link ON link.id = chat_link.link_id
            WHERE chat_id = ?
            """).param(chatId).query(LinkResponse.class).list());
    }

    @Override
    public LinkResponse addLink(long chatId, AddLinkRequest addLinkRequest, LinkType linkType) {
        return null;
    }

    @Override
    public LinkResponse removeLink(long chatId, RemoveLinkRequest removeLinkRequest) {
        return null;
    }
}
