package edu.java.configuration;

import edu.java.client.github.GithubClient;
import edu.java.client.link.LinkInformationProvider;
import edu.java.client.stackoverflow.StackOverflowClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Value("${client.stackoverflow.base-url}")
    private String stackOverflowBaseUrl;
    @Value("${client.github.base-url}")
    private String githubBaseUrl;

    @Bean
    public LinkInformationProvider stackOverflowClient() {
        if (stackOverflowBaseUrl.isEmpty()) {
            return new StackOverflowClient();
        }
        return new StackOverflowClient(stackOverflowBaseUrl);
    }

    @Bean
    public LinkInformationProvider githubClient() {
        if (githubBaseUrl.isEmpty()) {
            return new GithubClient();
        }
        return new GithubClient(githubBaseUrl);
    }
}