package edu.java.scrapper.information_reciever;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.client.link_information.LinkInformation;
import edu.java.client.link_information.LinkInformationReceiver;
import edu.java.client.link_information.StackOverflowLinkInformationReceiver;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

public class StackOverflowLinkInformationReceiverTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        String url = "/questions/13133695?site=stackoverflow";
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.stubFor(get(urlEqualTo(url))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("""
                    {
                        "title": "IncompatibleClassChangeError with Eclipse Jetty",
                        "last_activity_date": 1352102450
                    }
                    """)));
        wireMockServer.start();
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("StackOverflowLinkInformationReceiver#receiveLinkInformation test")
    public void getQuestion_shouldReturnCorrectResponse() {
        LinkInformationReceiver stackOverflowClient =
            new StackOverflowLinkInformationReceiver(wireMockServer.baseUrl());

        LinkInformation actual =
            stackOverflowClient.receiveLinkInformation("https://stackoverflow.com/questions/13133695");

        assertThat(actual)
            .extracting(LinkInformation::link, LinkInformation::lastUpdate)
            .containsExactly(
                "https://stackoverflow.com/questions/13133695",
                OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1352102450),
                    ZoneOffset.UTC
                )
            );
    }
}
