package edu.java.scrapper.information_reciever;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.client.github.GithubClient;
import edu.java.client.link.LastUpdateTime;
import edu.java.client.link.LinkInformationProvider;
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

public class GithubClientTest {

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
    @DisplayName("GithubClient#receiveLastUpdateTime test")
    public void receiveLastUpdateTime_shouldReturnCorrectResponse() {
        LinkInformationProvider client = new GithubClient(wireMockServer.baseUrl());

        LastUpdateTime actual = client.receiveLastUpdateTime("https://github.com/gavrilovds/link-tracker");

        assertThat(actual.lastUpdate()).isEqualTo(OffsetDateTime.parse("2024-02-01T08:45:43Z"));
    }
}
