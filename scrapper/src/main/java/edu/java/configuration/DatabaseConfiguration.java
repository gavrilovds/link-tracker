package edu.java.configuration;

import edu.java.link_type_resolver.LinkTypeResolver;
import edu.java.repository.chat_link.JdbcChatLinkRepository;
import edu.java.repository.link.JdbcLinkRepository;
import edu.java.repository.link.JpaLinkRepository;
import edu.java.service.link.LinkService;
import edu.java.service.link.LinkServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class DatabaseConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
    public LinkService jdbcLinkService(
        JdbcLinkRepository jdbcLinkRepository,
        JdbcChatLinkRepository jdbcChatLinkRepository,
        LinkTypeResolver linkTypeResolver
    ) {
        return new LinkServiceImpl(jdbcLinkRepository, jdbcChatLinkRepository, linkTypeResolver);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
    public LinkService jpaLinkService(
    )
}
