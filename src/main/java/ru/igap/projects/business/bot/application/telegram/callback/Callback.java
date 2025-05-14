package ru.igap.projects.business.bot.application.telegram.callback;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Callback {
    void apply(Update update);
    CallbackType getType();
}
