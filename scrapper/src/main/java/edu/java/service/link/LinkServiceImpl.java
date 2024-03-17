package edu.java.service.link;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.Link;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;
import edu.java.exception.LinkNotFoundException;
import edu.java.exception.UnsupportedLinkTypeException;
import edu.java.link_type_resolver.LinkType;
import edu.java.link_type_resolver.LinkTypeResolver;
import edu.java.repository.chat_link.ChatLinkRepository;
import edu.java.repository.link.LinkRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final ChatLinkRepository chatLinkRepository;
    private final LinkTypeResolver linkTypeResolver;

    @Override
    @Transactional
    public ListLinksResponse getAllLinks(long chatId) {
        return linkRepository.findAllByChatId(chatId);
    }

    @Override
    @Transactional
    public LinkResponse addLink(long chatId, AddLinkRequest addLinkRequest) {
        LinkType linkType = linkTypeResolver.resolve(addLinkRequest.link());
        if (linkType.equals(LinkType.UNKNOWN)) {
            throw new UnsupportedLinkTypeException(addLinkRequest);
        }
        long linkId = linkRepository.add(chatId, addLinkRequest, linkType);
        chatLinkRepository.add(linkId, chatId);
        return new LinkResponse(linkId, addLinkRequest.link());
    }

    @Override
    @Transactional
    public LinkResponse removeLink(long chatId, RemoveLinkRequest removeLinkRequest) {
        Optional<Link> optionalLink = linkRepository.findById(removeLinkRequest.linkId());
        if (optionalLink.isEmpty()) {
            throw new LinkNotFoundException(removeLinkRequest);
        }
        Link link = optionalLink.get();
        chatLinkRepository.remove(link.id(), chatId);
        if (chatLinkRepository.findAllByLinkId(link.id()).isEmpty()) {
            linkRepository.remove(link.id());
        }
        return new LinkResponse(link.id(), link.url());
    }
}
