package modulesHandler;

import NmapHandler.*;
import interfaces.BotModule;
import java.util.ArrayList;
import java.util.Arrays;
import meteoHandler.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NmapModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new NmapHelpModule(this),
                new NmapSearcherModule(this),
                new NmapCloseModule(this)
        ));

    public NmapModule() {
        super("/nmap");
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
                        BotModule m = new NmapHelpModule(this);
                        this.activate();
                        return m;
                    }else{
                        BotModule m = new NmapSearcherModule(this);
                        this.activate();
                        return m;
                    }
                });

        response = module.handleCommand(update);
        return response;
    }

}
