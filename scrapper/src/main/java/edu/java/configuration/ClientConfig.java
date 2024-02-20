package edu.java.configuration;

import edu.java.client.github.GithubClientImpl;
import edu.java.client.link_information.LinkInformationReceiver;
import edu.java.client.stackoverflow.StackOverflowClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public LinkInformationReceiver stackOverflowClient() {
        return new StackOverflowClientImpl();
    }

    @Bean
    public LinkInformationReceiver githubClient() {
        return new GithubClientImpl();
    }
}
