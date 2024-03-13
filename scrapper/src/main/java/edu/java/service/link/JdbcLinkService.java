package edu.java.service.link;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;
import edu.java.exception.UnsupportedLinkTypeException;
import edu.java.link_type_resolver.LinkType;
import edu.java.link_type_resolver.LinkTypeResolver;
import edu.java.repository.link.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {

    private final LinkRepository linkRepository;
    private final LinkTypeResolver linkTypeResolver;

    @Override
    public ListLinksResponse getAllLinks(long chatId) {
        return linkRepository.getAllLinks(chatId);
    }

    @Override
    public LinkResponse addLink(long chatId, AddLinkRequest addLinkRequest) {
        LinkType linkType = linkTypeResolver.resolve(addLinkRequest.link());
        if (linkType.equals(LinkType.UNKNOWN)) {
            throw new UnsupportedLinkTypeException(addLinkRequest);
        }
        return null;
    }

    @Override
    public LinkResponse removeLink(long chatId, RemoveLinkRequest removeLinkRequest) {
        return null;
    }
}
