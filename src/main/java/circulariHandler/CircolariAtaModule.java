package circulariHandler;

import Modules.Circolari;
import config.BotConfiguration;
import interfaces.BotModule;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CircolariAtaModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    CircolariModule superModule;
    public CircolariAtaModule(CircolariModule m) {
        super("/ataedocenti");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        try {
            m.setText(Circolari.getContentCircolariAta());
        } catch (IOException ex) {
            m.setText("Errore durante il controllo");
        }
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
