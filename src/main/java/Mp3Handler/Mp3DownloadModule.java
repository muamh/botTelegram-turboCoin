/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mp3Handler;

import Modules.YoutubeVideoDownloader;
import com.fasterxml.jackson.core.JsonProcessingException;
import interfaces.BotModule;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.Mp3Module;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class Mp3DownloadModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    Mp3Module superModule;
    public Mp3DownloadModule(Mp3Module m) {
        super("/link");
        superModule = m;
    }

    @Override
    public PartialBotApiMethod<?> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc
        SendAudio m = new SendAudio();
        m.setChatId(update.getMessage().getChatId().toString());
        String t = "";
        String cerca = update.getMessage().getText().substring(1); // Replace with your desired song title
        try {
            t = (String) YoutubeVideoDownloader.link(cerca);
        } catch (JsonProcessingException ex) {
            m.setCaption("Errore nella lettura del link");
        } catch (IOException ex) {
            m.setCaption("Errore nel link inserito");
        } catch (InterruptedException ex) {
            m.setCaption("Errore durante il caricamento delle risorse");
        }
        
        m.setAudio(new InputFile(t));
        m.setCaption("Conversione completata");
        m.enableNotification();
        
        this.superModule.activate();
        return m;
    }
}
