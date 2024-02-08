package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.LinkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static edu.java.bot.util.MessagesUtils.ERROR_MESSAGE;

public class CommandExecutorTest {

    @Mock
    private LinkService linkService;
    private final CommandExecutor commandExecutor = CommandExecutor.link(
        new StartCommandExecutor(),
        new HelpCommandExecutor(),
        new ListCommandExecutor(linkService),
        new TrackCommandExecutor(linkService),
        new UntrackCommandExecutor(linkService)
    );

    @Test
    @DisplayName("CommandExecutor#execute test")
    public void execute_shouldReturnCorrectMessage_whenCommandIsInvalid() {
        SendMessage actual = commandExecutor.execute("wrokoj", 1);

        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(ERROR_MESSAGE);
    }
}
