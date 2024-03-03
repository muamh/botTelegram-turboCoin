package modulesHandler;

import NmapHandler.*;
import gameHandler.*;
import interfaces.BotModule;
import java.util.ArrayList;
import java.util.Arrays;
import meteoHandler.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GameModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new GameHelpModule(this),
                new GameCloseModule(this),
                new GameStartModule(this)
        ));

    public GameModule() {
        super("/game");
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
                    BotModule m = new GameHelpModule(this);
                    this.activate();
                    return m;
                });

        response = module.handleCommand(update);
        return response;
    }

}
