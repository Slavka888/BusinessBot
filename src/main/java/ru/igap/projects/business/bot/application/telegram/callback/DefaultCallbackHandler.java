package ru.igap.projects.business.bot.application.telegram.callback;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.igap.projects.business.bot.application.telegram.CallbackHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultCallbackHandler implements CallbackHandler {
    Map<CallbackType, Callback> callbacksMap;

    @Autowired
    public DefaultCallbackHandler(List<Callback> callbacks){
        this.callbacksMap = new HashMap<>();
        callbacks.forEach(callback ->callbacksMap.put(callback.getType(), callback));
    }
    @Override
    public void handle(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        log.debug("[{}] Получен колбэк {}", update.getUpdateId(), callbackData);
        CallbackType type = CallbackType.valueOf(callbackData);
        var callback = callbacksMap.get(type);
        if (callback!= null){
            callback.apply(update);
        }
        else{
            long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getUserName();
            log.error("[{}] Неизвестный колбэк {} от пользователя {} с id = {}", update.getUpdateId(), callbackData, userName, userId);
        }
    }
}
