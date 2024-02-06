package edu.java.bot.command;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.service.LinkService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.command.Command.LIST;

@Log4j2
public class ListCommandExecutor extends CommandExecutor {

    private final LinkService linkService = new LinkService();

    @Override
    protected SendMessage execute(String command, long chatId) {
        if (!command.equals(LIST.getCommandName())) {
            return executeNext(command, chatId);
        }
        log.info("Command /list has executed");
        return buildMessage(chatId);
    }

    private SendMessage buildMessage(long chatId) {
        List<String> links = linkService.getAllTrackedLinks(chatId);
        if (links.isEmpty()) {
            return new SendMessage(chatId, "Отслеживаемых ссылок нет 😥. Добавьте ссылку при помощи команды /track");
        }
        Keyboard keyboard = createKeyboard(links);
        return new SendMessage(chatId, "Отслеживаемые ссылки: ").replyMarkup(keyboard);
    }

    private InlineKeyboardMarkup createKeyboard(List<String> links) {
        List<InlineKeyboardButton[]> keyboardRows = new ArrayList<>();
        for (String link : links) {
            InlineKeyboardButton button = new InlineKeyboardButton(link).url(link);
            InlineKeyboardButton[] row = new InlineKeyboardButton[] {button};
            keyboardRows.add(row);
        }
        InlineKeyboardButton[][] buttonsArray = keyboardRows.toArray(new InlineKeyboardButton[0][0]);
        return new InlineKeyboardMarkup(buttonsArray);
    }
}
