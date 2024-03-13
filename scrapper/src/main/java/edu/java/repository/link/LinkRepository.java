package edu.java.repository.link;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;
import edu.java.link_type_resolver.LinkType;

public interface LinkRepository {

    ListLinksResponse getAllLinks(long chatId);

    LinkResponse addLink(long chatId, AddLinkRequest addLinkRequest, LinkType linkType);

    LinkResponse removeLink(long chatId, RemoveLinkRequest removeLinkRequest);
}
