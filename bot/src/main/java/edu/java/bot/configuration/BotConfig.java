package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.bot.command.CommandChain;
import edu.java.bot.update_resolver.CallbackUpdateResolver;
import edu.java.bot.update_resolver.MessageUpdateResolver;
import edu.java.bot.update_resolver.UpdateResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static edu.java.bot.command.Command.HELP;
import static edu.java.bot.command.Command.LIST;
import static edu.java.bot.command.Command.TRACK;
import static edu.java.bot.command.Command.UNTRACK;

@Configuration
public class BotConfig {

    @Bean
    public UpdateResolver updateResolver(ScrapperClient scrapperClient, CommandChain commandChain) {
        return UpdateResolver.link(
            new MessageUpdateResolver(commandChain),
            new CallbackUpdateResolver(scrapperClient)
        );
    }

    @Bean
    public BotCommand[] commands() {
        return new BotCommand[] {
            new BotCommand(TRACK.getName(), TRACK.getDescription()),
            new BotCommand(UNTRACK.getName(), UNTRACK.getDescription()),
            new BotCommand(LIST.getName(), LIST.getDescription()),
            new BotCommand(HELP.getName(), HELP.getDescription())
        };
    }

    @Bean
    public TelegramBot bot(ApplicationConfig applicationConfig) {
        return new TelegramBot(applicationConfig.telegramToken());
    }
}
