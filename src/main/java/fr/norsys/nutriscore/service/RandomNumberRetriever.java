package fr.norsys.nutriscore.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class RandomNumberRetriever {

    public static final String RANDOM_NUMBER_URL = "https://www.random.org/integers/?num=1&min=0&max=4&col=1&base=10&format=plain&rnd=new";

    private HttpURLConnection httpConnection;

    public void init() throws IOException {
        URL url = new URL(RANDOM_NUMBER_URL);

        httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("GET");
    }

    public int retrieveRandomNumberFromMath(){
        return (int) (Math.random() * 4);
    }

    public int retrieveRandomNumberFromUrl() throws IOException {
        // TODO
        System.out.println("calling connect");
        httpConnection.connect();

        int responseCode = httpConnection.getResponseCode();
        String responseMessage = httpConnection.getResponseMessage();
        //Get response content payload

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(RANDOM_NUMBER_URL))
                    .GET()
                    .build();
            //TODO
            HttpClient client = HttpClient.newHttpClient();

            //TODO that can be blocking; be sure to call it not in the EDT
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            System.out.println("get random number response: " + responseBody);
            return Integer.parseInt(responseBody.trim());
        } catch (URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    };


}
