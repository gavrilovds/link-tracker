package edu.java.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.client.scrapper.ScrapperClient;
import edu.java.bot.dto.LinkResponse;
import edu.java.bot.dto.ListLinksResponse;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static edu.java.bot.command.Command.UNTRACK;
import static edu.java.bot.util.MessagesUtils.CHOOSE_LINK_TO_UNTRACK;
import static edu.java.bot.util.MessagesUtils.NO_TRACKED_LINKS;

@ExtendWith(MockitoExtension.class)
public class UntrackCommandExecutorTest {

    @Mock
    private ScrapperClient scrapperClient;
    @InjectMocks
    private UntrackCommandExecutor commandExecutor;

    @Test
    @DisplayName("UntrackCommandExecutor#execute with tracked links test")
    public void execute_shouldReturnCorrectMessage_whenTrackedLinksExist() {
        long chatId = 1;
        Mockito.when(scrapperClient.getAllTrackedLinks(chatId)).thenReturn(new ListLinksResponse(List.of(
            new LinkResponse(
                1,
                "https://github.com/sanyarnd/tinkoff-java-course-2023"
            ),
            new LinkResponse(
                2,
                "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"
            )
        )));

        SendMessage actual = commandExecutor.execute(UNTRACK.getName(), chatId);

        Assertions.assertThat(actual.getParameters().get("text"))
            .isEqualTo(CHOOSE_LINK_TO_UNTRACK);
    }

    @Test
    @DisplayName("UntrackCommandExecutor#execute with no tracked links test")
    public void execute_shouldReturnCorrectMessage_whenNoTrackedLinks() {
        long chatId = 1;
        Mockito.when(scrapperClient.getAllTrackedLinks(chatId))
            .thenReturn(new ListLinksResponse(Collections.emptyList()));

        SendMessage actual = commandExecutor.execute(UNTRACK.getName(), chatId);

        Assertions.assertThat(actual.getParameters().get("text"))
            .isEqualTo(NO_TRACKED_LINKS);
    }
}
