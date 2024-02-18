package edu.java.client.link_information;

import edu.java.link_type_resolver.LinkType;

public interface LinkInformationReceiver {

    LinkType getLinkType();

    LinkInformation receiveLinkInformation(String link);

    default boolean canReceive(LinkType linkType) {
        return linkType.equals(getLinkType());
    }
}
