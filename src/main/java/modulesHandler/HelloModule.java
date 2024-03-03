package modulesHandler;

import interfaces.BotModule;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelloModule extends BotModule {

    public HelloModule() {
        super("/ciao");
    }

    @Override
    public PartialBotApiMethod<Message> handleCommand(Update update) {
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());
        String user = update.getMessage().getChat().getFirstName() + " " + update.getMessage().getChat().getLastName();
        m.setText("Ciao " + user);
        this.deactivate();
        return m;
    }
}
