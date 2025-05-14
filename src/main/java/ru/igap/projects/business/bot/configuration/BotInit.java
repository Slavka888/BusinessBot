package ru.igap.projects.business.bot.configuration;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.igap.projects.business.bot.application.telegram.BusinessBot;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BotInit {
    BusinessBot bot;

    @Autowired
    public BotInit(BusinessBot bot){
        this.bot = bot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
    }
}
