package ru.igap.projects.business.bot.utils;

import lombok.experimental.UtilityClass;
import ru.igap.projects.business.bot.application.telegram.callback.CallbackType;

import java.util.LinkedHashMap;
import java.util.Map;

@UtilityClass
public class BdMessage {
    public Map<Long, CallbackType> bd = new LinkedHashMap<>();
    public void addToBd(long chatId, CallbackType type){
        bd.put(chatId,type);
    }
    public CallbackType getFromBd(long chatId){
        return bd.remove(chatId);
    }
}
