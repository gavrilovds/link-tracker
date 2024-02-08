package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.java.bot.command.Command.START;
import static edu.java.bot.util.MessagesUtils.WELCOME_MESSAGE;

public class StartCommandExecutorTest {

    @Test
    @DisplayName("StartCommandExecutor#execute test")
    public void execute_shouldReturnCorrectMessage() {
        StartCommandExecutor commandExecutor = new StartCommandExecutor();

        SendMessage actual = commandExecutor.execute(START.getCommandName(), 1);

        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(WELCOME_MESSAGE);
    }
}
