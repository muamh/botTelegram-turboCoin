import interfaces.BotModule;
import modulesHandler.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {



    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);

        ArrayList<BotModule> modules = new ArrayList<>(Arrays.asList(
                // TODO: registrare tutti i moduli presenti
                new HelloModule(),
                new MeteoModule(),
                new CocktailModule(),
                new CircolariModule(),
                new NewsModule(),
                new ImageSearchModule(),
                new TempMailModule(),
                new QuoteModule(),
                new NmapModule(),
                new Mp3Module(),
                new LyricsModule(),
                new GoldenModule(),
                new GameModule(),
                new HelpModule()
        ));

        bot.registerBot(new MultiApiTelegramBot(modules));

    }
}
