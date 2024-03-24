package edu.java.configuration;

import edu.java.client.bot.BotClient;
import edu.java.client.github.GithubClient;
import edu.java.client.link.LinkInformationProvider;
import edu.java.client.stackoverflow.StackOverflowClient;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Value("${client.stackoverflow.base-url}")
    private String stackOverflowBaseUrl;
    @Value("${client.github.base-url}")
    private String githubBaseUrl;
    @Value("${client.bot.base-url}")
    private String botBaseUrl;
    @Value("${client.github.authorization-token}")
    private String getGithubAuthorizationToken;

    @Bean
    public LinkInformationProvider stackOverflowClient() {
        if (stackOverflowBaseUrl.isEmpty()) {
            throw new IllegalStateException("Не указан базовый stackoverflow url");
        }
        return new StackOverflowClient(stackOverflowBaseUrl);
    }

    @Bean
    public LinkInformationProvider githubClient() {
        if (githubBaseUrl.isEmpty()) {
            throw new IllegalStateException("Не указан базовый github url");
        }
        Map<String, String> headers = new HashMap<>();
        if (!getGithubAuthorizationToken.isEmpty()) {
            headers.put("Authorization", "Bearer " + getGithubAuthorizationToken);
        }
        return new GithubClient(githubBaseUrl, headers);
    }

    @Bean
    public BotClient botClient() {
        if (botBaseUrl.isEmpty()) {
            throw new IllegalStateException("Не указан базовый bot url");
        }
        return new BotClient(botBaseUrl);
    }
}
