package edu.java.controller;

import edu.java.dto.AddLinkRequest;
import edu.java.dto.LinkResponse;
import edu.java.dto.ListLinksResponse;
import edu.java.dto.RemoveLinkRequest;
import edu.java.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
@Log4j2
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/{chat_id}")
    public ListLinksResponse getAllLinks(@PathVariable("chat_id") long chatId) {
        log.info("Get all links for chat with id {}", chatId);
        return linkService.getAllLinks(chatId);
    }

    @PostMapping("/{chat_id}")
    public LinkResponse addLink(
        @PathVariable("chat_id") long chatId,
        @RequestBody AddLinkRequest addLinkRequest
    ) {
        log.info("Add link to chat with id {}", chatId);
        return linkService.addLink(chatId, addLinkRequest);
    }

    @DeleteMapping("/{chat_id}")
    public LinkResponse removeLink(
        @PathVariable("chat_id") long chatId,
        @RequestBody RemoveLinkRequest removeLinkRequest
    ) {
        log.info("Remove link from chat with id {}", chatId);
        return linkService.removeLink(chatId, removeLinkRequest);
    }
}
