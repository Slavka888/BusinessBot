package ru.igap.projects.business.bot.application.telegram.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.igap.projects.business.bot.application.telegram.BusinessBot;
import ru.igap.projects.business.bot.utils.KeyboardUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StartCommand implements Command{
    BusinessBot bot;

    @Override
    public void apply(Update update) {
        long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getUserName();
        log.info("[{}] Команда {} от пользователя {} с id = {}", update.getUpdateId(), getType(), userName, userId);

        long chatId = update.getMessage().getChatId();
        var deleteMessage = DeleteMessage.builder().chatId(chatId).messageId(update.getMessage().getMessageId()).build();
        bot.deleteMessage(deleteMessage);
        SendMessage choiceMessage = SendMessage.builder().chatId(chatId).text("Приветствую! Я бот бизнес-помощник. Выберите действия, которые вас интересуют:").replyMarkup(KeyboardUtils.buildInlineKeyboard()).build();

        bot.sendMessage(choiceMessage);
    }

    @Override
    public CommandType getType() {
        return CommandType.START;
    }
}
