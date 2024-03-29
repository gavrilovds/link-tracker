package edu.java.bot.util;

import lombok.experimental.UtilityClass;
import static edu.java.bot.command.Command.HELP;
import static edu.java.bot.command.Command.LIST;
import static edu.java.bot.command.Command.TRACK;
import static edu.java.bot.command.Command.UNTRACK;

@UtilityClass
public class MessagesUtils {

    public static final String LINK_HAS_BEEN_UNTRACKED = "Ссылка больше не отслеживается";
    public static final String CHOOSE_LINK_TO_UNTRACK = "Выберите ссылку, у которой нужно прекратить отслеживание: ";
    public static final String NO_TRACKED_LINKS =
        "Отслеживаемых ссылок нет 😥. Добавьте ссылку при помощи команды /track";
    public static final String WELCOME_MESSAGE = """
        <b>Привет! 😊</b>
        Это бот для отслеживания изменений на GitHub и StackOverFlow. 🚀
        Справка по командам - /help""";
    public static final String TRACKED_LINKS = "Отслеживаемые ссылки: ";
    public static final String HELP_MESSAGE = """
        <b>Доступные команды:</b>
        %s - %s
        %s - %s
        %s - %s
        %s - %s""".formatted(TRACK.getName(),
        TRACK.getDescription().toLowerCase(),
        UNTRACK.getName(),
        UNTRACK.getDescription().toLowerCase(),
        LIST.getName(), LIST.getDescription().toLowerCase(),
        HELP.getName(), HELP.getDescription().toLowerCase()
    );
    public static final String ERROR_MESSAGE = """
        <b>Ошибка:</b> Команда не существует. ❌
        Пожалуйста, воспользуйтесь командой /help для получения списка доступных команд.
        """;
    public static final String ONLY_TEXT_TO_SEND = "Для отправки доступен только текст!";
    public static final String TRACK_EXAMPLE = "Отправьте команду в формате: /track <ссылка>";
    public static final String LINK_IS_TRACKED = "Ссылка %s теперь отслеживается ✅";
    public static final String HTTPS_PREFIX = "https://";
    public static final String HTTP_PREFIX = "http://";
    public static final String LINK_SHOULD_STARTS_WITH_HTTP =
        "Ссылка должна начинаться с " + HTTPS_PREFIX + " или " + HTTP_PREFIX + "❗";
    public static final String CHAT_DOESNT_EXIST = "Чат с id = %d не зарегистрирован";
    public static final String CHAT_ALREADY_EXIST = "Чат уже зарегистрирован";
    public static final String TRACK_ERROR = "Ошибка при попытке добавить ссылку в отслеживаемые";
    public static final String UNTRACK_ERROR = "Ошибка при попытке удалить ссылку из отслеживаемых";
}
