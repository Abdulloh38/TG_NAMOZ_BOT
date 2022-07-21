package com.myBot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.LinkedList;
import java.util.List;

public class Util1 {
    public static SendMessage send(Update update){
        SendMessage sendMessage = new SendMessage();
        Message message = update.getMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Assalomu Alaykum " + message.getFrom().getFirstName() + "\uD83D\uDE04.\nBotimizga xush kelibsiz.\nBu bot orqali Toshkentdagi namoz vaqtlarini aniqlashingiz mumkin !");
        sendMessage.enableMarkdown(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new LinkedList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("\uD83D\uDCFF⏳ Bugungi namoz vaqtlari");
        firstRow.add("\uD83D\uDD0E Ertangi namoz vaqtlari");
        keyboardRows.add(firstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}
