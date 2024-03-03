package NmapHandler;

import meteoHandler.*;
import config.BotConfiguration;
import interfaces.BotModule;
import modulesHandler.NmapModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NmapCloseModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    NmapModule superModule;
    public NmapCloseModule(NmapModule m) {
        super("/close");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        m.setText("Comunicazio con /nmap interrotta!");
        m.enableMarkdown(true);
        this.superModule.deactivate();
        return m;
    }

}
