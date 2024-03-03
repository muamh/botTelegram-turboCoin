package Modules;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImgSearch {

    private static final String SERPAPI_API_KEY = "223bcfbd0a87cbdebd9d3a30ef8dfeec99f1b57d10ec83e03a2ab9cf68ffad2e";

    public static String getImageUrl(String searchTerm) {
        try {
            HttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(RequestConfig.custom().build())
                    .build();
            
            HttpGet request = new HttpGet("https://serpapi.com/search.json?"
                    + "q=" + searchTerm
                    + "&engine=google_images&ijn=0"
                    + "&api_key=" + SERPAPI_API_KEY );
            
            org.apache.http.HttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray hits = jsonResponse.getJSONArray("images_results");
            if (hits.length() > 0) {
                return hits.getJSONObject(0).getString("link");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date parseExpiresDate(String expires) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        return dateFormat.parse(expires);
    }
}
