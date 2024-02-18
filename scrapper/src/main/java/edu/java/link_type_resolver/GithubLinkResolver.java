package edu.java.link_type_resolver;

import java.util.regex.Pattern;

public class GithubLinkResolver extends LinkTypeResolver {

    private static final Pattern GITHUB_LINK_PATTERN = Pattern.compile("https://github.com/([^/]+)/([^/]+)");

    @Override
    public LinkType resolve(String link) {
        if (!GITHUB_LINK_PATTERN.matcher(link).find()) {
            return resolveNext(link);
        }
        return LinkType.GITHUB;
    }
}
