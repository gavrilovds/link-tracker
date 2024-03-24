package edu.java.service.link;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;

public interface LinkService {

    ListLinksResponse getAllLinks(long chatId);

    LinkResponse addLink(long chatId, AddLinkRequest addLinkRequest);

    LinkResponse removeLink(long chatId, RemoveLinkRequest removeLinkRequest);
}
