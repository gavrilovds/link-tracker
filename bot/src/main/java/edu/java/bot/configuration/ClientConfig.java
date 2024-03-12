package edu.java.bot.configuration;

import edu.java.bot.client.ScrapperClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Value("${client.scrapper.base-url}")
    private String scrapperBaseUrl;

    @Bean
    public ScrapperClient scrapperClient() {
        if (scrapperBaseUrl.isEmpty()) {
            throw new IllegalStateException("Не указан базовый url");
        }
        return new ScrapperClient(scrapperBaseUrl);
    }
}
