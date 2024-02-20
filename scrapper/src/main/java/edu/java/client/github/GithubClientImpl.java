package edu.java.client.github;

import edu.java.client.dto.github.GetRepoResponse;
import edu.java.client.link_information.LastUpdateTime;
import edu.java.client.link_information.LinkInformationReceiver;
import edu.java.link_type_resolver.LinkType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.reactive.function.client.WebClient;

public class GithubClientImpl implements GithubClient, LinkInformationReceiver {

    private static final String BASE_URL = "https://api.github.com/";
    private static final Pattern REPOSITORY_PATTERN = Pattern.compile("https://github.com/(.+)/(.+)");
    private final WebClient webClient;

    public GithubClientImpl() {
        this(BASE_URL);
    }

    public GithubClientImpl(String baseUrl) {
        webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public LinkType getLinkType() {
        return LinkType.GITHUB;
    }

    @Override
    public LastUpdateTime receiveLastUpdateTime(String link) {
        Matcher matcher = REPOSITORY_PATTERN.matcher(link);
        if (!matcher.find()) {
            return null;
        }
        GetRepoResponse response = getRepository(matcher.group(1), matcher.group(2));
        return new LastUpdateTime(response.lastUpdate());
    }

    @Override
    public GetRepoResponse getRepository(String userName, String repoName) {
        return webClient.get().uri("repos/" + userName + "/" + repoName).retrieve().bodyToMono(GetRepoResponse.class)
            .block();
    }
}
