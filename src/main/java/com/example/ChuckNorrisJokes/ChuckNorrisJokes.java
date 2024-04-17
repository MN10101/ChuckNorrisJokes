package com.example.ChuckNorrisJokes;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.stream.IntStream;

public class ChuckNorrisJokes {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        // Number of jokes to fetch
        int numOfJokes = 5;

        IntStream.rangeClosed(1, numOfJokes)
                .forEachOrdered(jokeNumber -> {
                    try {
                        // Create a request to fetch a random Chuck Norris joke
                        Request request = new Request.Builder()
                                .url("https://api.chucknorris.io/jokes/random")
                                .build();

                        // Execute the request
                        Response response = client.newCall(request).execute();

                        // Check if the response is successful (status code is 200)
                        if (response.isSuccessful()) {
                            // Parse the JSON response
                            JSONObject jsonResponse = new JSONObject(response.body().string());

                            // Extract the joke text from the response
                            String joke = jsonResponse.getString("value");

                            // Display the retrieved joke
                            System.out.println("Chuck Norris Joke " + jokeNumber + ":");
                            System.out.println(joke);
                            System.out.println();
                        } else {
                            System.out.println("Failed to fetch Chuck Norris joke: " + response.code() + " " + response.message());
                        }
                    } catch (IOException e) {
                        System.out.println("Error while fetching Chuck Norris joke: " + e.getMessage());
                    }
                });
    }
}
