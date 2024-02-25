package edu.java.bot.update_resolver;

import com.pengrad.telegrambot.model.Update;
import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.bot.command.CommandChain;
import edu.java.bot.service.LinkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdateResolverTest {

    private final UpdateResolver updateResolver =
        UpdateResolver.link(
            new MessageUpdateResolver(new CommandChain()),
            new CallbackUpdateResolver(new ScrapperClient())
        );

    @Test
    @DisplayName("UpdateResolver#resolve with invalid Update test")
    public void test_shouldThrowException_whenUpdateIsInvalid() {
        Assertions.assertThatThrownBy(() -> updateResolver.resolve(new Update())).isInstanceOf(RuntimeException.class);
    }
}
