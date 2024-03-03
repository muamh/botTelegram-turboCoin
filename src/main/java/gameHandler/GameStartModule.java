/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameHandler;

import Modules.Game;
import interfaces.BotModule;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.GameModule;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class GameStartModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    GameModule superModule;
    public GameStartModule(GameModule m) {
        super("/start");
        superModule = m;
    }

    @Override
    public PartialBotApiMethod<?> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());
        String game = "";
        try {
            Game.avviaGioco();
            game = "Link per scaricare il gioco: \n";
        } catch (Exception ex) {
            Logger.getLogger(GameStartModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.setText(game + "https://github.com/muamh/personalCandyCrush.git");
        
        this.superModule.activate();
        return m;
    }
}
