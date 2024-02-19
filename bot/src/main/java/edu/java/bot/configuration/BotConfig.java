package edu.java.bot.configuration;

import com.pengrad.telegrambot.model.BotCommand;
import edu.java.bot.command.CommandChain;
import edu.java.bot.command.CommandExecutor;
import edu.java.bot.command.HelpCommandExecutor;
import edu.java.bot.command.ListCommandExecutor;
import edu.java.bot.command.StartCommandExecutor;
import edu.java.bot.command.TrackCommandExecutor;
import edu.java.bot.command.UntrackCommandExecutor;
import edu.java.bot.service.LinkService;
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
    public UpdateResolver updateResolver(LinkService linkService) {
        return UpdateResolver.link(
            new MessageUpdateResolver(commandChain(linkService)),
            new CallbackUpdateResolver(linkService)
        );
    }

    @Bean
    public CommandChain commandChain(LinkService linkService) {
        return new CommandChain(
            CommandExecutor.link(
                new StartCommandExecutor(),
                new HelpCommandExecutor(),
                new ListCommandExecutor(linkService),
                new TrackCommandExecutor(linkService),
                new UntrackCommandExecutor(linkService)
            ));
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
    public String telegramToken(ApplicationConfig applicationConfig) {
        return applicationConfig.telegramToken();
    }
}
