package modulesHandler;

import interfaces.BotModule;
import java.util.ArrayList;
import java.util.Arrays;
import meteoHandler.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import tempMailHandler.*;

public class TempMailModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new TempMailHelpModule(this),
                new TempMailCreateModule(this),
                new TempMailSendModule(this),
                new TempMailCloseModule(this)
        ));

    public TempMailModule() {
        super("/tempmail");
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
                    if(!this.isActive()){
                        BotModule m = new TempMailHelpModule(this);
                        this.activate();
                        return m;
                    } else{
                        BotModule m = new TempMailSendModule(this);
                        this.activate();
                        return m;
                    }
                });

        response = module.handleCommand(update);
        return response;
    }

}
