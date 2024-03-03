package circulariHandler;

import config.BotConfiguration;
import interfaces.BotModule;
import modulesHandler.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CircolariHelpModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    CircolariModule superModule;
    public CircolariHelpModule(CircolariModule m) {
        super("/help");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        m.setText(BotConfiguration.CIRCOLARI_HELP_TEXT);
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
