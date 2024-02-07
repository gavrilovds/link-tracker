package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.dto.Link;
import edu.java.bot.keyboard_builder.KeyboardBuilder;
import edu.java.bot.service.LinkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.command.Command.LIST;

@Log4j2
@RequiredArgsConstructor
public class ListCommandExecutor extends CommandExecutor {

    private final LinkService linkService;

    @Override
    protected SendMessage execute(String command, long chatId) {
        if (!command.equals(LIST.getCommandName())) {
            return executeNext(command, chatId);
        }
        log.info("Command /list has executed");
        return buildMessage(chatId);
    }

    private SendMessage buildMessage(long chatId) {
        List<Link> links = linkService.getAllTrackedLinks(chatId);
        if (links.isEmpty()) {
            return new SendMessage(chatId, "Отслеживаемых ссылок нет 😥. Добавьте ссылку при помощи команды /track");
        }
        Keyboard keyboard = KeyboardBuilder.buildUrlKeyboard(links);
        return new SendMessage(chatId, "Отслеживаемые ссылки: ").replyMarkup(keyboard);
    }
}
