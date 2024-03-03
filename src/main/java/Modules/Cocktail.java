package Modules;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.sun.java.accessibility.util.Translator;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Cocktail {
    private static final String CHIAVE_API = "a0f2607ad3a4d50f48620eac72300439";
    private static final String URL_API = "https://www.thecocktaildb.com/api/json/v1/1/";

    public static String ottieniInformazioniCocktail() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = costruisciURL("random.php");

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Codice inaspettato " + response);

            String datiRisposta = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(datiRisposta).getAsJsonObject();
            JsonArray arrayCocktail = jsonObject.getAsJsonArray("drinks");
            JsonElement elementoCocktail = arrayCocktail.get(0);
            return analizzaInformazioniCocktail((JsonObject)elementoCocktail);
        }
    }
    
    public static String ottieniTuttiCocktail() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = costruisciURL("search.php?s=");

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Codice inaspettato " + response);
            
            StringBuilder nomiCocktail = new StringBuilder();
            nomiCocktail.append("Cocktail disponiili: ");
            String datiRisposta = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(datiRisposta).getAsJsonObject();
            JsonArray arrayCocktail = jsonObject.getAsJsonArray("drinks");
            for(JsonElement elementoCocktail : arrayCocktail){
                nomiCocktail.append(analizzaCocktail((JsonObject)elementoCocktail)).append(", ");
            }
            return nomiCocktail.toString();
        }
    }

    private static String costruisciURL(String content) {
        return String.format(URL_API + content);
    }

    private static String analizzaInformazioniCocktail(JsonObject jsonObject) {
        int id = analizzaId(jsonObject);
        String cocktail = analizzaCocktail(jsonObject);
        String istruzioni = analizzaIstruzioni(jsonObject);
        String ingredienti = analizzaIngredienti(jsonObject);

        return String.format("Id: %s;\nCocktail: %s;\nIstruzioni: %s;\nIngredienti: %s", id, cocktail, istruzioni, ingredienti);
    }
    
    private static int analizzaId(JsonObject jsonObject) {
        return jsonObject.get("idDrink").getAsInt();
    }
    
    private static String analizzaCocktail(JsonObject jsonObject) {
        return jsonObject.get("strDrink").getAsString();
    }
    
    private static String analizzaIstruzioni(JsonObject jsonObject) {
        return jsonObject.get("strInstructions").getAsString();
    }
    
    private static String analizzaIngredienti(JsonObject jsonObject) {
        StringBuilder ingredienti = new StringBuilder();

        for (int i = 1; i <= 15; i++) {
            try{
                String nomeIngrediente = jsonObject.get("strIngredient" + i).getAsString();
                String misuraIngrediente = jsonObject.get("strMeasure" + i).getAsString();
                

                if (!nomeIngrediente.isEmpty()) {
                    ingredienti.append(nomeIngrediente);
                    if (!misuraIngrediente.isEmpty()) {
                        ingredienti.append(" (").append(misuraIngrediente).append("), ");
                    }
                    else{
                        ingredienti.append(", ");
                    }
                } else {
                    ingredienti.deleteCharAt(ingredienti.length()-1);
                    ingredienti.append(".");
                    break;  // Interrompi se non ci sono più ingredienti
                }
            }catch(Exception e){
                break;
            }
        }

        return ingredienti.toString().trim();
    }

    public static String getCocktailIngredientsList() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = costruisciURL("list.php?i=list");

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Codice inaspettato " + response);

            String datiRisposta = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(datiRisposta).getAsJsonObject();
            JsonArray arrayIngredients = jsonObject.getAsJsonArray("drinks");

            StringBuilder ingredientiList = new StringBuilder();
            ingredientiList.append("Ingredienti dei cocktail: ");
            for (JsonElement element : arrayIngredients) {
                JsonObject ingredientObject = element.getAsJsonObject();
                String nomeIngrediente = ingredientObject.get("strIngredient1").getAsString();
                ingredientiList.append(nomeIngrediente).append(", ");
            }

            return ingredientiList.toString();
        }
    }
    
}
