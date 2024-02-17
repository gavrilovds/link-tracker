package edu.java.link_type_resolver;

import java.util.regex.Pattern;

public class StackOverflowLinkResolver extends LinkTypeResolver {

    private static final Pattern STACKOVERFLOW_LINK_PATTERN =
        Pattern.compile("https://stackoverflow\\.com/questions/(\\d+).*");

    @Override
    protected LinkType resolve(String link) {
        if (!STACKOVERFLOW_LINK_PATTERN.matcher(link).find()) {
            return resolveNext(link);
        }
        return LinkType.STACKOVERFLOW;
    }
}
