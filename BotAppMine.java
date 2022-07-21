package com.myBot;

import com.myBot.service.BaseInterface;
import com.myBot.service.TYPES;
import com.myBot.util.Util1;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

public class BotAppMine extends TelegramLongPollingBot {
    private final List<BaseInterface> baseInterfaces;
    public BotAppMine(List<BaseInterface> baseInterfaces) {
        this.baseInterfaces = baseInterfaces;
    }

    @Override
    public String getBotUsername() {
        return "@namazTimeTashKentbot";
    }

    @Override
    public String getBotToken() {
        return "5561683084:AAFgnoiMSJQgvfMD0-Kor1BpGgIKn3Bgp5Q";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            if (update.getMessage().hasText()){
                if (update.getMessage().getText().equals("/start")){
                    SendMessage sendMessage = Util1.send(update);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        if(update.getMessage().getText().equals("\uD83D\uDCFF⏳ Bugungi namoz vaqtlari")){
            Optional<BaseInterface> any = baseInterfaces.stream().filter(t -> t.type().equals(TYPES.BUGUNGI)).findAny();
            BaseInterface baseInterface = any.get();
            SendMessage sendMessage = baseInterface.onUpdateReceived(update);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(update.getMessage().getText().equals("\uD83D\uDD0E Ertangi namoz vaqtlari")){
            Optional<BaseInterface> any = baseInterfaces.stream().filter(t -> t.type().equals(TYPES.ERTANGI)).findAny();
            BaseInterface baseInterface = any.get();
            SendMessage sendMessage = baseInterface.onUpdateReceived(update);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        
    }
}
