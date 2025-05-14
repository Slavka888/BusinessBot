package ru.igap.projects.business.bot.utils;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.igap.projects.business.bot.application.telegram.callback.CallbackType;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class KeyboardUtils {
    public static InlineKeyboardMarkup buildInlineKeyboard(){
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text(CallbackType.INFO.getButtonText()).callbackData(CallbackType.INFO.name()).build());
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text(CallbackType.MESSAGE.getButtonText()).callbackData(CallbackType.MESSAGE.name()).build());
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text(CallbackType.DATA.getButtonText()).callbackData(CallbackType.DATA.name()).build());
        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text(CallbackType.CHAT.getButtonText()).callbackData(CallbackType.CHAT.name()).build());

        return InlineKeyboardMarkup.builder().keyboard(List.of(row1,row2,row3,row4)).build();
    }
}
