package edu.java.client.link;

import edu.java.link_type_resolver.LinkType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class LinkInformationReceiverManager {

    private final Map<LinkType, LinkInformationProvider> receivers = new HashMap<>();

    public LinkInformationReceiverManager(List<LinkInformationProvider> receiversList) {
        receiversList.forEach(receiver -> receivers.put(receiver.getLinkType(), receiver));
    }

    public Optional<LinkInformationProvider> getReceiver(LinkType linkType) {
        return Optional.ofNullable(receivers.get(linkType));
    }
}
