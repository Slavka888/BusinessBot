package ru.igap.projects.business.bot.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageHandlerUtils {
    public String formatContact(String txt){
        return txt.trim().toUpperCase();
    }

    public boolean contactsCorrectCheck(String txt){
        String contactArr [] = txt.split(" ");
        if (contactArr.length != 4){return false;}
        boolean numTest = (contactArr[3].startsWith("+7"));
        boolean numLengthTest = (contactArr[3].length() == 12);

        return numLengthTest && numTest;
    }
}