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
    public LinkService linkService() {
        return new LinkService();
    }

    @Bean
    public UpdateResolver updateResolver() {
        return UpdateResolver.link(
            new MessageUpdateResolver(commandChain()),
            new CallbackUpdateResolver(linkService())
        );
    }

    @Bean
    public CommandChain commandChain() {
        return new CommandChain(
            CommandExecutor.link(
                new StartCommandExecutor(),
                new HelpCommandExecutor(),
                new ListCommandExecutor(linkService()),
                new TrackCommandExecutor(linkService()),
                new UntrackCommandExecutor(linkService())
            ));
    }

    @Bean
    public BotCommand[] commands() {
        return new BotCommand[] {
            new BotCommand(TRACK.getCommandName(), TRACK.getCommandDescription()),
            new BotCommand(UNTRACK.getCommandName(), UNTRACK.getCommandDescription()),
            new BotCommand(LIST.getCommandName(), LIST.getCommandDescription()),
            new BotCommand(HELP.getCommandName(), HELP.getCommandDescription())
        };
    }

    @Bean
    public String telegramToken(ApplicationConfig applicationConfig) {
        return applicationConfig.telegramToken();
    }
}
