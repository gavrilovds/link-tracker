package edu.java.scheduler;

import edu.java.client.bot.BotClient;
import edu.java.client.link.LastUpdateTime;
import edu.java.client.link.LinkInformationProvider;
import edu.java.client.link.LinkInformationReceiverManager;
import edu.java.dto.Chat;
import edu.java.dto.Link;
import edu.java.dto.LinkUpdate;
import edu.java.repository.chat_link.ChatLinkRepository;
import edu.java.repository.link.LinkRepository;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;

@Log4j2
@RequiredArgsConstructor
public class LinkUpdaterScheduler {

    private static final Duration CHECK_PERIOD = Duration.ofSeconds(15);
    private final LinkRepository linkRepository;
    private final ChatLinkRepository chatLinkRepository;
    private final LinkInformationReceiverManager linkInformationReceiverManager;
    private final BotClient botClient;

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {

        log.info("Update has been started");
        List<Link> links = linkRepository.findUncheckedLinks(CHECK_PERIOD);

        log.info("Updating {}", links);
        List<LinkUpdate> linkUpdates = new ArrayList<>();
        for (Link link : links) {
            LinkInformationProvider linkInformationProvider =
                linkInformationReceiverManager.getReceiver(link.linkType()).get();
            LastUpdateTime lastUpdateTime = linkInformationProvider.receiveLastUpdateTime(link.url());
            if (!lastUpdateTime.lastUpdate().equals(link.updatedAt()) && link.updatedAt() != null) {
                linkUpdates.add(new LinkUpdate(link.id(), link.url(), "Произошли изменения", getChatIds(link.id())));
            }
            linkRepository.updateInfo(link.id(), lastUpdateTime.lastUpdate());
        }

        log.info("Sending updates: {}", linkUpdates);
        for (LinkUpdate linkUpdate : linkUpdates) {
            botClient.sendUpdate(linkUpdate);
        }
    }

    private List<Long> getChatIds(long id) {
        List<Chat> chats = chatLinkRepository.findAllByLinkId(id);
        return chats.stream().map(Chat::chatId).toList();
    }
}
