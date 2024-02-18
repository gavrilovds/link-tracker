package edu.java.configuration;

import edu.java.client.link_information.GithubLinkInformationReceiver;
import edu.java.client.link_information.LinkInformationReceiver;
import edu.java.client.link_information.StackOverflowLinkInformationReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public LinkInformationReceiver stackOverflowClient() {
        return new StackOverflowLinkInformationReceiver();
    }

    @Bean
    public LinkInformationReceiver githubClient() {
        return new GithubLinkInformationReceiver();
    }
}
