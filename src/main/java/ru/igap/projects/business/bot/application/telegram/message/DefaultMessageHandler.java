package ru.igap.projects.business.bot.application.telegram.message;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.igap.projects.business.bot.application.telegram.BusinessBot;
import ru.igap.projects.business.bot.application.telegram.MessageHandler;
import ru.igap.projects.business.bot.utils.BdMessage;
import ru.igap.projects.business.bot.utils.KeyboardUtils;
import ru.igap.projects.business.bot.utils.MessageHandlerUtils;

import static ru.igap.projects.business.bot.application.telegram.callback.CallbackType.DATA;
import static ru.igap.projects.business.bot.application.telegram.callback.CallbackType.MESSAGE;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultMessageHandler implements MessageHandler {
    BusinessBot bot;
    @Override
    public void handle(Update update) {
        var callback = BdMessage.getFromBd(update.getMessage().getChatId());
        System.out.println(BdMessage.bd.isEmpty());
        if (callback == DATA && MessageHandlerUtils.contactsCorrectCheck(update.getMessage().getText())){
            long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getUserName();
            log.info("[{}] Сообщение {} от пользователя {} с id = {}", update.getUpdateId(), update.getMessage().getText(), userName, userId);

            long chatId = update.getMessage().getChatId();
            String txt = update.getMessage().getText();
            var message = SendMessage.builder().chatId(chatId).replyMarkup(KeyboardUtils.buildInlineKeyboard()).text("Ваши данные: " + MessageHandlerUtils.formatContact(txt) + " успешно сохранены!").build();
            bot.sendMessage(message);
        }
        else if (callback == MESSAGE){
            long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getUserName();
            log.info("[{}] Сообщение {} от пользователя {} с id = {}", update.getUpdateId(), update.getMessage().getText(), userName, userId);

            long chatId = update.getMessage().getChatId();
            String txt = update.getMessage().getText();
            var message = SendMessage.builder().chatId(chatId).replyMarkup(KeyboardUtils.buildInlineKeyboard()).text("Ваш запрос: " +txt + " \nуспешно отправлен").build();
            bot.sendMessage(message);
        }
        else {
            long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getUserName();
            log.info("[{}] Сообщение {} от пользователя {} с id = {} было некорректным", update.getUpdateId(), update.getMessage().getText(), userName, userId);

            long chatId = update.getMessage().getChatId();
            String txt = update.getMessage().getText();
            var message = SendMessage.builder().chatId(chatId).replyMarkup(KeyboardUtils.buildInlineKeyboard()).text("Проверьте корректность запроса!").build();
            bot.sendMessage(message);
        }
    }
}
