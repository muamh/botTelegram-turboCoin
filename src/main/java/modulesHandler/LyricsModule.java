package modulesHandler;

import lyricsHandler.LyricsFoundModule;
import lyricsHandler.*;
import interfaces.BotModule;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import meteoHandler.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class LyricsModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new LyricsCloseModule(this),
                new LyricsHelpModule(this),
                new LyricsFoundModule(this)
        ));

    public LyricsModule() {
        super("/lyrics");
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
                        BotModule m = new LyricsHelpModule(this);
                        this.activate();
                        return m;
                    } else{
                        BotModule m = new LyricsFoundModule(this);
                        this.activate();
                        return m;
                    }
                });

        response = module.handleCommand(update);
        return response;
    }

}
