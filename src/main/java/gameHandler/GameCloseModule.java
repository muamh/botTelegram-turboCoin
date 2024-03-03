/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameHandler;

import lyricsHandler.*;
import tempMailHandler.*;
import meteoHandler.*;
import config.BotConfiguration;
import interfaces.BotModule;
import modulesHandler.GameModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class GameCloseModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    GameModule superModule;
    public GameCloseModule(GameModule m) {
        super("/close");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        m.setText("Il bot non risponde più ai comandi di /game");
        m.enableMarkdown(true);
        this.superModule.deactivate();
        return m;
    }
}
