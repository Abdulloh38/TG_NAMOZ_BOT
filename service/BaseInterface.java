package com.myBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BaseInterface {
    SendMessage onUpdateReceived(Update update);
    TYPES type();
}
