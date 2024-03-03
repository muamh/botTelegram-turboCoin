package cocktailHandler;

import interfaces.BotModule;
import modulesHandler.CocktailModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CocktailCloseModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    CocktailModule superModule;
    public CocktailCloseModule(CocktailModule m) {
        super("/close");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        m.setText("Comunicazione con i cocktail terminata");
        m.enableMarkdown(true);
        this.superModule.deactivate();
        return m;
    }
}
