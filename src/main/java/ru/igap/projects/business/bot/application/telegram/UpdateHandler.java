package ru.igap.projects.business.bot.application.telegram;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateHandler {
    CommandHandler commandHandler;
    CallbackHandler callbackHandler;
    MessageHandler messageHandler;

    void handle(Update update){
        if(update.hasMessage()){
            log.debug("[{}] В update поступило сообщение", update.getUpdateId());
            if(update.getMessage().hasText()){
                log.debug("[{}] В сообщении поступил текст", update.getUpdateId());
                if(update.getMessage().getText().startsWith("/")){
                    commandHandler.handle(update);
                    return;
                }
                else {
                    log.debug("[{}] В update поступило сообщение после колбэка", update.getUpdateId());
                    messageHandler.handle(update);
                    return;
                }
            }
        }
        else if (update.hasCallbackQuery()){
            log.debug("[{}] В update поступил колбэк", update.getUpdateId());
            callbackHandler.handle(update);
            return;
        }
        log.warn("[{}] В update поступила информация, но ее не получилось обработать!", update.getUpdateId());
    }
}
