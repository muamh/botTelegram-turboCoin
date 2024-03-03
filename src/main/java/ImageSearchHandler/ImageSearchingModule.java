package ImageSearchHandler;

import Modules.ImgSearch;
import interfaces.BotModule;
import modulesHandler.ImageSearchModule;
import org.apache.commons.io.IOUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ImageSearchingModule extends BotModule {
    ImageSearchModule superModule;

    public ImageSearchingModule(ImageSearchModule m) {
        super("/gatto");
        superModule = m;
    }

    @Override
    public PartialBotApiMethod<?> handleCommand(Update update) {
        SendPhoto sendPhoto = new SendPhoto();
        try {
            // TODO: gestisci errori ecc..
            String imageUrl = "";
            if(update.getMessage().getText().startsWith("/")){
                imageUrl = ImgSearch.getImageUrl(update.getMessage().getText().split("/")[0]);
            }
            else{
                imageUrl = ImgSearch.getImageUrl(update.getMessage().getText());
            }

            sendPhoto.setChatId(update.getMessage().getChatId());
            sendPhoto.setPhoto(new InputFile(imageUrl));

            this.superModule.activate();
            return sendPhoto;

        } catch (Exception e) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Errore durante l'invio del messaggio: " + e.getMessage());

            this.superModule.activate();
            return sendMessage;
        }
    }

}
