package edu.java.scrapper.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.client.dto.stackoverflow.GetQuestionResponse;
import edu.java.client.stackoverflow.StackOverflowClient;
import edu.java.client.stackoverflow.StackOverflowClientImpl;
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

public class StackOverflowClientTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        String url = "/questions/13133695";
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
    @DisplayName("GithubClient#getQuestion test")
    public void getQuestion_shouldReturnCorrectResponse() {
        StackOverflowClient stackOverflowClient = new StackOverflowClientImpl(wireMockServer.baseUrl());

        GetQuestionResponse actual = stackOverflowClient.getQuestion(13133695);

        assertThat(actual)
            .extracting(GetQuestionResponse::questionTitle, GetQuestionResponse::lastUpdate)
            .containsExactly(
                "IncompatibleClassChangeError with Eclipse Jetty",
                OffsetDateTime.ofInstant(
                    Instant.ofEpochSecond(1352102450),
                    ZoneOffset.UTC
                )
            );
    }
}
