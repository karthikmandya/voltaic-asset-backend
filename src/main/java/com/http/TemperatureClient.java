package com.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.database.DatabaseOperation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TemperatureClient {
    /*public static void main(String[] args) {
        getTemperature();
    }*/

    public double getTemperature() {
        try {
            // Create HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Build GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://10.189.230.81:5000/temperature"))
                    .GET()
                    .build();

            // Send request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print results
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response: " + response.body());

            // Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            // Extract
            double temperature = root.get("temperature").asDouble();
            double humidity = root.get("humidity").asDouble();
            System.out.println("Temperature: " + temperature + "Humidity: " + humidity);

            // Save in database
            DatabaseOperation dbOp = new DatabaseOperation();
            dbOp.insert("DT-500-11", temperature, temperature+1.0, humidity);

            return temperature;

        } catch (Exception e) {
            return 24.8;
        }
    }
}

