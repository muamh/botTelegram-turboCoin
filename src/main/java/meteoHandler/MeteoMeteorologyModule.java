package meteoHandler;

import Modules.Meteo;
import config.BotConfiguration;
import interfaces.BotModule;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.MeteoModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


public class MeteoMeteorologyModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    MeteoModule superModule;
    public MeteoMeteorologyModule(MeteoModule m) {
        super("/lonato");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());
        String[] citta = update.getMessage().getText().split("/");

        try {
            m.setText(Meteo.ottieniInformazioniMeteo(citta[1]));
        } catch (IOException ex) {
            m.setText("Località non valida");
        }
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
