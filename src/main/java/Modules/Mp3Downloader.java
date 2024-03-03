package Modules;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.sound.sampled.*;

public class Mp3Downloader {

    public static void convertOnlineVideoToMP3(String videoUrl, String outputMP3Path) throws UnsupportedAudioFileException {
        try {
            // Apri uno stream di input dal video URL
            URL url = new URL(videoUrl);
            try (InputStream in = url.openStream()) {
                // Crea un AudioInputStream dallo stream di input
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(in);

                // Specifica il formato audio di output (MP3)
                AudioFormat outFormat = new AudioFormat(44100, 16, 1, true, false);

                // Crea un nuovo AudioInputStream convertito
                AudioInputStream convertedAudioInputStream = AudioSystem.getAudioInputStream(outFormat, audioInputStream);

                // Scrivi il file audio MP3
                AudioSystem.write(convertedAudioInputStream, AudioFileFormat.Type.WAVE, new java.io.File(outputMP3Path));

                // Chiudi gli stream audio
                audioInputStream.close();
                convertedAudioInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
