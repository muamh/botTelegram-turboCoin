/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HackHandler;

import Modules.GoldenHack;
import tempMailHandler.*;
import com.mailslurp.clients.ApiException;
import meteoHandler.*;
import config.BotConfiguration;
import interfaces.BotModule;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.GoldenModule;
import modulesHandler.TempMailModule;
import org.json.JSONException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class HackHackingModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    GoldenModule superModule;
    public HackHackingModule(GoldenModule m) {
        super("/start");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        try {
            m.setText("risposta: " + GoldenHack.sendPrenotation());
        } catch (JSONException ex) {
            Logger.getLogger(HackHackingModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HackHackingModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
