package edu.java.client.link;

import edu.java.link_type_resolver.LinkType;

public interface LinkInformationProvider {

    LinkType getLinkType();

    LastUpdateTime receiveLastUpdateTime(String link);
}
