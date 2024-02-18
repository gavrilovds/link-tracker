package edu.java.client.link_information;

import edu.java.client.dto.stackoverflow.GetQuestionResponse;
import edu.java.link_type_resolver.LinkType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowLinkInformationReceiver implements LinkInformationReceiver {

    private static final String BASE_URL = "https://api.stackexchange.com/2.3/";
    private static final Pattern STACKOVERFLOW_LINK_PATTERN =
        Pattern.compile("https://stackoverflow.com/questions/(\\d+).*");

    private final WebClient webClient;

    public StackOverflowLinkInformationReceiver() {
        this(BASE_URL);
    }

    public StackOverflowLinkInformationReceiver(String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public LinkType getLinkType() {
        return LinkType.STACKOVERFLOW;
    }

    @Override
    public LinkInformation receiveLinkInformation(String link) {
        Matcher matcher = STACKOVERFLOW_LINK_PATTERN.matcher(link);
        if (!matcher.find()) {
            return null;
        }
        GetQuestionResponse response = getQuestion(matcher.group(1));
        return new LinkInformation(link, response.lastUpdate());
    }

    private GetQuestionResponse getQuestion(String questionId) {
        return webClient.get().uri("/questions/" + questionId + "?site=stackoverflow").retrieve()
            .bodyToMono(GetQuestionResponse.class).block();
    }
}
