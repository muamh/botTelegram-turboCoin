/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mp3Handler;

import translatorHandler.*;
import config.BotConfiguration;
import interfaces.BotModule;
import modulesHandler.Mp3Module;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class Mp3HelpModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    Mp3Module superModule;
    public Mp3HelpModule(Mp3Module m) {
        super("/help");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        m.setText(BotConfiguration.MP3_HELP_TEXT);
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
