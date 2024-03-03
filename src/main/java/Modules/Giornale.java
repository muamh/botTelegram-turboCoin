package Modules;

import java.io.IOException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Giornale {

    private static final String CHIAVE_API = "4ac93425d2374cd883a0952ee3584605";
    private static final String URL_API_NOTIZIE = "https://newsapi.org/v2/top-headlines";
    private static final String PAESE = "it";

    private static String ultimeNotiziePerCategoria(String categoria) {
        OkHttpClient client = new OkHttpClient();
        String url = String.format("%s?country=%s&category=%s&apiKey=%s", URL_API_NOTIZIE, PAESE, categoria, CHIAVE_API);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Codice inaspettato " + response);

            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            return "Errore durante la richiesta delle notizie.";
        }
    }

    private static String formattaNotizia(String titolo, String localita, String data) {
        StringBuilder risultato = new StringBuilder();
        risultato.append(titolo).append(";\n");

        if (!localita.isEmpty()) {
            risultato.append("Luogo: ").append(localita).append(";\n");
        }

        risultato.append("Data: ").append(data.split("T")[0]).append(";");

        return risultato.toString();
    }

    public static String ultimeNotizieCronaca() {
        String rispostaRaw = ultimeNotiziePerCategoria("general");

        JsonObject jsonObject = JsonParser.parseString(rispostaRaw).getAsJsonObject();
        JsonArray arrayNotizie = jsonObject.getAsJsonArray("articles");

        if (arrayNotizie.size() > 0) {
            JsonObject primaNotizia = arrayNotizie.get(0).getAsJsonObject();
            String titolo = primaNotizia.get("title").getAsString();
            String localita = primaNotizia.has("location") ? primaNotizia.get("location").getAsString() : "";
            String data = primaNotizia.get("publishedAt").getAsString();

            return formattaNotizia(titolo, localita, data);
        } else {
            return "Nessuna notizia disponibile.";
        }
    }

    public static String ultimeNotizieEconomia() {
        String rispostaRaw = ultimeNotiziePerCategoria("business");

        JsonObject jsonObject = JsonParser.parseString(rispostaRaw).getAsJsonObject();
        JsonArray arrayNotizie = jsonObject.getAsJsonArray("articles");

        if (arrayNotizie.size() > 0) {
            JsonObject primaNotizia = arrayNotizie.get(0).getAsJsonObject();
            String titolo = primaNotizia.get("title").getAsString();
            String localita = primaNotizia.has("location") ? primaNotizia.get("location").getAsString() : "";
            String data = primaNotizia.get("publishedAt").getAsString();

            return formattaNotizia(titolo, localita, data);
        } else {
            return "Nessuna notizia disponibile.";
        }
    }

    public static String ultimeNotizieIntrattenimento() {
        String rispostaRaw = ultimeNotiziePerCategoria("entertainment");

        JsonObject jsonObject = JsonParser.parseString(rispostaRaw).getAsJsonObject();
        JsonArray arrayNotizie = jsonObject.getAsJsonArray("articles");

        if (arrayNotizie.size() > 0) {
            JsonObject primaNotizia = arrayNotizie.get(0).getAsJsonObject();
            String titolo = primaNotizia.get("title").getAsString();
            String localita = primaNotizia.has("location") ? primaNotizia.get("location").getAsString() : "";
            String data = primaNotizia.get("publishedAt").getAsString();

            return formattaNotizia(titolo, localita, data);
        } else {
            return "Nessuna notizia disponibile.";
        }
    }

    public static String ultimeNotizieSalute() {
        String rispostaRaw = ultimeNotiziePerCategoria("health");
        
        JsonObject jsonObject = JsonParser.parseString(rispostaRaw).getAsJsonObject();
        JsonArray arrayNotizie = jsonObject.getAsJsonArray("articles");

        if (arrayNotizie.size() > 0) {
            JsonObject primaNotizia = arrayNotizie.get(0).getAsJsonObject();
            String titolo = primaNotizia.get("title").getAsString();
            String localita = primaNotizia.has("location") ? primaNotizia.get("location").getAsString() : "";
            String data = primaNotizia.get("publishedAt").getAsString();

            return formattaNotizia(titolo, localita, data);
        } else {
            return "Nessuna notizia disponibile.";
        }
    }

    public static String ultimeNotizieScienzaTecnologia() {
        String rispostaRaw = ultimeNotiziePerCategoria("science");
        
        JsonObject jsonObject = JsonParser.parseString(rispostaRaw).getAsJsonObject();
        JsonArray arrayNotizie = jsonObject.getAsJsonArray("articles");

        if (arrayNotizie.size() > 0) {
            JsonObject primaNotizia = arrayNotizie.get(0).getAsJsonObject();
            String titolo = primaNotizia.get("title").getAsString();
            String localita = primaNotizia.has("location") ? primaNotizia.get("location").getAsString() : "";
            String data = primaNotizia.get("publishedAt").getAsString();

            return formattaNotizia(titolo, localita, data);
        } else {
            return "Nessuna notizia disponibile.";
        }
    }

    public static String ultimeNotizieSport() {
        String rispostaRaw = ultimeNotiziePerCategoria("sports");
        
        JsonObject jsonObject = JsonParser.parseString(rispostaRaw).getAsJsonObject();
        JsonArray arrayNotizie = jsonObject.getAsJsonArray("articles");

        if (arrayNotizie.size() > 0) {
            JsonObject primaNotizia = arrayNotizie.get(0).getAsJsonObject();
            String titolo = primaNotizia.get("title").getAsString();
            String localita = primaNotizia.has("location") ? primaNotizia.get("location").getAsString() : "";
            String data = primaNotizia.get("publishedAt").getAsString();

            return formattaNotizia(titolo, localita, data);
        } else {
            return "Nessuna notizia disponibile.";
        }
    }

    public static String ultimeNotizieTecnologia() {
        String rispostaRaw = ultimeNotiziePerCategoria("technology");
        
        JsonObject jsonObject = JsonParser.parseString(rispostaRaw).getAsJsonObject();
        JsonArray arrayNotizie = jsonObject.getAsJsonArray("articles");

        if (arrayNotizie.size() > 0) {
            JsonObject primaNotizia = arrayNotizie.get(0).getAsJsonObject();
            String titolo = primaNotizia.get("title").getAsString();
            String localita = primaNotizia.has("location") ? primaNotizia.get("location").getAsString() : "";
            String data = primaNotizia.get("publishedAt").getAsString();

            return formattaNotizia(titolo, localita, data);
        } else {
            return "Nessuna notizia disponibile.";
        }
    }
}
