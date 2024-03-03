/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lyricsHandler;

import Modules.YoutubeVideoDownloader;
import com.fasterxml.jackson.core.JsonProcessingException;
import interfaces.BotModule;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulesHandler.LyricsModule;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author Husm
 */
public class LyricsFoundModule extends BotModule {
    /**
     * Viene richiamato al comando /meteo e in caso di comando inesistente
     * */
    LyricsModule superModule;
    public LyricsFoundModule(LyricsModule m) {
        super("/link");
        superModule = m;
    }

    @Override
    public PartialBotApiMethod<?> handleCommand(Update update) {
        // TODO: stampare manuale / gestire errore ecc
        SendMessage m = new SendMessage();
        m.setChatId(update.getMessage().getChatId());
        String lyrics = "";
        try {
            lyrics = (String) YoutubeVideoDownloader.lyrics(update.getMessage().getText().substring(1));
        } catch (InterruptedException ex) {
            Logger.getLogger(LyricsFoundModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(LyricsFoundModule.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LyricsFoundModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.setText(lyrics);
        
        this.superModule.activate();
        return m;
    }
}
