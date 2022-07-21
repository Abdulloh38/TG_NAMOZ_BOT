package com.myBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.LinkedList;

public class Ertangi  implements BaseInterface{
    @Override
    public synchronized SendMessage onUpdateReceived(Update update) {
        try {
            URL url = new URL("http://uzsmart.ru/namoz-vaqtlari/");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int i;
            String str = "";
            while ((i= bufferedReader.read())!=-1){
                str += (char)i;
            }
            int ind = str.indexOf("<h2 style=\"font-size: 24px; color: green\">");
            int indLast = str.indexOf(" <div class=\"title\"><h2>Toshkentdan boshqa shaharlardagi vaqtlar farqi (minut)</h2></div>");
            String substring = str.substring(ind,indLast);
            int first  = substring.indexOf("<table  style=\"font-size: 24px\">");
            int last = substring.indexOf("</table>");
            substring = substring.substring(first,last);
            String[] split = substring.split("<tr class=\"item\">");
            LinkedList<String[]> rows = new LinkedList<>();
            for (String tr :split) {
                String[] strings = tr.split("</tr>");
                rows.add(strings);
            }
            String time = "";
            for (int j = 1; j < rows.size(); j++){
                String[] strings = rows.get(j);
                for(int k = 0; k < strings.length; k++){
                    time += strings[k];
                }

                time = time.replace("<td>","").strip();
                time = time.replace("<b>","").strip();
                time = time.replace("</b>","").strip();
                time = time.replace("</td>","").strip();
                time = time.replace("</tr>","").strip();
            }

            String all = time.replaceAll("\\s*\n\\s*", "");
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            String s = "Sana : "+tomorrow+"\nVaqt mintaqasi  (Asia/Tashkent) :\n\n";
            for (int k = 0; k < all.length(); k++){
                if (all.charAt(k) == 'Q' || all.charAt(k) == 'P' || all.charAt(k) == 'S' || all.charAt(k) == 'X' || all.charAt(k) == 'A'){
                    s += "\n\n";
                }
                s += all.charAt(k);
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText(s);
            return sendMessage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TYPES type() {
        return TYPES.ERTANGI;
    }
}
