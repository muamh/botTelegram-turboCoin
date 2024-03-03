package modulesHandler;

import interfaces.BotModule;
import java.util.ArrayList;
import java.util.Arrays;
import newsHandler.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NewsModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new NewsHelpModule(this),
                new NewsCronacaModule(this),
                new NewsEconomiaModule(this),
                new NewsIntrattenimentoModule(this),
                new NewsSaluteModule(this),
                new NewsScienzaModule(this),
                new NewsSportModule(this),
                new NewsTecnologiaModule(this),
                new NewsCloseModule(this)
        ));

    public NewsModule() {
        super("/news");
    }

    @Override
    public PartialBotApiMethod<?> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc..
        PartialBotApiMethod<?> response;

        BotModule module = this.modules.parallelStream()
                .filter(
                        (m) -> m.isFired(update.getMessage().getText().toLowerCase()) || m.isActive()
                )
                .findFirst()
                .orElseGet(() -> {
                    BotModule m = new NewsHelpModule(this);
                    this.activate();
                    return m;
                });

        response = module.handleCommand(update);
        return response;
    }

}
