package edu.java.client.link;

import edu.java.link_type_resolver.LinkType;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LinkInformationReceiverManager {

    private final Map<LinkType, LinkInformationProvider> receivers = new HashMap<>();
    private final List<LinkInformationProvider> receiversList;

    @PostConstruct
    private void registerReceivers() {
        receiversList.forEach(receiver -> receivers.put(receiver.getLinkType(), receiver));
    }

    public Optional<LinkInformationProvider> getReceiver(LinkType linkType) {
        return Optional.ofNullable(receivers.get(linkType));
    }
}
