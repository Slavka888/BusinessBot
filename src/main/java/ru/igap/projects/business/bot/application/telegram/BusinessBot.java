package ru.igap.projects.business.bot.application.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.igap.projects.business.bot.configuration.BusinessBotProperties;

@Component
public class BusinessBot extends TelegramLongPollingBot {
    UpdateHandler updateHandler;
    BusinessBotProperties properties;

    @Autowired
    public BusinessBot(@Lazy UpdateHandler updateHandler, BusinessBotProperties properties){
        super(new DefaultBotOptions(), properties.token());
        this.updateHandler = updateHandler;
        this.properties=properties;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handle(update);
    }

    @Override
    public String getBotUsername() {
        return properties.name();
    }

    public void sendMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteMessage(DeleteMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
