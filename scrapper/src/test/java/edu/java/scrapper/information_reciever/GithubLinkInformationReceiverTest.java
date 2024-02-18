package edu.java.scrapper.information_reciever;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.client.link_information.GithubLinkInformationReceiver;
import edu.java.client.link_information.LinkInformation;
import edu.java.client.link_information.LinkInformationReceiver;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class GithubLinkInformationReceiverTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        String url = "/repos/gavrilovds/link-tracker";
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.stubFor(get(urlEqualTo(url))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("""
                    {
                        "full_name": "gavrilovds/link-tracker",
                        "updated_at": "2024-02-01T08:45:43Z"
                    }
                    """)));
        wireMockServer.start();
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("GithubLinkInformationReceiver#receiveLinkInformation test")
    public void getRepository_shouldReturnCorrectResponse() {
        LinkInformationReceiver client = new GithubLinkInformationReceiver(wireMockServer.baseUrl());

        LinkInformation actual = client.receiveLinkInformation("https://github.com/gavrilovds/link-tracker");

        assertThat(actual)
            .extracting(LinkInformation::link, LinkInformation::lastUpdate)
            .containsExactly(
                "https://github.com/gavrilovds/link-tracker",
                OffsetDateTime.parse("2024-02-01T08:45:43Z")
            );
    }
}
