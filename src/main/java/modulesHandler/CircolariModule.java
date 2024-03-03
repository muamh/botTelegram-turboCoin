package modulesHandler;

import circulariHandler.*;
import interfaces.BotModule;
import java.util.ArrayList;
import java.util.Arrays;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CircolariModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new CircolariHelpModule(this),
                new CircolariCloseModule(this),
                new CircolariFamilyModule(this),
                new CircolariUltimaModule(this),
                new CircolariAtaModule(this)
        ));

    public CircolariModule() {
        super("/circolari");
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
                    BotModule m = new CircolariHelpModule(this);
                    this.activate();
                    return m;
                });

        response = module.handleCommand(update);
        return response;
    }

}
