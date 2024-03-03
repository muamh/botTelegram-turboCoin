package modulesHandler;

import cocktailHandler.*;
import interfaces.BotModule;
import java.util.ArrayList;
import java.util.Arrays;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CocktailModule  extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new CocktailHelpModule(this),
                new CocktailRandomModule(this),
                new CocktailIngredientsModule(this),
                new CocktailAllModule(this),
                new CocktailCloseModule(this)
        ));
    
    public CocktailModule() {
        super("/cocktail");
    }

    @Override
    public PartialBotApiMethod<?> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        PartialBotApiMethod<?> response;

        BotModule module = this.modules.parallelStream()
                .filter(
                        (m) -> m.isFired(update.getMessage().getText()) || m.isActive()
                )
                .findFirst()
                .orElseGet(() -> {
                    BotModule m = new CocktailHelpModule(this);
                    this.activate();
                    return m;
                });

        response = module.handleCommand(update);
        return response;
    }

}
