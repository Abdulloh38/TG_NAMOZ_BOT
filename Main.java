package com.myBot;

import com.myBot.service.BaseInterface;
import com.myBot.service.Ertangi;
import com.myBot.service.Bugingi;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<BaseInterface> baseInterfaces = new ArrayList<>(10);
    public static void main(String[] args) {
        baseInterfaces.add(new Bugingi());
        baseInterfaces.add(new Ertangi());
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new BotAppMine(baseInterfaces));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
