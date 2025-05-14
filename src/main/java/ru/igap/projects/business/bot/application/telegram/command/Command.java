package ru.igap.projects.business.bot.application.telegram.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void apply(Update update);
    CommandType getType();
}
