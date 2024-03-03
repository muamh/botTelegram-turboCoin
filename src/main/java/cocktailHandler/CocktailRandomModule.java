package cocktailHandler;

import Modules.Cocktail;
import config.BotConfiguration;
import interfaces.BotModule;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.CocktailModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CocktailRandomModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    CocktailModule superModule;
    public CocktailRandomModule(CocktailModule m) {
        super("/random");
        superModule = m;
    }

    @Override
    public BotApiMethod<Message> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());

        try {
            m.setText(Cocktail.ottieniInformazioniCocktail());
        } catch (IOException ex) {
            m.setText("Errore durante l'interrogazione con il database");
        }
        m.enableMarkdown(true);
        this.superModule.activate();
        return m;
    }
}
