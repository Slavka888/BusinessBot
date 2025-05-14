package ru.igap.projects.business.bot.application.telegram.callback;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.igap.projects.business.bot.application.telegram.BusinessBot;
import ru.igap.projects.business.bot.utils.KeyboardUtils;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InfoCallback implements Callback{
    BusinessBot bot;
    @Override
    public void apply(Update update) {
        long userId = update.getCallbackQuery().getFrom().getId();
        String userName = update.getCallbackQuery().getFrom().getUserName();
        log.info("[{}] Колбэк {} от пользователя {} с id = {}", update.getUpdateId(), getType(), userName, userId);

        long chatId = update.getCallbackQuery().getMessage().getChatId();

        var message = SendMessage.builder().chatId(chatId).replyMarkup(KeyboardUtils.buildInlineKeyboard()).text("""
                Ф.И.О Директора: Иванов И.И.
                Телефон: +7 XXX XXX XX XX
                Адрес: Г. N, ул. M, строение K
                Адрес электронной почты: example@mail.ry
                """).build();
        bot.sendMessage(message);
    }

    @Override
    public CallbackType getType() {
        return CallbackType.INFO;
    }
}
