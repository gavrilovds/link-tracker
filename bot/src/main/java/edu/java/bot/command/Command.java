package edu.java.bot.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Command {
    START("/start", "Запуск бота"),
    TRACK("/track", "Начать отслеживание обновлений ссылки 📌"),
    UNTRACK("/untrack", "Прекратить отслеживание обновлений ссылки 🚫"),
    LIST("/list", "Список отслеживаемых ссылок 📋"),
    HELP("/help", "Справка по командам ❓");

    private final String name;
    private final String description;
}
