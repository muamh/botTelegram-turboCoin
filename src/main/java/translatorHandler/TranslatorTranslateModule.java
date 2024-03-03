/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package translatorHandler;

import Modules.Traduttore;
import config.BotConfiguration;
import interfaces.BotModule;
import modulesHandler.TranslatorModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class TranslatorTranslateModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    TranslatorModule superModule;
    public TranslatorTranslateModule(TranslatorModule m) {
        super("/traduci");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        m.setText(Traduttore.getTraduzione(update.getMessage().getText().substring(1)));
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
