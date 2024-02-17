package edu.java.client.stackoverflow;

import edu.java.client.dto.stackoverflow.GetQuestionResponse;
import edu.java.client.link_information.LinkInformation;
import edu.java.client.link_information.LinkInformationReceiver;
import edu.java.link_type_resolver.LinkType;
import java.net.URI;
import org.springframework.web.reactive.function.client.WebClient;

public class StackOverflowClientImpl implements StackOverflowClient, LinkInformationReceiver {

    private static final String BASE_URL = "https://api.stackexchange.com/2.3/";

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
    public LinkInformation receiveLinkInformation(String link) {
        URI linkURI = URI.create(link);
        GetQuestionResponse response = getQuestion(linkURI.getPath());
        return new LinkInformation(link, response.lastUpdate());
    }

    @Override
    public GetQuestionResponse getQuestion(String path) {
        return webClient.get().uri(path).retrieve().bodyToMono(GetQuestionResponse.class).block();
    }
}
