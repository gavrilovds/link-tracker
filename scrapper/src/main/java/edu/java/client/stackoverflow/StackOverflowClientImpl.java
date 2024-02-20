package edu.java.client.stackoverflow;

import edu.java.client.dto.stackoverflow.GetQuestionResponse;
import edu.java.client.link_information.LastUpdateTime;
import edu.java.client.link_information.LinkInformationReceiver;
import edu.java.link_type_resolver.LinkType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowClientImpl implements StackOverflowClient, LinkInformationReceiver {

    private static final String BASE_URL = "https://api.stackexchange.com/2.3/";
    private static final Pattern STACKOVERFLOW_LINK_PATTERN =
        Pattern.compile("https://stackoverflow.com/questions/(\\d+).*");

    private final WebClient webClient;

    public StackOverflowClientImpl() {
        this(BASE_URL);
    }

    public StackOverflowClientImpl(String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
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
        GetQuestionResponse response = getQuestion(matcher.group(1));
        return new LastUpdateTime(response.lastUpdate());
    }

    @Override
    public GetQuestionResponse getQuestion(String questionId) {
        return webClient.get().uri("/questions/" + questionId + "?site=stackoverflow").retrieve()
            .bodyToMono(GetQuestionResponse.class).block();
    }
}
