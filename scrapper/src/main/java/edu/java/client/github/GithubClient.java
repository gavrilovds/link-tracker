package edu.java.client.github;

import edu.java.client.AbstractWebClient;
import edu.java.client.dto.github.GetRepoResponse;
import edu.java.client.link.LastUpdateTime;
import edu.java.client.link.LinkInformationProvider;
import edu.java.link_type_resolver.LinkType;
import edu.java.service.GithubService;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubClient extends AbstractWebClient<GithubService> implements LinkInformationProvider {

    private static final Pattern REPOSITORY_PATTERN = Pattern.compile("https://github.com/(.+)/(.+)");
    private final Map<String, String> headers;

    public GithubClient(String baseUrl, Map<String, String> headers) {
        super(baseUrl);
        this.headers = headers;
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
        GetRepoResponse response = service.getRepository(matcher.group(1), matcher.group(2), headers);
        return new LastUpdateTime(response.lastUpdate());
    }
}
