package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StartCommandExecutorTest {

    private static final String WELCOME_MESSAGE = """
        <b>Привет! 😊</b>
        Это бот для отслеживания изменений на GitHub и StackOverFlow. 🚀
        Справка по командам - /help""";

    @Test
    @DisplayName("StartCommandExecutor#execute test")
    public void execute_shouldReturnCorrectMessage() {
        StartCommandExecutor commandExecutor = new StartCommandExecutor();

        SendMessage actual = commandExecutor.execute("/start", 1);

        Assertions.assertThat(actual.getParameters().get("text")).isEqualTo(WELCOME_MESSAGE);
    }
}
