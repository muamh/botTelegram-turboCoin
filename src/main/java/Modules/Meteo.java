package Modules;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Meteo {

    private static final String CHIAVE_API = "a0f2607ad3a4d50f48620eac72300439";
    private static final String URL_API = "http://api.openweathermap.org/data/2.5/weather";

    public static String ottieniInformazioniMeteo(String nomeCittà) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = costruisciURL(nomeCittà);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Codice inaspettato " + response);

            String datiRisposta = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(datiRisposta).getAsJsonObject();

            return analizzaInformazioniMeteo(jsonObject);
        }
    }

    private static String costruisciURL(String nomeCittà) {
        return String.format("%s?q=%s&appid=%s&lang=it", URL_API, nomeCittà, CHIAVE_API);
    }


    private static String analizzaInformazioniMeteo(JsonObject jsonObject) {
        String coordinate = analizzaCoordinate(jsonObject);
        String luogo = analizzaLuogo(jsonObject);
        String meteo = analizzaMeteo(jsonObject);
        String temperatura = analizzaTemperatura(jsonObject);

        return String.format("Luogo: %s;\nCoordinate: %s;\nMeteo: %s;\nTemperatura: %s°C", luogo, coordinate, meteo, temperatura);
    }

    private static String analizzaCoordinate(JsonObject jsonObject) {
        JsonObject oggettoCoordinate = jsonObject.getAsJsonObject("coord");
        double longitudine = oggettoCoordinate.getAsJsonPrimitive("lon").getAsDouble();
        double latitudine = oggettoCoordinate.getAsJsonPrimitive("lat").getAsDouble();
        return String.format("%.2f, %.2f", longitudine, latitudine);
    }

    private static String analizzaLuogo(JsonObject jsonObject) {
        return jsonObject.get("name").getAsString();
    }

    private static String analizzaMeteo(JsonObject jsonObject) {
        JsonArray arrayMeteo = jsonObject.getAsJsonArray("weather");
        JsonElement elementoMeteo = arrayMeteo.get(0);
        JsonObject oggettoMeteo = elementoMeteo.getAsJsonObject();
        return oggettoMeteo.get("description").getAsString();
    }

    private static String analizzaTemperatura(JsonObject jsonObject) {
        JsonObject oggettoPrincipale = jsonObject.getAsJsonObject("main");
        double temperaturaKelvin = oggettoPrincipale.getAsJsonPrimitive("temp").getAsDouble();
        double temperaturaCelsius = temperaturaKelvin - 273.15;
        return String.format("%.2f°C", temperaturaCelsius);
    }
}
