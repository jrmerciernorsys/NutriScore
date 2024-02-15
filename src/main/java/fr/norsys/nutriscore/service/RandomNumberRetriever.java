package fr.norsys.nutriscore.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RandomNumberRetriever {

    public static final String RANDOM_NUMBER_URL = "https://www.random.org/integers/?num=1&min=0&max=4&col=1&base=10&format=plain&rnd=new";

    private static HttpURLConnection httpConnection;

    private static boolean initialized = false;

    private static void init() throws IOException {
        URL url = new URL(RANDOM_NUMBER_URL);

        httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("GET");
        initialized = true;
    }

    public int retrieveRandomNumberFromMath(){
        return (int) (Math.random() * 4);
    }

    public static int retrieveRandomNumberFromUrl() throws IOException {
        if (!initialized) {
            init();
        }
        System.out.println("calling connect");
        httpConnection.connect();

        //TODO : change URL if server is "too busy right now, please back off" or use Math API
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(RANDOM_NUMBER_URL))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body().trim();
            System.out.println("get random number response: " + responseBody);
            return Integer.parseInt(responseBody);
        } catch (URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            httpConnection.disconnect();
        }
    }


}
