package edu.java.client.link_information;

import edu.java.client.dto.github.GetRepoResponse;
import edu.java.link_type_resolver.LinkType;
import java.net.URI;
import org.springframework.web.reactive.function.client.WebClient;

public class GithubLinkInformationReceiver implements LinkInformationReceiver {

    private static final String BASE_URL = "https://api.github.com/";
    private final WebClient webClient;

    public GithubLinkInformationReceiver() {
        this(BASE_URL);
    }

    public GithubLinkInformationReceiver(String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public LinkType getLinkType() {
        return LinkType.GITHUB;
    }

    @Override
    public LinkInformation receiveLinkInformation(String link) {
        URI linkURI = URI.create(link);
        GetRepoResponse response = getRepository(linkURI.getPath());
        return new LinkInformation(link, response.lastUpdate());
    }

    private GetRepoResponse getRepository(String path) {
        return webClient.get().uri("repos/" + path).retrieve().bodyToMono(GetRepoResponse.class)
            .block();
    }
}
