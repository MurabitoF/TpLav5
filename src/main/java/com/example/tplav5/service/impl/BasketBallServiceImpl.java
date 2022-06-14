package com.example.tplav5.service.impl;

import com.example.tplav5.model.BasketBallData;
import com.example.tplav5.model.User;
import com.example.tplav5.service.BasketBallService;
import com.example.tplav5.utils.BasketApiResponse;
import com.example.tplav5.utils.JsonBodyHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class BasketBallServiceImpl implements BasketBallService {

    @Override
    public List<BasketBallData> getLiveMatches() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sports-live-scores.p.rapidapi.com/basketball/live"))
                .header("X-RapidAPI-Key", "a879c9aa41mshb46d8a3fd86f2ecp1d2bbajsn990e14cb1ee3")
                .header("X-RapidAPI-Host", "sports-live-scores.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<BasketApiResponse> response = HttpClient.newHttpClient().send(request, new JsonBodyHandler<>(BasketApiResponse.class));

        return response.body().getMatches();
    }

    @Override
    public String getDataFromApiOff() throws IOException, InterruptedException {

        User user = new User("franco", "1234");
        String json = JsonBodyHandler.convertFromObjectToJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/login"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
