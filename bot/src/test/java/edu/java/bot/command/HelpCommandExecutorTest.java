package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.java.bot.command.Command.HELP;
import static edu.java.bot.command.Command.LIST;
import static edu.java.bot.command.Command.TRACK;
import static edu.java.bot.command.Command.UNTRACK;

public class HelpCommandExecutorTest {

    private static final String HELP_MESSAGE = """
        <b>Доступные команды:</b>
        %s - %s
        %s - %s
        %s - %s
        %s - %s""".formatted(TRACK.getCommandName(),
        TRACK.getCommandDescription().toLowerCase(),
        UNTRACK.getCommandName(),
        UNTRACK.getCommandDescription().toLowerCase(),
        LIST.getCommandName(), LIST.getCommandDescription().toLowerCase(),
        HELP.getCommandName(), HELP.getCommandDescription().toLowerCase()
    );

    @Test
    @DisplayName("HelpCommandExecutor#execute test")
    public void execute_shouldReturnCorrectMessage() {
        HelpCommandExecutor testExecutor = new HelpCommandExecutor();

        SendMessage actual = testExecutor.execute("/help", 1);

        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(HELP_MESSAGE);
        Assertions.assertThat(actual.getParameters().get("chat_id")).isEqualTo(1L);
    }
}
