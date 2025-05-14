package ru.igap.projects.business.bot.application.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackHandler {
    void handle(Update update);
}
