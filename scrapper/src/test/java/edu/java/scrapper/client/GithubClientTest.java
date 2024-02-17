package edu.java.scrapper.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.client.dto.github.GetRepoResponse;
import edu.java.client.github.GithubClient;
import edu.java.client.github.GithubClientImpl;
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
    @DisplayName("GithubClient#getRepository test")
    public void getRepository_shouldReturnCorrectResponse() {
        GithubClient client = new GithubClientImpl(wireMockServer.baseUrl());

        GetRepoResponse actual = client.getRepository("gavrilovds", "link-tracker");

        assertThat(actual)
            .extracting(GetRepoResponse::repoFullName, GetRepoResponse::lastUpdate)
            .containsExactly(
                "gavrilovds/link-tracker",
                OffsetDateTime.parse("2024-02-01T08:45:43Z")
            );
    }
}
