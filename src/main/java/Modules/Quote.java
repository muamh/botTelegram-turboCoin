package Modules;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import org.json.JSONArray;

public class Quote {

    public static String getQuote() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://zenquotes.io/api/random")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONArray jsonArray = new JSONArray(responseBody);

                // Prendi il primo elemento dall'array
                JSONObject quoteObject = jsonArray.getJSONObject(0);

                String quote = "**" + quoteObject.getString("a") + "**: " + quoteObject.getString("q");
                return quote;
            } else {
                return "Errore nella lettura del quote. Codice di risposta: " + response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Errore nella lettura del quote.";
        }
    }
}
