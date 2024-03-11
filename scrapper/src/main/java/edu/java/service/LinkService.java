package edu.java.service;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;
import edu.java.exception.ChatAlreadyRegisteredException;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    public ListLinksResponse getAllLinks(long chatId) {
        throw new ChatAlreadyRegisteredException(chatId);
        //return new ListLinksResponse(Collections.emptyList());
    }

    public LinkResponse addLink(long chatId, AddLinkRequest addLinkRequest) {
        return new LinkResponse(0, null);
    }

    public LinkResponse removeLink(long chatId, RemoveLinkRequest removeLinkRequest) {
        return new LinkResponse(0, null);
    }
}
