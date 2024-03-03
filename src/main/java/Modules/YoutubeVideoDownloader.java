package Modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jagrosh.jlyrics.Lyrics;
import com.jagrosh.jlyrics.LyricsClient;
import io.github.gaeqs.javayoutubedownloader.decoder.HTMLDecoder;
import io.github.gaeqs.javayoutubedownloader.stream.StreamOption;
import io.github.gaeqs.javayoutubedownloader.stream.YoutubeVideo;
import io.github.gaeqs.javayoutubedownloader.stream.download.StreamDownloader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import org.json.JSONObject;

public class YoutubeVideoDownloader {
    public static boolean download(String url, File folder) throws MalformedURLException, IOException {
	//Extracts and decodes all streams.
        HTMLDecoder htmlDecoder = new HTMLDecoder(url);
	YoutubeVideo video = htmlDecoder.extractVideo(new URL(url)); //JavaYoutubeDownloader.decodeOrNull(url, MultipleDecoderMethod.AND, "html", "embedded");
	//Gets the option with the greatest quality that has video and audio.
	StreamOption option = video.getStreamOptions().stream()
		.filter(target -> target.getType().hasVideo() && target.getType().hasAudio())
		.min(Comparator.comparingInt(o -> o.getType().getVideoQuality().ordinal())).orElse(null);
	//If there is no option, returns false.
	if (option == null) return false;
	//Prints the option type.
	System.out.println(option.getType());
	//Creates the file. folder/title.extension
        if(new File("audio.mp4").exists()){
            new File("audio.mp4").delete();
        }
	File file = new File(folder, "audio." + option.getType().getContainer().toString().toLowerCase());
        
	//Creates the downloader.
	StreamDownloader downloader = new StreamDownloader(option, file, null);
	//Runs the downloader.
	new Thread(downloader).start();
	return true;
    }
    
    public static String link (String t) throws MalformedURLException, JsonProcessingException, IOException, InterruptedException{
        String downloadUrl = "";
        try {
            // URL della richiesta
            String urlString = "https://yt-cw.fabdl.com/youtube/get?url=" + t + "&mp3_task=2";
            URL url = new URL(urlString);
            
            // Apertura della connessione
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Lettura della risposta
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            // Parsare la risposta JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            
            // Ottenere l'oggetto "results" dalla risposta JSON
            JSONObject results = jsonResponse.getJSONObject("result");
            
            // Ottenere il valore di mp3_download_url dall'oggetto "results"
            String mp3DownloadUrl = results.getString("get_mp3_download_url");
            
            
            // Chiusura della connessione
            URL mp3Url = new URL(mp3DownloadUrl);
            HttpURLConnection mp3Connection = (HttpURLConnection) mp3Url.openConnection();
            mp3Connection.setRequestMethod("GET");

            // Lettura della risposta
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(mp3Connection.getInputStream()));
            StringBuilder response2 = new StringBuilder();
            String line2;
            while ((line2 = reader2.readLine()) != null) {
                response2.append(line2);
            }
            reader.close();

            // Stampare la risposta
            /*
            System.out.println("Response from mp3DownloadUrl:");
            System.out.println(response2.toString());*/
            JSONObject jsonResponse2 = new JSONObject(response2.toString());
            
            // Ottenere l'oggetto "result" dalla risposta JSON
            JSONObject result2 = jsonResponse2.getJSONObject("result");
            
            // Ottenere il valore di "download_URL" dall'oggetto "result"
            downloadUrl = result2.getString("download_url");

            
            // Chiusura della connessione
        } catch (IOException e) {
            e.printStackTrace();
        }
        return downloadUrl;
    }
    
    public static String lyrics(String titolo) throws InterruptedException, ExecutionException, UnsupportedEncodingException{
        LyricsClient lc = new LyricsClient();
        Lyrics lyrics = lc.getLyrics(titolo).get();
        String lyricsContent = "Autore: " +lyrics.getAuthor() + "\n";
        lyricsContent += "Titolo: " + lyrics.getTitle() + "\n";
        lyricsContent += "Lyrics trovati:\n" + lyrics.getContent();
        /*String prova = Traduttore.getTraduzione("io sono una brava, persona");
        System.out.println(prova);*/
        return lyricsContent;
    }
    
}
