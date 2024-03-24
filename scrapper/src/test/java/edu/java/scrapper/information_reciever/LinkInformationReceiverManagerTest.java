package edu.java.scrapper.information_reciever;

import edu.java.client.github.GithubClient;
import edu.java.client.link.LinkInformationProvider;
import edu.java.client.link.LinkInformationReceiverManager;
import edu.java.link_type_resolver.LinkType;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LinkInformationReceiverManagerTest {

    @Test
    public void getReceiver_shouldReturnCorrectReceiver() {
        LinkInformationProvider test = new GithubClient("test", Collections.emptyMap());
        LinkInformationReceiverManager manager = new LinkInformationReceiverManager(List.of(test));

        LinkInformationProvider actual = manager.getReceiver(LinkType.GITHUB).get();

        assertThat(actual).isEqualTo(test);
    }

}
