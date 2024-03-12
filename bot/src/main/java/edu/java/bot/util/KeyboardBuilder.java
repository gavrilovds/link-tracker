package edu.java.bot.util;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import edu.java.bot.dto.LinkResponse;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class KeyboardBuilder {

    public static Keyboard buildUrlKeyboard(List<LinkResponse> links) {
        return new InlineKeyboardMarkup(
            links.stream()
                .map(link -> new InlineKeyboardButton(link.link()).url(link.link()))
                .map(button -> new InlineKeyboardButton[] {button})
                .toArray(InlineKeyboardButton[][]::new)
        );
    }

    public static Keyboard buildCallbackKeyboard(List<LinkResponse> links) {
        return new InlineKeyboardMarkup(
            links.stream()
                .map(link -> new InlineKeyboardButton(link.link()).callbackData("/untrack:" + link.linkId()))
                .map(button -> new InlineKeyboardButton[] {button})
                .toArray(InlineKeyboardButton[][]::new)
        );
    }
}
