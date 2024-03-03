/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ImageSearchHandler;

import newsHandler.*;
import config.BotConfiguration;
import interfaces.BotModule;
import modulesHandler.ImageSearchModule;
import modulesHandler.NewsModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class ImageCloseModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ImageSearchModule superModule;
    public ImageCloseModule(ImageSearchModule m) {
        super("/close");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        m.setText("Comunicazione con il bot img interrotta");
        m.enableMarkdown(true);
        this.superModule.deactivate();
        return m;
    }
}
