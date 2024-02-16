package fr.norsys.nutriscore.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Provides a static method to retrieve a random number from an API.
 */
public class RandomNumberRetriever {

    public static final String RANDOM_NUMBER_URL = "https://www.random.org/integers/?num=1&min=0&max=4&col=1&base=10&format=plain&rnd=new";
    public static final int API_CALL_TIMEOUT_IN_MILLISECONDS = 1500;

    private static boolean initialized = false;

    private static HttpClient httpClient;

    private static void init() {
        httpClient = HttpClient.newHttpClient();
        initialized = true;
    }

    public static int retrieveRandomNumberFromUrl() throws IOException {
        if (!initialized) {
            init();
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(RANDOM_NUMBER_URL))
                    .GET()
                    .timeout(Duration.ofMillis(API_CALL_TIMEOUT_IN_MILLISECONDS))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body().trim();
            System.out.println("get random number response: " + responseBody);
            return Integer.parseInt(responseBody);
        } catch (URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
