package edu.java.client.stackoverflow;

import edu.java.client.AbstractWebClient;
import edu.java.client.dto.stackoverflow.GetQuestionResponse;
import edu.java.client.link.LastUpdateTime;
import edu.java.client.link.LinkInformationProvider;
import edu.java.link_type_resolver.LinkType;
import edu.java.service.StackOverflowService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackOverflowClient extends AbstractWebClient<StackOverflowService> implements LinkInformationProvider {

    private static final Pattern STACKOVERFLOW_LINK_PATTERN =
        Pattern.compile("https://stackoverflow.com/questions/(\\d+).*");

    public StackOverflowClient(String baseUrl) {
        super(baseUrl);
    }

    @Override
    public LinkType getLinkType() {
        return LinkType.STACKOVERFLOW;
    }

    @Override
    public LastUpdateTime receiveLastUpdateTime(String link) {
        Matcher matcher = STACKOVERFLOW_LINK_PATTERN.matcher(link);
        if (!matcher.find()) {
            return null;
        }
        GetQuestionResponse response = service.getQuestion(matcher.group(1));
        return new LastUpdateTime(response.items().get(0).lastUpdate());
    }
}
