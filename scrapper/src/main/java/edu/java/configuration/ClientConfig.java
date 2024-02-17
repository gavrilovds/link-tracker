package edu.java.configuration;

import edu.java.client.github.GithubClient;
import edu.java.client.github.GithubClientImpl;
import edu.java.client.stackoverflow.StackOverflowClient;
import edu.java.client.stackoverflow.StackOverflowClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public StackOverflowClient stackOverflowClient() {
        return new StackOverflowClientImpl();
    }

    @Bean
    public GithubClient githubClient() {
        return new GithubClientImpl();
    }
}
