package ru.igap.projects.business.bot.application.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

public interface MessageHandler {
    void handle(Update update);
}
