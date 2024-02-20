package edu.java.scrapper.information_reciever;

import edu.java.client.github.GithubClientImpl;
import edu.java.client.link_information.LinkInformationReceiver;
import edu.java.client.link_information.LinkInformationReceiverProvider;
import edu.java.link_type_resolver.LinkType;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LinkInformationReceiverProviderTest {

    @Test
    public void getReceiver_shouldReturnCorrectReceiver() {
        LinkInformationReceiverProvider provider = new LinkInformationReceiverProvider();
        provider.registerReceiver(LinkType.GITHUB, new GithubClientImpl());

        LinkInformationReceiver actual = provider.getReceiver(LinkType.GITHUB);

        assertThat(actual).isInstanceOf(GithubClientImpl.class);
    }
}
