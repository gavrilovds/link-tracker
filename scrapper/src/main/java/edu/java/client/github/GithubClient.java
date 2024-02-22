package edu.java.client.github;

import edu.java.client.AbstractWebClient;
import edu.java.client.dto.github.GetRepoResponse;
import edu.java.client.link_information.LastUpdateTime;
import edu.java.link_type_resolver.LinkType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubClient extends AbstractWebClient {

    private static final String BASE_URL = "https://api.github.com/";
    private static final Pattern REPOSITORY_PATTERN = Pattern.compile("https://github.com/(.+)/(.+)");
    private final GithubService githubService;

    public GithubClient() {
        this(BASE_URL);
    }

    public GithubClient(String baseUrl) {
        super(baseUrl);
        githubService = factory.createClient(GithubService.class);
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
        GetRepoResponse response = githubService.getRepository(matcher.group(1), matcher.group(2));
        return new LastUpdateTime(response.lastUpdate());
    }
}
