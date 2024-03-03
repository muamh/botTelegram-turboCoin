/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tempMailHandler;

import Modules.TempMailGenerator;
import com.mailslurp.clients.ApiException;
import meteoHandler.*;
import config.BotConfiguration;
import interfaces.BotModule;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.TempMailModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class TempMailCreateModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    TempMailModule superModule;
    public TempMailCreateModule(TempMailModule m) {
        super("/generate");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        try {
            m.setText("Email Temporanea generata: " + TempMailGenerator.getNewMail());
        } catch (ApiException ex) {
            m.setText("C'è stato un errore durante la generazione dell'email");
        }
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
