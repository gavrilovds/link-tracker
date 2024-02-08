package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.LinkService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static edu.java.bot.util.MessagesUtils.LINK_IS_TRACKED;

@ExtendWith(MockitoExtension.class)
public class TrackCommandExecutorTest {

    @Mock
    private LinkService linkService;
    @InjectMocks
    private TrackCommandExecutor commandExecutor;

    @Test
    public void execute_shouldReturnCorrectMessage_whenCommandIsCorrect() {
        long chatId = 1;
        String command = "/track test.com";

        SendMessage actual = commandExecutor.execute(command, chatId);

        Assertions.assertThat(actual.getParameters().get("text"))
            .isEqualTo(LINK_IS_TRACKED.formatted(command.split(" ")[1]));
        Mockito.verify(linkService).trackLink(chatId, command.split(" ")[1]);
    }
}
