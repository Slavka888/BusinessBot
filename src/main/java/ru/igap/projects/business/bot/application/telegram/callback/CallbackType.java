package ru.igap.projects.business.bot.application.telegram.callback;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CallbackType {
    INFO("\uD83D\uDCCCИнформация о компании"),
    MESSAGE("\uD83D\uDCE9Оставить сообщение"),
    CHAT("\uD83D\uDDE3\uFE0FПерейти в чат"),
    DATA("\uD83D\uDCDDОставить данные");
    String buttonText;
}
