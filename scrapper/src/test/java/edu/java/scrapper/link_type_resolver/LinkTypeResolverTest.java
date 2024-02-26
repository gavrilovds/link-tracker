package edu.java.scrapper.link_type_resolver;

import edu.java.link_type_resolver.GithubLinkResolver;
import edu.java.link_type_resolver.LinkType;
import edu.java.link_type_resolver.LinkTypeResolver;
import edu.java.link_type_resolver.StackOverflowLinkResolver;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class LinkTypeResolverTest {

    private static Stream<Arguments> inputs() {
        return Stream.of(
            Arguments.of("https://github.com/gavrilovds/link-tracker", LinkType.GITHUB),
            Arguments.of("https://github.com/gavrilovds/link-tracker/pull/2/files", LinkType.GITHUB),
            Arguments.of(
                "https://stackoverflow.com/questions/13133695/incompatibleclasschangeerror-with-eclipse-jetty",
                LinkType.STACKOVERFLOW
            ),
            Arguments.of("https://stackoverflow.com/questions/13133695", LinkType.STACKOVERFLOW),
            Arguments.of("https://vk.com", LinkType.UNKNOWN)
        );
    }

    @ParameterizedTest()
    @MethodSource("inputs")
    @DisplayName("LinkTypeResolver#resolve test")
    public void resolve_shouldReturnCorrectLinkType(String link, LinkType expected) {
        LinkTypeResolver resolver = LinkTypeResolver.link(new GithubLinkResolver(), new StackOverflowLinkResolver());

        LinkType actual = resolver.resolve(link);

        assertThat(actual).isEqualTo(expected);
    }
}
